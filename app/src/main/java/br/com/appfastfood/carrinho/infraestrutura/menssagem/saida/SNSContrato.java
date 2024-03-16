package br.com.appfastfood.carrinho.infraestrutura.menssagem.saida;


public class SNSContrato {

    public String metodo;
    public String descricao;
    public MetadataOUT metadata;

    public SNSContrato(String metodo, String descricao, MetadataOUT metadata) {
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

    public MetadataOUT getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataOUT metadata) {
        this.metadata = metadata;
    }
}
