package br.com.wallace.avaliacao.avaliacao.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wallace.avaliacao.avaliacao.model.Conta;
import br.com.wallace.avaliacao.avaliacao.model.LimiteConta;
import br.com.wallace.avaliacao.avaliacao.repository.ContaRepository;
import br.com.wallace.avaliacao.avaliacao.repository.LimiteContaRepository;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaRepository contaRepository;
    private final LimiteContaRepository limiteContaRepository;

    public ContaController(ContaRepository contaRepository, LimiteContaRepository limiteContaRepository) {
        this.contaRepository = contaRepository;
        this.limiteContaRepository = limiteContaRepository;
    }

    @PostMapping
    public Conta criarConta(@RequestBody Map<String, String> requestBody) {
        String numeroConta = requestBody.get("numero_conta");
        Conta conta = new Conta();
        conta.setNumeroConta(numeroConta);
        return contaRepository.save(conta);
    }

    @GetMapping("/{contaId}")
    public ResponseEntity<Conta> consultarConta(@PathVariable int contaId) {
        Optional<Conta> conta = contaRepository.findById(contaId);
        return conta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{contaId}/limite")
    public ResponseEntity<Double> visualizarLimiteCredito(@PathVariable int contaId) {
        // Busque o limite de crédito mais recente para a conta
        Optional<Double> limiteMaisRecente = limiteContaRepository.calcularLimiteAtual(contaId);
        return limiteMaisRecente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}