create table categoria (
    id bigint not null auto_increment,
    nome varchar(155) not null,
    data_cadastro timestamp not null,
    data_atualizacao timestamp not null,

    primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table produto (
    id bigint not null auto_increment,
    nome varchar(155) not null,
    valor decimal(9,2) not null,
    unidade_medida varchar(55) not null,
    categoria_id bigint not null,
    data_cadastro timestamp not null,
    data_atualizacao timestamp not null,

    primary key (id),
    constraint fk_produto_categoria foreign key (categoria_id) references categoria (id)
) engine=InnoDB default charset=utf8mb4;