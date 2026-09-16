package com.br.test.f1rst.funcionario.api;

import com.br.test.f1rst.funcionario.api.request.FuncionarioPostRequest;
import com.br.test.f1rst.funcionario.api.request.FuncionarioUpdateRequest;
import com.br.test.f1rst.funcionario.api.response.FuncionarioResponse;
import com.br.test.f1rst.funcionario.api.response.PageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public interface FuncionarioApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    FuncionarioResponse criar(@Valid @RequestBody FuncionarioPostRequest funcionarioRequest);


    @GetMapping("/funcionarios")
    PageResponse<FuncionarioResponse> obterTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    );

    @PatchMapping("/{idFuncionario}")
    FuncionarioResponse atualizarFuncionario
            (@PathVariable UUID idFuncionario,
             @Valid @RequestBody FuncionarioUpdateRequest updateRequest);


    @DeleteMapping("/{idFuncionario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletarFuncionario(@PathVariable UUID idFuncionario);

}
