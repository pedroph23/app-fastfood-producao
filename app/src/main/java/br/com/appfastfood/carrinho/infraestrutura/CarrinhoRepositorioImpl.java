package br.com.appfastfood.carrinho.infraestrutura;


import br.com.appfastfood.carrinho.dominio.modelos.Carrinho;
import br.com.appfastfood.carrinho.dominio.modelos.Cliente;
import br.com.appfastfood.carrinho.dominio.modelos.enums.StatusCarrinhoEnum;
import br.com.appfastfood.carrinho.dominio.repositorios.CarrinhoRepositorio;
import br.com.appfastfood.carrinho.dominio.vo.ProdutoVO;
import br.com.appfastfood.carrinho.exceptions.ExceptionsMessages;
import br.com.appfastfood.carrinho.infraestrutura.entidades.CarrinhoEntidade;
import br.com.appfastfood.carrinho.infraestrutura.entidades.ProdEnt;
import br.com.appfastfood.configuracoes.execption.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarrinhoRepositorioImpl implements CarrinhoRepositorio {
    private final SpringDataCarrinhoRepository springDataCarrinhoRepository;
    public CarrinhoRepositorioImpl(SpringDataCarrinhoRepository springDataCarrinhoRepository) {
        this.springDataCarrinhoRepository = springDataCarrinhoRepository;
    }

    @Override
    public void criar(Carrinho carrinho) {
        Double valorTotal = 0D;
        List<ProdEnt> produtosEntidade = new ArrayList<>();

        carrinho.getProdutoVOS().forEach(produto -> {
            produtosEntidade.add(new ProdEnt(produto.getIdProduto(), produto.getQuantidadeProduto()));
        });

        CarrinhoEntidade carrinhoEntidade = new CarrinhoEntidade(produtosEntidade, carrinho.getCliente().getCliente(), carrinho.getValorTotal(), StatusCarrinhoEnum.retornaNomeEnum(carrinho.getStatus()));

        springDataCarrinhoRepository.save(carrinhoEntidade);
    }

    @Override
    public Carrinho buscarPorId(Long id) {
        List<ProdutoVO> produtos = new ArrayList<>();

        Optional<CarrinhoEntidade> carrinhoEntidade = this.springDataCarrinhoRepository.findById(id);

        if (!carrinhoEntidade.isPresent()){
            throw new BadRequestException(ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());
        }

        CarrinhoEntidade carrinhoEntidadeResult = carrinhoEntidade.get();

        List<ProdEnt> produtosEnt = carrinhoEntidadeResult.getProdutos();

        produtosEnt.forEach(prodEnt -> {
            ProdutoVO produtoVO = new ProdutoVO(prodEnt.getIdProduto(), prodEnt.getQuantidadeProduto());
            produtos.add(produtoVO);
        });

        Carrinho carrinho = new Carrinho(
                produtos,
                new Cliente(carrinhoEntidadeResult.getClienteId()),
                carrinhoEntidadeResult.getValorTotal(),
                StatusCarrinhoEnum.buscaEnumPorStatusString(carrinhoEntidadeResult.getStatus())
        );
        return carrinho;
    }

    @Override
    public void remover(Long id) {
        this.springDataCarrinhoRepository.deleteById(id);
    }

    @Override
    public List<CarrinhoEntidade> listar() {
        return this.springDataCarrinhoRepository.findAll();
    }

    @Override
    public Carrinho atualizar(Long id, Carrinho carrinho) {

       Optional<CarrinhoEntidade> carrinhoOptionalEnt = this.springDataCarrinhoRepository.findById(id);

       if (!carrinhoOptionalEnt.isEmpty()) {

           CarrinhoEntidade carrinhoEnt = carrinhoOptionalEnt.get();

           List<ProdEnt> prodEnts = carrinho.getProdutoVOS()
                   .stream()
                   .map(produtoVO -> new ProdEnt(produtoVO.getIdProduto(), produtoVO.getQuantidadeProduto()))
                   .toList();

           CarrinhoEntidade carrinhoEntidade = new CarrinhoEntidade(id, prodEnts, carrinho.getCliente().getCliente(), carrinho.getValorTotal(), carrinhoEnt.getStatus());

           this.springDataCarrinhoRepository.save(carrinhoEntidade);
           return carrinho;
       }

        throw new BadRequestException(ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());
    }

    @Override
    public void fecharCarrinho(Long id) {
        if(this.springDataCarrinhoRepository.existsById(id)) {
           CarrinhoEntidade entidade = this.springDataCarrinhoRepository.findById(id).get();
           entidade.setStatus(StatusCarrinhoEnum.FECHADO.getNome());
           this.springDataCarrinhoRepository.save(entidade);
        } else {
            throw new BadRequestException(ExceptionsMessages.ID_NAO_ENCONTRADO.getValue());
        }
    }

}
