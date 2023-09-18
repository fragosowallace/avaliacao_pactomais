package br.com.wallace.avaliacao.avaliacao.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wallace.avaliacao.avaliacao.enums.TipoOperacaoEnum;
import br.com.wallace.avaliacao.avaliacao.exception.TransacaoRecusadaException;
import br.com.wallace.avaliacao.avaliacao.model.Conta;
import br.com.wallace.avaliacao.avaliacao.model.LimiteConta;
import br.com.wallace.avaliacao.avaliacao.model.TipoOperacao;
import br.com.wallace.avaliacao.avaliacao.model.Transacao;
import br.com.wallace.avaliacao.avaliacao.repository.ContaRepository;
import br.com.wallace.avaliacao.avaliacao.repository.LimiteContaRepository;
import br.com.wallace.avaliacao.avaliacao.repository.TipoOperacaoRepository;
import br.com.wallace.avaliacao.avaliacao.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private LimiteContaRepository limiteContaRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TipoOperacaoRepository tipoOperacaoRepository;

    @Transactional(rollbackFor = Exception.class)
    public Transacao criarTransacao(Transacao transacao) throws Exception {
        TipoOperacaoEnum tipoOperacao = TipoOperacaoEnum.fromDescricao(transacao.getTipoOperacao().getDescricao());
        
        if (tipoOperacao == TipoOperacaoEnum.PAGAMENTO) {
            processarPagamento(transacao);
            return transacaoRepository.save(transacao);
        }

        processarSaque(transacao);
        return transacaoRepository.save(transacao);
    }

    private void processarSaque(Transacao transacao) throws Exception {
        LimiteConta limiteConta = recuperaLimiteConta(transacao);

        if ( ( transacao.getValor() * -1) > limiteConta.getLimite()) {
            throw new TransacaoRecusadaException("Transação recusada. Limite de crédito insuficiente. Consulte em /"+transacao.getConta().getId()+"/limite");
        }

        double novoLimite = Math.max(limiteConta.getLimite() - ( transacao.getValor() * -1), 0);
        limiteConta.setLimite(novoLimite);
        limiteContaRepository.save(limiteConta);
    }

    private void processarPagamento(Transacao transacao) {
        LimiteConta limiteConta = recuperaLimiteConta(transacao);
        double novoLimite = limiteConta.getLimite() + transacao.getValor();
        limiteConta.setLimite(novoLimite);
        limiteContaRepository.save(limiteConta);
    }

    public Optional<Transacao> findById(int contaId) {
        return transacaoRepository.findById(contaId);
    }

    private LimiteConta recuperaLimiteConta(Transacao transacao){
        LimiteConta limiteConta = limiteContaRepository.findByContaId(transacao.getConta().getId()).orElse(null);

        // Se não existir, cria
        if ( limiteConta == null ){
            limiteConta = new LimiteConta();
            limiteConta.setConta(transacao.getConta());
            limiteConta.setDataLimite(LocalDate.now());
            limiteConta.setLimite(0);
        }

        return limiteConta;
    }

    public void novaTransacao(int contaId, int tipoOperacaoId, Double valor) throws Exception {
        Conta conta = contaRepository.findById(contaId).orElse(null);
        TipoOperacao tipoOperacao = tipoOperacaoRepository.findById(tipoOperacaoId).orElse(null);
     
        if (conta == null || tipoOperacao == null) {
            throw new TransacaoRecusadaException("Conta ou tipo de operação não encontrados");
        }

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setTipoOperacao(tipoOperacao);
        transacao.setDataTransacao(LocalDateTime.now());
       
        if (tipoOperacaoId == TipoOperacaoEnum.PAGAMENTO.getValor() ) {
            transacao.setValor(valor);
        }else{
            transacao.setValor(-valor);
        }

        criarTransacao(transacao);
    }
}
