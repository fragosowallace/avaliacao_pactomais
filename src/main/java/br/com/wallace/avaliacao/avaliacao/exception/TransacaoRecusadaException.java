package br.com.wallace.avaliacao.avaliacao.exception;

public class TransacaoRecusadaException extends RuntimeException {
    public TransacaoRecusadaException(String mensagem) {
        super(mensagem);
    }
}