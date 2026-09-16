package com.br.test.f1rst.funcionario.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequest {

    @NotBlank(message = "O campo logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "O campo numero é obrigatório")
    private String numero;

    private String complemento;

    @NotBlank(message = "O campo bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "O campo cidade é obrigatório")
    private String cidade;

    @NotBlank(message = "O campo estado é obrigatório")
    private String estado;

    @NotBlank(message = "O campo cep é obrigatório")
    private String cep;


}
