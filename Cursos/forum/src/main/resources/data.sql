INSERT INTO USUARIO(NOME, EMAIL, SENHA) VALUES('Aluno', 'aluno@email.com', '123456');

INSERT INTO CURSO(NOME, CATEGORIA) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(NOME, CATEGORIA) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(TITULO, MENSAGEM, DATA_CRIACAO, STATUS, ID_AUTOR, ID_CURSO) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(TITULO, MENSAGEM, DATA_CRIACAO, STATUS, ID_AUTOR, ID_CURSO) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(TITULO, MENSAGEM, DATA_CRIACAO, STATUS, ID_AUTOR, ID_CURSO) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);           