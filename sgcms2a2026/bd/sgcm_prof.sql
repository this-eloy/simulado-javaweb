drop database sgcm_prof;

create database sgcm_prof;
use sgcm_prof;

create table tipo_usuario (	
	id int not null,
    modulo_administrativo varchar(1) not null,
    modulo_agendamento varchar(1) not null,
    modulo_atendimento varchar(1) not null,
    primary key (id)
);

create table convenio (	
	id int not null,
    nome varchar(45) not null,
    cnpj varchar(45),
    telefone varchar(20),
    valor float not null,
    primary key (id)
);

create table usuario (	
	id int not null,
    nome varchar(50),
    senha varchar(100) not null,
    tipo_usuario_id int not null,
    nascimento date,
    logradouro varchar(100),
    numero varchar(10),
    bairro varchar(100),
    cidade varchar(100),
    estado varchar(50),
    uf varchar(2),
    cep varchar(10),
    convenio_id int,
    primary key (id),
    foreign key (tipo_usuario_id) references tipo_usuario(id),
    foreign key (convenio_id) references convenio(id)
);

insert into tipo_usuario values ( 1024, 'S',  'S',  'S' );
insert into usuario ( id, nome, senha, tipo_usuario_id ) values ( 1024, 'Admin',  '192ad82b61931a696f246ceb68fc37d698506be05a2b296b65ddd82492c9411b', 1024 );
