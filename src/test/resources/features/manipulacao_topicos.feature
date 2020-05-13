#language:pt
  Funcionalidade: Controle de Manipulação para Tópicos

    Cenario: Listagem de topicos
      Dado que exista o topico "musicas" criado
      Quando o usuario solicitar pelos topicos
      Entao o topico "musicas" devera ser apresentado

    Cenario: Criacao de um Topico
      Dado que o solicite a criacao de um topico "musicas"
      Quando executar a criacao
      Entao o topico "musicas" devera ser criado

    Cenario: Deletar um topico
      Dado que exista o topico "musicas" com o id "0001"
      Quando solicicar a remocao
      Entao o topico "musicas" com o id "0001" foi removido
