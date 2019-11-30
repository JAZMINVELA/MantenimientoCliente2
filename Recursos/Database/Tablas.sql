create table Usuarios(
	usuario varchar(20),
	clave	varchar(20),
	nombres varchar(50),
	apellidos varchar(50),
	primary key(usuario)
);

CREATE TABLE cliente
(
  cli_id int,
  cli_ruc varchar(15),
  cli_raz_social varchar(50),
  cli_direccion varchar(50),
  cli_telefono varchar(10),
  PRIMARY KEY (cli_id)
);



create table Categorias(
	id_categoria int,
	descripcion	 varchar(50),
	primary key(id_categoria)
);

create table Subcategorias(
	id_categoria int,
	id_subcategoria int,
	descripcion	 varchar(50),
	primary key(id_categoria, id_subcategoria),
	foreign key(id_categoria) references Categorias(id_categoria)
);

create table Caracteristicas(
	id_caracteristica int,
	nombre varchar(50),
	primary key (id_caracteristica)
);


create table Subcateg_Caracteristicas(
	id_categoria int,
	id_subcategoria int,
	id_caracteristica int,
	primary key(id_categoria, id_subcategoria, id_caracteristica),
	foreign key(id_categoria, id_subcategoria) 
						references Subcategorias(id_categoria, id_subcategoria),
	foreign key(id_caracteristica) 
						references Caracteristicas(id_caracteristica)
);

create table Subcateg_caracter_valores(
	id_categoria int,
	id_subcategoria int,
	id_caracteristica int,
	id_valor int,
	valor varchar(100),
	primary key(id_categoria, id_subcategoria, id_caracteristica, id_valor),
	foreign key(id_categoria, id_subcategoria, id_caracteristica) 
					references Subcateg_Caracteristicas(id_categoria, id_subcategoria, 
															id_caracteristica)
);

create table Monedas(
	id_moneda 	int,
	simbolo		varchar(10),
	nombre		varchar(50),
	primary key(id_moneda)
);

create table Productos(
	id_producto int,
	codigo varchar(30),
	nombre varchar(100),
	descripcion varchar(250),
	id_moneda int,
	precio decimal(10,2),
	id_categoria int,
	id_subcategoria int,
	primary key(id_producto),
	foreign key(id_moneda) references Monedas(id_moneda),
	foreign key(id_categoria, id_subcategoria) 
		references Subcategorias(id_categoria, id_subcategoria)
);

create table Productos_caracter_valores(
	id_producto int,
	id_categoria int,
	id_subcategoria int,
	id_caracteristica int,
	id_valor int,
	primary key(id_producto, id_categoria, id_subcategoria, id_caracteristica, id_valor),
	foreign key(id_producto) references Productos(id_producto),
	foreign key(id_categoria, id_subcategoria, id_caracteristica, id_valor) 
			references Subcateg_caracter_valores(id_categoria, 
						id_subcategoria, id_caracteristica, id_valor)
);


select * from CLIENTE
