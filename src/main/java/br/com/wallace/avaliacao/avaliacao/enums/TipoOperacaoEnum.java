package br.com.wallace.avaliacao.avaliacao.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoOperacaoEnum {
    COMPRA_A_VISTA(1, "COMPRA A VISTA"),
    COMPRA_PARCELADA(2, "COMPRA PARCELADA"),
    SAQUE(3, "SAQUE"),
    PAGAMENTO(4, "PAGAMENTO");

    private final int valor;
    private final String descricao;

    private static final Map<String, TipoOperacaoEnum> descricaoToEnum = new HashMap<>();

    static {
        for (TipoOperacaoEnum tipo : values()) {
            descricaoToEnum.put(tipo.getDescricao(), tipo);
        }
    }

    TipoOperacaoEnum(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoOperacaoEnum fromDescricao(String descricao) {
        return descricaoToEnum.get(descricao);
    }

    public static TipoOperacaoEnum fromId(int id) {
        return descricaoToEnum.get(id);
    }
}
