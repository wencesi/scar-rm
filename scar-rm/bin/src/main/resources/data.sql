-- informe 1
insert into informe (id, codigo, nombre, descripcion) values (1000, 'inf-001','Informe de Grau primer semestre',
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.');
	insert into grupo (id, informe_id,codigo, nombre, orden, descripcion) values (1000, 1000, 'grp-001','Grupo 1 inf 1', 10, 
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.');
		insert into consulta (id, grupo_id,codigo, nombre, orden, descripcion,sql) 
		values (1000, 1000, 'qry-001','Consulta 1 grp 1',10,
		'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.',
		'select * from prueba where tipo=''#1''');
			insert into columna(id,consulta_id,sql_name, header,orden) values (10, 1000, 'id', 'A', 10);
			insert into columna(id,consulta_id,sql_name, header,orden) values (11, 1000, 'fecha', 'B', 20);	
			insert into columna(id,consulta_id,sql_name, header,orden) values (12, 1000, 'tipo', 'C', 30);			
			insert into columna(id,consulta_id,sql_name, header,orden) values (13, 1000, 'valor', 'D', 40);	
			
			insert into parametro(id,consulta_id,codigo, valor,orden) values (10, 1000, '#1', 'tipo1', 1);
			
		insert into consulta (id, grupo_id,codigo, nombre, orden, descripcion,sql) 
		values (1001, 1000, 'qry-002','Consulta 2 grp 1',20,
		'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.',
		'select * from prueba');
			insert into columna(id,consulta_id,sql_name, header,orden) values (14, 1001, 'id', 'AX', 10);
			insert into columna(id,consulta_id,sql_name, header,orden) values (15, 1001, 'fecha', 'BX', 20);	
			insert into columna(id,consulta_id,sql_name, header,orden) values (16, 1001, 'tipo', 'CX', 30);			
			insert into columna(id,consulta_id,sql_name, header,orden) values (17, 1001, 'valor', 'DX', 40);	

insert into grupo (id, informe_id,codigo, nombre, orden, descripcion) values (1001, 1000, 'grp-002','Grupo 2 inf 1', 20, 
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.');
	insert into consulta (id, grupo_id,codigo, nombre, orden, descripcion,sql) 
	values (1002, 1001, 'qry-003','Consulta 2 grp 1',20,
	'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.',
		'select * from prueba where tipo=''#1''');
			insert into columna(id,consulta_id,sql_name, header,orden) values (18, 1002, 'id', 'Ar', 10);
			insert into columna(id,consulta_id,sql_name, header,orden) values (19, 1002, 'fecha', 'Br', 20);	
			insert into columna(id,consulta_id,sql_name, header,orden) values (20, 1002, 'tipo', 'Cr', 30);			
			insert into columna(id,consulta_id,sql_name, header,orden) values (21, 1002, 'valor', 'Dr',40);
			insert into columna(id,consulta_id,sql_name, header,orden) values (22, 1002, 'texto', 'Er', 50);
			
			
			insert into parametro(id,consulta_id,codigo, valor,orden) values (20, 1002, '#1', 'tipo8', 1);			
			
			
-- informe 2
insert into informe (id, codigo, nombre, descripcion) values (1001, 'inf-002','Informe de Grau segon semestre',
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.');

-- informe 3
insert into informe (id, codigo, nombre, descripcion) values (1002, 'inf-003','Informe de Grau tercer semestre',
'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean molestie ultricies ligula eu volutpat. Morbi dolor leo, placerat ut magna sit amet, pellentesque aliquam nibh. Mauris tempor, nisl eget volutpat dapibus, massa mi lacinia mauris, vel ultrices eros sem ac tortor. Duis venenatis viverra sapien efficitur venenatis.');


-- pruebas

insert into prueba (id,fecha,tipo,valor,texto) values (1,current_date, 'tipo1',5,'aaa');
insert into prueba (id,fecha,tipo,valor,texto) values (2,current_date, 'tipo2',5.35,'bbb');
insert into prueba (id,fecha,tipo,valor,texto) values (3,current_date, 'tipo3',5.365,'ccc');
insert into prueba (id,fecha,tipo,valor,texto) values (4,current_date, 'tipo1',6,'ddd');
insert into prueba (id,fecha,tipo,valor,texto) values (5,current_date, 'tipo1',7,'eee');
insert into prueba (id,fecha,tipo,valor,texto) values (6,current_date, 'tipo8',9,'eee');
