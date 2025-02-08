
CREATE DATABASE IF NOT EXISTS Projeto_Crud;
USE Projeto_Crud;


CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL
);


CREATE TABLE IF NOT EXISTS genero (
    id_genero INT AUTO_INCREMENT PRIMARY KEY,
    genero_nome VARCHAR(50) NOT NULL,
    STATUS BIT DEFAULT 1
);


CREATE TABLE IF NOT EXISTS livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editora VARCHAR(255),
    ano_publicacao YEAR,
    isbn VARCHAR(20),
    num_paginas INT,
    sinopse TEXT,
    idioma VARCHAR(50),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    preco DECIMAL(10, 2),
    genero_id_genero INT,
    FOREIGN KEY (genero_id_genero) REFERENCES genero(id_genero) ON DELETE SET NULL ON UPDATE CASCADE
);


INSERT INTO genero (genero_nome, STATUS) VALUES 
('Aventura', 1),
('Biografia', 1),
('Ficção Científica', 1),
('Fantasia', 1),
('Romance', 1);


INSERT INTO usuario (login, senha) VALUES 
('admin', 'admin123'),
('leitor', 'leitor2024'),
('editor', 'editor2024');


INSERT INTO livro (titulo, autor, editora, ano_publicacao, isbn, num_paginas, sinopse, idioma, preco, genero_id_genero) VALUES 
('1984', 'George Orwell', 'Companhia das Letras', '1949', '9788535902772', 328, 'Uma distopia sobre vigilância e controle.', 'Português', 49.90, 3),
('O Hobbit', 'J.R.R. Tolkien', 'HarperCollins', '1937', '9788595084742', 310, 'A jornada de Bilbo Bolseiro pela Terra Média.', 'Português', 59.90, 4),
('Cem Anos de Solidão', 'Gabriel García Márquez', 'Record', '1967', '9788501016074', 432, 'A história da família Buendía em Macondo.', 'Português', 69.90, 5);
