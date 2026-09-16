package com.br.test.f1rst.funcionario.api.response;

import com.br.test.f1rst.funcionario.domain.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnderecoResponse {

    private String logradouro;
    private String numero;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;


    public EnderecoResponse(Endereco endereco) {
        this.bairro = endereco.getBairro();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.complemento = endereco.getComplemento();
        this.estado = endereco.getEstado();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
    }
}
