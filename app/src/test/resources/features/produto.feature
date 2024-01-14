# language: pt

Funcionalidade: Operações do ProdutoController

Contexto: Dado que o serviço de produtos está em execução

Cenário:Cadastrar um novo produto
  Quando eu enviar uma requisição para cadastrar um produto
  Então a resposta deve conter status 201

Cenário: Excessão ao tentar cadastar um produto com o campo invalido
  Quando eu enviar uma requisição para cadastrar um produto com um campo invalido
  Então a resposta deve retornar uma mensagem de erro: "Todos os campos são obrigatórios!" com status 400


  Cenário: Remover um produto existente
  Quando eu enviar esse id de produto, com valor: 1, devo realizar uma requisição
  Então a resposta deve conter status 200

Cenário: Excessão ao tentar remover um produto não existente
  Quando eu enviar esse id de produto, com valor: 3000, devo realizar uma requisição
  Então  a resposta deve retornar uma mensagem de erro: "Id Produto não encontrado!" com status 400

  Cenário: Atualizar um produto existente
  Quando eu enviar uma requisição para atualizar o produto com o id 1
  Então a resposta deve conter status 200
  E o retorno da atualizacao do produto deve conter a seguinte estrutura '{"nome":"Pepsi","preco":5.0,"uriImagem":"http://www","categoria":"bebida","descricao":"gosto de Coca"}'

  Cenário: Excessão ao tentar atualizar um produto não existente
  Quando eu enviar uma requisição para atualizar o produto com o id 6000
  Então a resposta deve retornar uma mensagem de erro: "Id Produto não encontrado!" com status 400

Cenário: Buscar produtos por categoria
  Quando eu enviar uma requisição para buscar produtos por categoria "bebida"
  Então a resposta deve conter uma lista de produtos na categoria "bebida" e conter status 200

Cenário: Execessão ao buscar produtos por categoria inválida
  Quando eu enviar uma requisição para buscar produtos por categoria "drinks"
  Então  a resposta deve retornar uma mensagem de erro: "Categoria não existente!" com status 400