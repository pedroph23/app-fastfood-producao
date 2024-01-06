package br.com.appfastfood.carrinho.dominio.modelos.enums;

public enum StatusCarrinhoEnum {
    ABERTO(1, "ABERTO"),
    FECHADO(2, "FECHADO");

    private final int id;
    private final String nome;

    StatusCarrinhoEnum(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public static StatusCarrinhoEnum buscaEnumPorStatusString(String nome) {
        for (StatusCarrinhoEnum status : values()) {
            if (status.getNome().equalsIgnoreCase(nome)){
                return status;
            }
        }
        throw new IllegalArgumentException("Status Iválido: " + nome);
    }

    public static String retornaNomeEnum(StatusCarrinhoEnum status){
        for (StatusCarrinhoEnum statusEnum : values()) {
            if (statusEnum == status) {
                return statusEnum.getNome();
            }
        }
        throw new IllegalArgumentException("Enum de status de carrinho Iválido");
    }
}
