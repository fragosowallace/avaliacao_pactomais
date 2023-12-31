package br.com.wallace.avaliacao.avaliacao.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "tipo_operacao_id")
    private TipoOperacao tipoOperacao;

    @ManyToOne
    @JoinColumn(name = "limite_conta_id")
    private LimiteConta limiteConta;

    private Double valor;
    private LocalDateTime dataTransacao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public TipoOperacao getTipoOperacao() {
        return tipoOperacao;
    }
    public void setTipoOperacao(TipoOperacao tipo) {
        this.tipoOperacao = tipo;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }
    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
    public LimiteConta getLimiteConta() {
        return limiteConta;
    }
    public void setLimiteConta(LimiteConta limiteConta) {
        this.limiteConta = limiteConta;
    }
}
