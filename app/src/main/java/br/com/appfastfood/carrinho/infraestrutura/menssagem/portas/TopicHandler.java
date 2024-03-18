package br.com.appfastfood.carrinho.infraestrutura.menssagem.portas;

import com.amazonaws.services.sns.message.SnsMessage;

public interface TopicHandler {
    void publish(String message, String topicAddress);
}