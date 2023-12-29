package br.com.appfastfood.produto.aplicacao.adaptadores.requisicao;

public class RequisicaoExcecao {


    private String mensagem;
    private int codigo;
    public RequisicaoExcecao(String mensagem, int status){
        this.mensagem = mensagem;
        this.codigo = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getCodigo() {
        return codigo;
    }
}
