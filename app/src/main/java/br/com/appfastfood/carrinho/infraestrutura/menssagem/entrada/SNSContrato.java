package br.com.appfastfood.carrinho.infraestrutura.menssagem.entrada;

public class SNSContrato {

    public String metodo;
    public String descricao;
    public MetadataIN metadata;

    public SNSContrato(String metodo, String descricao, MetadataIN metadata) {
        this.metodo = metodo;
        this.descricao = descricao;
        this.metadata = metadata;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MetadataIN getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataIN metadata) {
        this.metadata = metadata;
    }
}
