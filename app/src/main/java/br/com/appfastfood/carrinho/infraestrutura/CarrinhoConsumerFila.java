package br.com.appfastfood.carrinho.infraestrutura;

import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

public interface CarrinhoConsumerFila {

    @RabbitListener(queues = "carrinho-request-exchange")
    void criar(@Payload Message message) throws JsonProcessingException;

    void remover() throws JsonProcessingException;

    void listar(List<CarrinhoEntidade> carrinhoEntidades) throws JsonProcessingException;

    void atualizar(Long id, Carrinho carrinhoAlterado) throws JsonProcessingException;

    void fecharCarrinho() throws JsonProcessingException;
}
