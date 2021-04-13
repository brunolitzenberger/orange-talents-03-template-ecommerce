INSERT INTO USUARIO(login, senha, instante) VALUES ('bruno@zup.com.br', '$2a$10$PuMPKe2MBpRpdbnxpaL9peClEfHOFSuYCatRCVNpMVODQc8BlNXL2', '2021-04-05T13:50:41');
INSERT INTO USUARIO(login, senha, instante) VALUES ('marina@zup.com.br', '$2a$10$PuMPKe2MBpRpdbnxpaL9peClEfHOFSuYCatRCVNpMVODQc8BlNXL2', '2021-04-05T13:50:41');
INSERT INTO CATEGORIA(nome) VALUES ('Tecnologia');

INSERT INTO PRODUTO(descricao, nome, quantidade, valor, categoria_id, usuario_id) VALUES('Um produto qualquer', 'Celular', 100, 100.0, 1, 1);
INSERT INTO COMPRA(quantidade, status_compra, valor, comprador_id, produto_id) VALUES(10, 'INICIADO', 100.0, 2, 1);