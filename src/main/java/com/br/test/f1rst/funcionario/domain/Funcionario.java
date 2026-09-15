package com.br.test.f1rst.funcionario.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "funcionario")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFuncionario;

    @Column(nullable = false, length = 100)
    @NotNull
    private String nome;

    @Column(nullable = false, length = 100)
    private String cargo;

    @Column(nullable = false, length = 50)
    private BigDecimal salario;

    @Column(nullable = false, length = 15)
    private String numeroTelefone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Endereco endereço;

}
