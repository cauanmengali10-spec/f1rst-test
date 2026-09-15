package com.br.test.f1rst.handler;

public enum ErrorCode {
  FUNCIONARIO_NAO_ENCONTRADO("funcionario.nao.encontrado"),
  FUNCIONARIO_JA_EXISTE("funcionario.ja.existe"),
  FUNCIONARIO_INVALIDO("funcionario.invalido"),
  FUNCIONARIO_NOME_INVALIDO("funcionario.nome.invalido"),
  FUNCIONARIO_CARGO_INVALIDO("funcionario.cargo.invalido"),
  FUNCIONARIO_SALARIO_INVALIDO("funcionario.salario.invalido"),
  FUNCIONARIO_TELEFONE_INVALIDO("funcionario.telefone.invalido"),
  ENDERECO_NAO_ENCONTRADO("endereco.nao.encontrado"),
  ENDERECO_JA_EXISTE("endereco.ja.existe"),
  ENDERECO_INVALIDO("endereco.invalido"),
  ENDERECO_CEP_INVALIDO("endereco.cep.invalido"),
  ENDERECO_ESTADO_INVALIDO("endereco.estado.invalido"),
  ERRO_INTERNO("erro.interno"),
  REQUEST_CORPO_INVALIDO("request.corpo.invalido"),
  REQUEST_PARAMETRO_AUSENTE("request.parametro.ausente"),
  REQUEST_PARAMETRO_INVALIDO("request.parametro.invalido"),
  REQUEST_METODO_NAO_SUPORTADO("request.metodo.nao.suportado"),
  REQUEST_MEDIA_TYPE_NAO_SUPORTADO("request.media.type.nao.suportado"),
  CONSTRAINT_VIOLATION("constraint.violation"),
  INTEGRIDADE_DADOS("integridade.dados"),
  RECURSO_NAO_ENCONTRADO("recurso.nao.encontrado");

  private final String code;

  ErrorCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}