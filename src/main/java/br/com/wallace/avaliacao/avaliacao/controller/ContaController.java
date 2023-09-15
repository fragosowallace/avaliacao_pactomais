package br.com.wallace.avaliacao.avaliacao.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wallace.avaliacao.avaliacao.model.Conta;
import br.com.wallace.avaliacao.avaliacao.repository.ContaRepository;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaRepository contaRepository;

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
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
   
}
