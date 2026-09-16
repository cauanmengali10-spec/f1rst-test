package com.br.test.f1rst.funcionario.api;

import com.br.test.f1rst.funcionario.api.request.FuncionarioPostRequest;
import com.br.test.f1rst.funcionario.api.request.FuncionarioUpdateRequest;
import com.br.test.f1rst.funcionario.api.response.FuncionarioResponse;
import com.br.test.f1rst.funcionario.api.response.PageResponse;
import com.br.test.f1rst.funcionario.api.service.FuncionarioService;
import com.br.test.f1rst.funcionario.domain.Funcionario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@Log4j2
public class FuncionarioController implements FuncionarioApi {
    private final FuncionarioService funcionarioService;

    @Override
    public FuncionarioResponse criar(FuncionarioPostRequest funcionarioRequest) {
        log.debug("[start] FuncionarioController - criaBeneficiario");
        return funcionarioService.criaFuncionario(funcionarioRequest);
    }

    @Override
    public PageResponse<FuncionarioResponse> obterTodos(int page, int size) {
        log.debug("[start] FuncionarioController - obterTodos");
        Page<Funcionario> funcionarios = funcionarioService.obterTodos(PageRequest.of(page, size));
        Page<FuncionarioResponse> response = funcionarios.map(FuncionarioResponse::new);
        log.debug("[finish] FuncionarioController - obterTodos");
        return PageResponse.from(response);
    }

    @Override
    public FuncionarioResponse atualizarFuncionario(UUID idFuncionario, FuncionarioUpdateRequest updateRequest) {
        log.debug("[start] FuncionarioController - atualizarFuncionario");
        return funcionarioService.atualizarFuncionario(idFuncionario, updateRequest);
    }

    @Override
    public void deletarFuncionario(UUID idFuncionario) {
        log.debug("[start] FuncionarioController - deletarFuncionario");
        funcionarioService.deletarFuncionario(idFuncionario);


    }
}
