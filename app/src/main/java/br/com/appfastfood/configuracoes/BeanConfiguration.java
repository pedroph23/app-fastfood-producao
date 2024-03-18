package br.com.appfastfood.configuracoes;

import br.com.appfastfood.AppFastfoodApplication;
import br.com.appfastfood.carrinho.dominio.repositorios.CarrinhoRepositorio;
import br.com.appfastfood.carrinho.infraestrutura.menssagem.portas.TopicHandler;
import br.com.appfastfood.carrinho.usecase.adaptadores.CarrinhoServicoImpl;
import br.com.appfastfood.carrinho.usecase.adaptadores.SNSTopicHandlerImpl;
import br.com.appfastfood.carrinho.usecase.portas.CarrinhoServico;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.configuracoes.logs.Log4jLog;
import br.com.appfastfood.produto.dominio.repositorios.ProdutoRepositorio;
import br.com.appfastfood.produto.usecase.adaptadores.ProdutoServicoImpl;
import br.com.appfastfood.produto.usecase.portas.ProdutoServico;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
@Configuration
@ComponentScan(basePackageClasses = AppFastfoodApplication.class)
public class BeanConfiguration {

    @Bean
    ProdutoServico produtoServico(ProdutoRepositorio produtoRepositorio){
        return new ProdutoServicoImpl(produtoRepositorio);
    }

    @Bean
    public AmazonSNS amazonSNS() {
        // Configuração básica de um cliente AmazonSNS usando as credenciais padrão do SDK
        return AmazonSNSClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }
    @Bean
    TopicHandler topicHandler(){
        return new SNSTopicHandlerImpl(amazonSNS());
    }


    @Bean
    CarrinhoServico carrinhoServico(CarrinhoRepositorio carrinhoRepositorio, ProdutoServico produtoServico, TopicHandler topicHandler) {
        return new CarrinhoServicoImpl(carrinhoRepositorio, produtoServico,  topicHandler);
    }
//    @Bean
//    CarrinhoFilaConsumerImpl carrinhoConsumerFila(AmazonSNS amazonSNS, String notificationName, NotificationMessagingTemplate notificationMessagingTemplate, AmqpTemplate template) {
//        return new CarrinhoFilaConsumerImpl(amazonSNS,notificationName, notificationMessagingTemplate, template);
//    }

    @Bean
    Log log(){
        return new Log4jLog(BeanConfiguration.class);
    }
}
