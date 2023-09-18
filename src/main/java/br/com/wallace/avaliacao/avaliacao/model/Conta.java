package br.com.wallace.avaliacao.avaliacao.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(unique = true)
    @JsonProperty("numero_conta")
    private String numeroConta;
    
    @OneToOne(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private LimiteConta limiteConta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }    

    public LimiteConta getLimiteConta() {
        return limiteConta;
    }

    public void setLimiteConta(LimiteConta limiteConta) {
        this.limiteConta = limiteConta;
    }
}