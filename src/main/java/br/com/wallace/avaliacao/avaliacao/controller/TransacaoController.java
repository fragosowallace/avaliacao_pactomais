package br.com.wallace.avaliacao.avaliacao.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wallace.avaliacao.avaliacao.model.Conta;
import br.com.wallace.avaliacao.avaliacao.model.TipoOperacao;
import br.com.wallace.avaliacao.avaliacao.model.Transacao;
import br.com.wallace.avaliacao.avaliacao.repository.ContaRepository;
import br.com.wallace.avaliacao.avaliacao.repository.TipoOperacaoRepository;
import br.com.wallace.avaliacao.avaliacao.repository.TransacaoRepository;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;
    private final TipoOperacaoRepository tipoOperacaoRepository;

    public TransacaoController(TransacaoRepository transacaoRepository, ContaRepository contaRepository, TipoOperacaoRepository tipoOperacaoRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
        this.tipoOperacaoRepository = tipoOperacaoRepository;
    }

    @PostMapping
    public ResponseEntity<?> criarTransacao(@RequestBody Map<String, Object> requestBody) {
        int contaId = Integer.parseInt(requestBody.get("conta_id").toString());
        int tipoOperacaoId = Integer.parseInt(requestBody.get("tipo_operacao_id").toString());
        Double valor = Double.valueOf(requestBody.get("valor").toString());

        Conta conta = contaRepository.findById(contaId).orElse(null);
        TipoOperacao tipoOperacao = tipoOperacaoRepository.findById(tipoOperacaoId).orElse(null);

        if (conta == null || tipoOperacao == null) {
            return ResponseEntity.badRequest().body("Conta ou tipo de operação não encontrados");
        }

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setTipoOperacao(tipoOperacao);
        transacao.setValor(valor);
        transacao.setDataTransacao(LocalDateTime.now());

        transacaoRepository.save(transacao);

        return ResponseEntity.ok("Transação criada com sucesso");
    }
}
