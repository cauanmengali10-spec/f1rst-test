package com.br.test.f1rst.funcionario.api.request;

import com.br.test.f1rst.funcionario.domain.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FuncionarioPostRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 255)
    private String nome;

    @NotBlank(message = "O campo cargo é obrigatório")
    private String cargo;

    @NotNull(message = "O campo salario é obrigatório")
    @DecimalMin(value = "0.0", message = "Salário deve ser maior que zero")
    @Digits(integer = 17, fraction = 2, message = "Salário inválido")
    private BigDecimal salario;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\+?[0-9()\\-\\s]+", message = "Telefone inválido")
    private String numeroTelefone;

    @NotNull(message = "O campo endereço é obrigatório")
    @Valid
    private EnderecoRequest endereco;



}
