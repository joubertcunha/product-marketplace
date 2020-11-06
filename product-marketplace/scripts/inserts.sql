use marketplace;

insert into comprador values (1, 'jose');
insert into vendedor values (1, 'maria');

insert into categoria_produto values(1, 'esporte');
insert into categoria_produto values(2, 'tecnologia');
insert into categoria_produto values(3, 'musical');

insert into produto (id_produto, created_by, creation_date, last_modified_by, data_cadastro, descricao, nome, valor_produto,
id_categoria_produto) VALUES (1, 'script', now(), 'script', now(), 'bola de futebol', 'bola', 50.00, 1);
insert into produto (id_produto, created_by, creation_date, last_modified_by, data_cadastro, descricao, nome, valor_produto,
id_categoria_produto) VALUES (2, 'script', now(), 'script', now(), 'bola de basquete', 'bola basquete', 10.00, 1);
insert into produto (id_produto, created_by, creation_date, last_modified_by, data_cadastro, descricao, nome, valor_produto,
id_categoria_produto) VALUES (3, 'script', now(), 'script', now(), 'peteca', 'peteca', 5.00, 1);

insert into produto (id_produto, created_by, creation_date, last_modified_by, data_cadastro, descricao, nome, valor_produto,
id_categoria_produto) VALUES (4, 'script', now(), 'script', now(), 'teclado', 'teclado', 30.00, 2);
insert into produto (id_produto, created_by, creation_date, last_modified_by, data_cadastro, descricao, nome, valor_produto,
id_categoria_produto) VALUES (5, 'script', now(), 'script', now(), 'notebook', 'notebook', 2000.00, 2);
insert into produto (id_produto, created_by, creation_date, last_modified_by, data_cadastro, descricao, nome, valor_produto,
id_categoria_produto) VALUES (6, 'script', now(), 'script', now(), 'mouse', 'mouse', 2.00, 2);

insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (1, 5, 1, 10.00, sysdate(), 1, 1, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (2, 3, 2, 20.00, sysdate(), 1, 2, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (3, 3, 2, 30.00, sysdate(), 1, 3, 1);


insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (4, 2, 1, 10.00, sysdate(), 1, 4, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (5, 3, 2, 20.00, sysdate(), 1, 5, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (6, 2, 2, 30.00, sysdate(), 1, 6, 1);

insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (7, 5, 1, 10.00, sysdate(), 1, 1, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (8, 5, 2, 20.00, sysdate(), 1, 2, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (9, 5, 2, 30.00, sysdate(), 1, 3, 1);

insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (10, 3, 1, 10.00, sysdate(), 1, 1, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (11, 3, 2, 20.00, sysdate(), 1, 2, 1);
insert into venda (id_venda, avaliacao, quantidade, valor_total, data_venda, id_comprador, id_produto, id_vendedor)
values (12, 3, 2, 30.00, sysdate(), 1, 3, 1);