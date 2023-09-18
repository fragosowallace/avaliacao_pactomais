package br.com.wallace.avaliacao.avaliacao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import br.com.wallace.avaliacao.avaliacao.exception.TransacaoRecusadaException;
import br.com.wallace.avaliacao.avaliacao.service.TransacaoService;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;
    
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<?> criarTransacao(@RequestBody Map<String, Object> requestBody) throws Exception {
        int contaId = Integer.parseInt(requestBody.get("conta_id").toString());
        int tipoOperacaoId = Integer.parseInt(requestBody.get("tipo_operacao_id").toString());
        Double valor = Double.valueOf(requestBody.get("valor").toString());

        try {
            transacaoService.novaTransacao(contaId, tipoOperacaoId, valor);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Será necessário criar limite de credito para esta conta.");
        } catch (TransacaoRecusadaException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Transação criada com sucesso");
    }
}
