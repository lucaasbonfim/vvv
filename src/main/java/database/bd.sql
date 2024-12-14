CREATE TABLE passageiro (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL
);

CREATE TABLE modal (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(50) NOT NULL,
    capacidade INT NOT NULL,
    ano_fabricacao VARCHAR(4) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    ativo BOOLEAN DEFAULT FALSE
);

CREATE TABLE ponto_venda (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(50) NOT NULL,
    localizacao VARCHAR(100) NOT NULL
);

CREATE TABLE funcionario (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    tipo BOOLEAN DEFAULT FALSE,
    id_ponto_venda BIGINT UNSIGNED, 
    CONSTRAINT fk_funcionario_ponto_venda FOREIGN KEY (id_ponto_venda) REFERENCES ponto_venda(id)
);

CREATE TABLE reserva (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    data_reserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status_reserva BOOLEAN DEFAULT FALSE,
    data_viagem DATE NOT NULL,
    partida VARCHAR(50) NOT NULL,
    chegada VARCHAR(50) NOT NULL,
    valor NUMERIC(5,2) NOT NULL,
    id_passageiro BIGINT UNSIGNED, 
    id_modal BIGINT UNSIGNED, 
    id_ponto_venda BIGINT UNSIGNED, 
    id_funcionario BIGINT UNSIGNED,
    CONSTRAINT fk_passageiro FOREIGN KEY (id_passageiro) REFERENCES passageiro(id),
    CONSTRAINT fk_modal FOREIGN KEY (id_modal) REFERENCES modal(id),
    CONSTRAINT fk_ponto_venda FOREIGN KEY (id_ponto_venda) REFERENCES ponto_venda(id),
    CONSTRAINT fk_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
);