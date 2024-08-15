Manutenção de Filmes
  Criar um microserviço java spring boot para manutenção de uma base de dados sobre filmes.
 
Campos:
  Título (campo obrigatório)
  Diretor (campo obrigatório)
  Gênero (campo obrigatório)
  Sinopse
  Data Lançamento
 
 
Métodos a serem implementados:
  Criação - Cria o registro na base de dados quando não houver outro registro com o mesmo Título
  Edição - É possível alterar qualquer campo com exceção do campo Título
  Exclusão - Deve ser feita a exclusão lógica, o registro permanece na base de dados "desativado"
  Consulta - Busca por registros ativos onde o título contenha a palavra informada como parâmetro
  Listagem - Lista todos os registros ativos da base aplicando filtros não obrigatórios por Título, Diretor, Gênero e Ano Lançamento.
 
 
Pode utilizar a base de dados H2 por exemplo
Os métodos serão expostos em formato REST
