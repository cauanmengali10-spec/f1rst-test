package com.br.test.f1rst.funcionario.api.request;


import com.br.test.f1rst.funcionario.api.response.EnderecoResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioUpdateRequest {

    @NotBlank(message = "Nome é obrigatório") @Size(max = 255)
    private String nome;

    @NotBlank(message = "Cargo é obrigatório")
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
