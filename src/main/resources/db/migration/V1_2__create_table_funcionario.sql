CREATE TABLE f1rst.funcionario (
    id_funcionario UUID NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    salario NUMERIC(17, 2) NOT NULL,
    numero_telefone VARCHAR(15) NOT NULL,
    id_endereco UUID,
    CONSTRAINT pk_funcionario PRIMARY KEY (id_funcionario),
    CONSTRAINT fk_funcionario_endereco
        FOREIGN KEY (id_endereco)
        REFERENCES f1rst.endereco (id),
    CONSTRAINT uq_funcionario_endereco UNIQUE (id_endereco)
);