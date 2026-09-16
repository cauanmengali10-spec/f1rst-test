package com.br.test.f1rst.funcionario.api.service;

import com.br.test.f1rst.funcionario.api.request.FuncionarioPostRequest;
import com.br.test.f1rst.funcionario.api.request.FuncionarioUpdateRequest;
import com.br.test.f1rst.funcionario.api.response.FuncionarioResponse;
import com.br.test.f1rst.funcionario.domain.Funcionario;
import com.br.test.f1rst.funcionario.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class FuncionarioApplicationService implements FuncionarioService{
    private final FuncionarioRepository funcionarioRepository;

    @Override
    @Transactional
    public FuncionarioResponse criaFuncionario(FuncionarioPostRequest request) {
        log.debug("[start] FuncionarioApplicationService - criaFuncionario");
        Funcionario funcionarioSalvo = funcionarioRepository.salvar(new Funcionario(request));
        log.debug("[finish] FuncionarioApplicationService - criaFuncionario");
        return new FuncionarioResponse(funcionarioSalvo);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Funcionario> obterTodos(Pageable pageable) {
        log.debug("[start] FuncionarioApplicationService - obterTodos");
        Page<Funcionario> funcionarios = funcionarioRepository.obterTodos(pageable);
        log.debug("[finish] FuncionarioApplicationService - obterTodos");
        return funcionarios;
    }

    @Override
    @Transactional
    public FuncionarioResponse atualizarFuncionario(UUID idFuncionario, FuncionarioUpdateRequest updateRequest) {
        log.debug("[start] FuncionarioApplicationService - atualizarFuncionario");
        Funcionario funcionario = funcionarioRepository.obterFuncionarioPorId(idFuncionario);
        funcionario.atualizaFuncionario(updateRequest);
        log.debug("[finish] FuncionarioApplicationService - atualizarFuncionario");
        return new FuncionarioResponse(funcionario);
    }

    @Override
    @Transactional
    public void deletarFuncionario(UUID idFuncionario) {
        log.debug("[start] FuncionarioApplicationService - deletarFuncionario");
        funcionarioRepository.deletarFuncionario(idFuncionario);
        log.debug("[finish] FuncionarioApplicationService - deletarFuncionario");

    }
}
