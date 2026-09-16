CREATE TABLE f1rst.endereco (
    id UUID NOT NULL,
    logradouro VARCHAR(150),
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep VARCHAR(20),
    CONSTRAINT pk_endereco PRIMARY KEY (id)
);