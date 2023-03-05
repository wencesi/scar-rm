drop table if exists planificador;

drop table if exists parametro;
drop table if exists columna;
drop table if exists consulta;
drop table if exists grupo;
drop table if exists informe;

create table informe (
	id integer not null, 
	codigo varchar(50) not null,	
	nombre varchar(150) not null,
	descripcion varchar(500) not null,
	primary key(id)
	);
	
create table grupo (
	id integer not null, 
	informe_id integer not null,
	codigo varchar(50) not null,	
	nombre varchar(150) not null,
	orden integer not null,	
	descripcion varchar(500) not null,	
	primary key(id),
	foreign key (informe_id) references informe(id)
	);
	
create table consulta (
	id integer not null, 
	grupo_id integer not null,
	codigo varchar(50) not null,	
	nombre varchar(150) not null,
	orden integer not null,	
	descripcion varchar(500) not null,	
	sql varchar (500) not null,
	primary key(id),
	foreign key (grupo_id) references grupo(id)
	);	
	
create table columna (
	id integer not null, 
	consulta_id integer not null,
	sql_name varchar(30) not null,
	header varchar(30) not null,	
	orden integer not null,	
	primary key(id),
	foreign key (consulta_id) references consulta(id)
	);
	
create table parametro (
	id integer not null, 
	consulta_id integer not null,
	codigo varchar(30) not null,
	valor varchar(30) not null,	
	orden integer not null,	
	primary key(id),
	foreign key (consulta_id) references consulta(id)
	);	
	
create table planificador (
	id integer not null, 
	fecha_peticion timestamp not null, -- fecha de peticion
	fecha_ejecucion timestamp, -- fecha de ejecucion	
	usuario varchar(50) not null,
	--tipo varchar(3)not null, 
	tipo enum ('inf','grp','qry') not null,
	modo enum ('online','offline'),
	codigo varchar(50) not null,
	estado varchar(10) not null,
	fichero varchar(50),
	primary key(id)
	);

create table prueba (
	id integer not null, 
	fecha timestamp not null,
	tipo varchar(10)not null,
	valor decimal not null,
	texto varchar(100),
	primary key(id)
);