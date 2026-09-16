package com.br.test.f1rst.funcionario.api.response;

import com.br.test.f1rst.funcionario.domain.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class FuncionarioResponse {

    private UUID idFuncionario;
    private String nome;
    private String cargo;
    private BigDecimal salario;
    private String numeroTelefone;
    private EnderecoResponse endereco;

    public FuncionarioResponse(Funcionario funcionario) {
        this.idFuncionario = funcionario.getIdFuncionario();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
        this.salario = funcionario.getSalario();
        this.numeroTelefone = funcionario.getNumeroTelefone();
        this.endereco = funcionario.getEndereco() != null ? new EnderecoResponse(funcionario.getEndereco()) : null;
    }
}
