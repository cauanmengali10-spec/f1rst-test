package com.br.test.f1rst.funcionario.api.service;

import com.br.test.f1rst.funcionario.api.request.FuncionarioPostRequest;
import com.br.test.f1rst.funcionario.api.request.FuncionarioUpdateRequest;
import com.br.test.f1rst.funcionario.api.response.FuncionarioResponse;
import com.br.test.f1rst.funcionario.domain.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FuncionarioService {
    FuncionarioResponse criaFuncionario(FuncionarioPostRequest funcionarioRequest);

    Page<Funcionario> obterTodos(Pageable pageable);

    FuncionarioResponse atualizarFuncionario(UUID idFuncionario, FuncionarioUpdateRequest updateRequest);

    void deletarFuncionario(UUID idFuncionario);
}
