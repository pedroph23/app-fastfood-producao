package br.com.appfastfood.carrinho.usecase.adaptadores;

import br.com.appfastfood.carrinho.infraestrutura.menssagem.portas.TopicHandler;
import com.amazonaws.services.sns.message.SnsMessage;

public class CarrinhoCriadoTopicHandler implements TopicHandler {

    @Override
    public void publish(String message, String topicAddress) {

    }
}
