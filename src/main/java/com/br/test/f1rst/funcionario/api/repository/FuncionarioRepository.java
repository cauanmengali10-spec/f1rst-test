package com.br.test.f1rst.funcionario.api.repository;

import com.br.test.f1rst.funcionario.domain.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FuncionarioRepository {
    Funcionario salvar(Funcionario funcionario);

    Page<Funcionario> obterTodos(Pageable pageable);

    Funcionario obterFuncionarioPorId(UUID idFuncionario);

    void deletarFuncionario(UUID idFuncionario);
}
