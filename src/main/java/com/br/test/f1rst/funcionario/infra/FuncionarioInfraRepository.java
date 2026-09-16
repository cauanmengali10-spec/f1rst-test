package com.br.test.f1rst.funcionario.infra;

import com.br.test.f1rst.funcionario.domain.Funcionario;
import com.br.test.f1rst.funcionario.api.repository.FuncionarioRepository;
import com.br.test.f1rst.handler.APIException;
import com.br.test.f1rst.handler.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class FuncionarioInfraRepository implements FuncionarioRepository {
    private final FuncionarioJPAReporsitory funcionarioJPAReporsitory;

    @Override
    public Funcionario salvar(Funcionario funcionario) {
        log.debug("[start] FuncionarioInfraRepository - salvarFuncionario");
        Funcionario funcionarioSalvo = funcionarioJPAReporsitory.save(funcionario);
        log.debug("[finish] FuncionarioInfraRepository - salvarFuncionario");
        return funcionarioSalvo;
    }

    @Override
    public Page<Funcionario> obterTodos(Pageable pageable) {
        log.debug("[start] FuncionarioInfraRepository - obterTodos");
        Page<Funcionario> funcionarios = funcionarioJPAReporsitory.obterTodos(pageable);
        log.debug("[finish] FuncionarioInfraRepository - obterTodos");
        return funcionarios;
    }

    @Override
    public Funcionario obterFuncionarioPorId(UUID idFuncionario) {
        log.debug("[start] FuncionarioInfraRepository - obterPorId");
        Funcionario funcionario = funcionarioJPAReporsitory
                .findById(idFuncionario)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, ErrorCode.FUNCIONARIO_NAO_ENCONTRADO));
        log.debug("[finish] FuncionarioInfraRepository - obterPorId");
        return funcionario;
    }

    @Override
    public void deletarFuncionario(UUID idFuncionario) {
        log.debug("[start] FuncionarioInfraRepository - deletarFuncionario");
        Funcionario funcionario = funcionarioJPAReporsitory.findById(idFuncionario)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, ErrorCode.FUNCIONARIO_NAO_ENCONTRADO));
        funcionarioJPAReporsitory.delete(funcionario);
        log.debug("[finish] FuncionarioInfraRepository - deletarFuncionario");

    }
}
