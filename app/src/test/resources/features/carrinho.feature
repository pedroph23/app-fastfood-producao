# language: pt

Funcionalidade: Operações do Carrinho

  Cenário: Cadastrar Carrinho
    Quando que eu envio uma requisição para cadastrar um carrinho
    Então a resposta deve ter o status 201

  Cenário: Excessão ao tentar cadastrar carrinho, com id do produto não existente
    Quando que eu envio uma requisição para cadastrar um carrinho, com id do produto não existente
    Então deve retornar uma mensagem de erro: "Id Produto não encontrado!" com status 400


  Cenário: Remover Carrinho
    Quando eu enviar esse id de carrinho, com valor: 1, devo realizar uma requisição de remover carrinho
    Então a resposta deve ter o status 200

  Cenário: Excessão ao remover Carrinho com um id não existente
    Quando eu enviar esse id de carrinho, com valor: 300000, devo realizar uma requisição de remover carrinho
    Então deve retornar uma mensagem de erro: "Id de carrinho não encontrado!" com status 400

  Cenário: Listar Carrinhos
    Quando eu envio uma requisição para listar os carrinhos
    Então a resposta deve ter o status 200
    E a resposta da listagem deve conter a seguinte estrutura '[{"id":1,"produtos":[{"idProduto":"1","quantidadeProduto":"2"}],"idCliente":"1","valorTotal":20.0,"status":"ABERTO"}]'

  Cenário: Atualizar Carrinho
    Dado que exista um carrinho cadastrado, e envio uma requisição para atualizar o carrinho com id 1
    Então a resposta deve ter o status 200
    E o retorno deve conter a seguinte estrutura '{"produtos":[{"idProduto": "2","quantidadeProduto":"2"}],"idCliente":"1","valorTotal":20.0}'

  Cenário: Fechar Carrinho
    Quando eu envio uma requisição para fechar o carrinho com id 1
    Então a resposta deve ter o status 200
