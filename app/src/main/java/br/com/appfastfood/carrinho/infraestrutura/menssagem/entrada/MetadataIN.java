package br.com.appfastfood.carrinho.infraestrutura.menssagem.entrada;

public class MetadataIN<T> {
    public T data;

    public MetadataIN(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
