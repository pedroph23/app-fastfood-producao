package br.com.appfastfood.configuracoes;

import br.com.appfastfood.AppFastfoodApplication;
import br.com.appfastfood.carrinho.dominio.repositorios.CarrinhoRepositorio;
import br.com.appfastfood.carrinho.usecase.adaptadores.CarrinhoServicoImpl;
import br.com.appfastfood.carrinho.usecase.portas.CarrinhoServico;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.configuracoes.logs.Log4jLog;
import br.com.appfastfood.produto.dominio.repositorios.ProdutoRepositorio;
import br.com.appfastfood.produto.usecase.adaptadores.ProdutoServicoImpl;
import br.com.appfastfood.produto.usecase.portas.ProdutoServico;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppFastfoodApplication.class)
public class BeanConfiguration {


    @Bean
    ProdutoServico produtoServico(ProdutoRepositorio produtoRepositorio){
        return new ProdutoServicoImpl(produtoRepositorio);
    }
    @Bean
    CarrinhoServico carrinhoServico(CarrinhoRepositorio carrinhoRepositorio, ProdutoServico produtoServico){
        return new CarrinhoServicoImpl(carrinhoRepositorio, produtoServico);
    }


    @Bean
    Log log(){
        return new Log4jLog(BeanConfiguration.class);
    }
}
