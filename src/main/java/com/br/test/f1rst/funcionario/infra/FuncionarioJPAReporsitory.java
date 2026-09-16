package com.br.test.f1rst.funcionario.infra;

import com.br.test.f1rst.funcionario.domain.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface FuncionarioJPAReporsitory extends JpaRepository<Funcionario, UUID> {

    @EntityGraph(attributePaths = "endereco")
    @Query(value = "SELECT f FROM Funcionario f ORDER BY f.nome ASC",
            countQuery = "SELECT count(distinct f) FROM Funcionario f")
    Page<Funcionario> obterTodos(Pageable pageable);
}
