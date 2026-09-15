package com.br.test.f1rst.funcionario.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "endereco")
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 150)
    @Size(max = 150)
    private String logradouro;

    @Column(length = 20)
    @Size(max = 20)
    private String numero;

    @Column(length = 100)
    @Size(max = 100)
    private String complemento;

    @Column(length = 100)
    @Size(max = 100)
    private String bairro;

    @Column(length = 100)
    @Size(max = 100)
    private String cidade;

    @Column(length = 50)
    @Size(max = 50)
    private String estado;

    @Column(length = 20)
    @Size(max = 20)
    private String cep;

    @OneToOne(mappedBy = "endereco")
    private Funcionario funcionario;

}
