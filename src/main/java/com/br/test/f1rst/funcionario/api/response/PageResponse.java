package com.br.test.f1rst.funcionario.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private Long totalElements;
    private int paginaAtual;
    private int totalPaginas;
    private long totalFuncionarios;

    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<> (
                page.getContent(),
                page.getTotalElements(),
                page.getNumber(),
                page.getTotalPages(),
                page.getNumberOfElements());
    }
}