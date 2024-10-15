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
 
 
Utilizar a base de dados H2 por exemplo
Os métodos serão expostos em formato REST


Frontend Angular - O frontend interage com o backend de manutenção de filmes, através de HTTP, enviando e resebendo dados em formato JSON e permitindo que os usuários realizem operações como buscar, criar, editar e excluir filmes de um banco de dados.
Componentes: O projeto foi dividido em diferentes componentes, cada um responsável por uma parte da interface do usuário:
Home: Tela principal com botões para acessar diferentes funcionalidades.
Consultar: Permite consultar os filmes apenas por título.
Criar: Formulário para adicionar novos filmes ao banco de dados.
Editar: Permite editar informações de filmes existentes.
Listar: Exibe uma lista de filmes ativos, que pode ser utilizados filtros como título, diretor, gênero e ano de lançamento e também pode fazer a exclusão lógica de um filme.
