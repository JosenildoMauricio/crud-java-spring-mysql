set foreign_key_checks = 0;

delete from produto;
delete from categoria;

set foreign_key_checks = 1;

alter table produto auto_increment = 1;
alter table categoria auto_increment = 1;

insert into categoria (nome, data_cadastro, data_atualizacao) values ('Alimenticios', utc_timestamp, utc_timestamp);
insert into produto (nome, valor, unidade_medida, categoria_id, data_cadastro, data_atualizacao) values ('Tomate', 7.48, 'KILOGRAMA', 1, utc_timestamp, utc_timestamp);
insert into produto (nome, valor, unidade_medida, categoria_id, data_cadastro, data_atualizacao) values ('Laranja', 3.92, 'KILOGRAMA', 1, utc_timestamp, utc_timestamp);
insert into produto (nome, valor, unidade_medida, categoria_id, data_cadastro, data_atualizacao) values ('Biscoito', 3.92, 'PACOTE', 1, utc_timestamp, utc_timestamp);




