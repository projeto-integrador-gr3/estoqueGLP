CREATE DATABASE gestao_estoque_glp;

USE gestao_estoque_glp;

-- Tabela de usuarios
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL
);

-- Tabela de produtos
CREATE TABLE produto (
    id_produto INT AUTO_INCREMENT PRIMARY KEY,
    id_fornecedor INT FOREIGN KEY,
    nome_produto VARCHAR(100) NOT NULL,
);

-- Tabela de fornecedores
CREATE TABLE fornecedores (
    id_fornecedor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

--Tabela para controle de estoque
CREATE TABLE controle_estoque (
    id_transacao INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT,
    id_usuario INT,
    id_fornecedor INT,
    qtd_entrada INT,
    qtd_vendas INT,
    qtd_estoque INT,
    data_transacao DATE NOT NULL,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id_fornecedor)
);
