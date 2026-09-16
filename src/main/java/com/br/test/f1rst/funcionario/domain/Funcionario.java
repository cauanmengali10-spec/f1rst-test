package com.br.test.f1rst.funcionario.domain;

import com.br.test.f1rst.funcionario.api.request.EnderecoRequest;
import com.br.test.f1rst.funcionario.api.request.FuncionarioPostRequest;
import com.br.test.f1rst.funcionario.api.request.FuncionarioUpdateRequest;
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

    @Column(nullable = false, length = 50,precision = 17, scale = 2)
    private BigDecimal salario;

    @Column(nullable = false, length = 15)
    private String numeroTelefone;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    public Funcionario(FuncionarioPostRequest request) {
        this.nome = request.getNome();
        this.cargo = request.getCargo();
        this.salario = request.getSalario();
        this.numeroTelefone = request.getNumeroTelefone();
        this.endereco = new Endereco(request.getEndereco());


    }

    public void atualizaFuncionario(FuncionarioUpdateRequest updateRequest) {
        this.nome = updateRequest.getNome();
        this.cargo = updateRequest.getCargo();
        this.salario = updateRequest.getSalario();
        this.numeroTelefone = updateRequest.getNumeroTelefone();
        if (this.endereco == null) {
            this.endereco = new Endereco(updateRequest.getEndereco());
        } else {
            this.endereco.setLogradouro(updateRequest.getEndereco().getLogradouro());
            this.endereco.setNumero(updateRequest.getEndereco().getNumero());
            this.endereco.setComplemento(updateRequest.getEndereco().getComplemento());
            this.endereco.setBairro(updateRequest.getEndereco().getBairro());
            this.endereco.setCidade(updateRequest.getEndereco().getCidade());
            this.endereco.setEstado(updateRequest.getEndereco().getEstado());
            this.endereco.setCep(updateRequest.getEndereco().getCep());
        }
    }
}
