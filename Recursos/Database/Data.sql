-- Tabla de Usuarios
insert into usuarios(usuario, clave, nombres, apellidos)
values ('adiestra', '123456', 'Alexis', 'Tamayo');


-- Tabla de Cliente

insert into cliente(cli_id, cli_ruc, cli_raz_social, cli_direccion,cli_telefono)
values (001, '123456789', 'Coca cola', 'Av. Colonial 123','2345654');

insert into cliente(cli_id, cli_ruc, cli_raz_social, cli_direccion,cli_telefono)
values (002, '023456789', 'Claro', 'Av.Nicolas Arriola 1923','2456534');

insert into cliente(cli_id, cli_ruc, cli_raz_social, cli_direccion,cli_telefono)
values (003, '823456789', 'Donofrio', 'Av.Venezuela 123','345654');


insert into cliente(cli_id,cli_ruc,cli_raz_social,cli_direccion,cli_telefono)
                values(004, '993456789', 'SONNY', 'Jr. de la Union 8723','745654');
                
                
-- Tabla de Categorias
insert into categorias(id_categoria, descripcion)
values (1, 'Televisores');
insert into categorias(id_categoria, descripcion)
values (2, 'Cómputo');
insert into categorias(id_categoria, descripcion)
values (3, 'Video Juegos');
insert into categorias(id_categoria, descripcion)
values (4, 'Audio');
insert into categorias(id_categoria, descripcion)
values (5, 'Cámaras');
insert into categorias(id_categoria, descripcion)
values (6, 'Accesorios de Computación');
insert into categorias(id_categoria, descripcion)
values (7, 'Cocina');
insert into categorias(id_categoria, descripcion)
values (8, 'Lavado y Secado');
insert into categorias(id_categoria, descripcion)
values (9, 'Refrigeración');
insert into categorias(id_categoria, descripcion)
values (10, 'Climatización');
insert into categorias(id_categoria, descripcion)
values (11, 'Pequeños Electros');
insert into categorias(id_categoria, descripcion)
values (12, 'Colchones');
insert into categorias(id_categoria, descripcion)
values (13, 'Ropa de Cama');
insert into categorias(id_categoria, descripcion)
values (14, 'Jardín y Terrazas');
insert into categorias(id_categoria, descripcion)
values (15, 'Cocina');
insert into categorias(id_categoria, descripcion)
values (16, 'Muebles');
insert into categorias(id_categoria, descripcion)
values (17, 'Baño');
insert into categorias(id_categoria, descripcion)
values (18, 'Decoración');
insert into categorias(id_categoria, descripcion)
values (19, 'Bicicletas');
insert into categorias(id_categoria, descripcion)
values (20, 'Tiempo Libre');
insert into categorias(id_categoria, descripcion)
values (21, 'Máquinas de Ejercicios');
insert into categorias(id_categoria, descripcion)
values (22, 'Paseo y Viaje');
insert into categorias(id_categoria, descripcion)
values (23, 'Alimentación y Cuidado');
insert into categorias(id_categoria, descripcion)
values (24, 'Entretenimiento para Bebés');

-- juguetes
insert into categorias(id_categoria, descripcion)
values (25, 'Juguetes');

-- Tabla de Subcategorias
insert into Subcategorias(id_categoria, id_subcategoria, descripcion)
values (1, 1, 'Smart TV');
insert into Subcategorias(id_categoria, id_subcategoria, descripcion)
values (1, 2, 'Televisores 3D');
insert into Subcategorias(id_categoria, id_subcategoria, descripcion)
values (1, 3, 'Televisores LED');
insert into Subcategorias(id_categoria, id_subcategoria, descripcion)
values (1, 4, 'Plasma');

-- COMPUTO
insert into Subcategorias(id_categoria, id_subcategoria, descripcion)
values (2, 5, 'Laptop HP');

insert into Subcategorias(id_categoria, id_subcategoria, descripcion)
values (2, 6, 'Laptop SONNY');



-- Tabla de Monedas
insert into Monedas(id_moneda, simbolo, nombre)
values (1, 'S/.', 'Nuevos soles');
insert into Monedas(id_moneda, simbolo, nombre)
values (2, '$', 'Dólares');

-- Tabla de Productos
insert into Productos(id_producto, codigo, nombre, descripcion, 
	id_moneda, precio, id_categoria, id_subcategoria)
values (1, 'TELSAM001', 'Televisor Samsung Smart 55', 'Televisor con calidad de imagen SUHD',
	1, 3500, 1, 1);

insert into Productos(id_producto, codigo, nombre, descripcion, 
	id_moneda, precio, id_categoria, id_subcategoria)
values (2, 'TELSAM002', 'Televisor Samsung Smart 80', 'Televisor con calidad de imagen SUHD de 80 pulgadas',
	1, 5500, 1, 1);

insert into Productos(id_producto, codigo, nombre, descripcion, 
	id_moneda, precio, id_categoria, id_subcategoria)
values (3, 'TELSAM003', 'Televisor Samsung 3D 50', 'Televisor con 4 lentes 3D',
	1, 4000, 1, 2);

-- COMPUTO
insert into Productos(id_producto, codigo, nombre, descripcion, 
	id_moneda, precio, id_categoria, id_subcategoria)
values (4, 'JUEGO004', 'Laptop HP', 'Laptop HP',
	1, 200, 2, 5);
	
	insert into Productos(id_producto, codigo, nombre, descripcion, 
	id_moneda, precio, id_categoria, id_subcategoria)
values (5, 'JUEGO005', 'Laptop SONNY', 'Laptop SONNY',
	1, 100, 2, 6);

-- consulta de buscar

  --         
select cli_id, cli_raz_social 
from cliente 
	                 --where cli_raz_social like('%O') TERMINE EN O
	                 where cli_id=003
	                AND cli_raz_social like('D%')

	                 
	 -- buscar por id                
	                 select cli_id, cli_ruc, cli_raz_social, 
                cli_direccion, cli_telefono 
                from cliente 
                where cli_id= 001
                
            -- update    
	  
                update cliente set cli_ruc='77777',cli_TELEFONO='77777'
        		-- where cli_id = 001 OR  cli_id = 003 
                WHERE cli_id in(001,003)
             
             -- delete
             
             delete from cliente where cli_id in(002,003)   
                
              select * from cliente
              
              
              
            update cliente set cli_raz_social='Nextel',
         		cli_direccion='Av. Arequipa 4123',cli_telefono='2338506'  
               
              
              
              
              
              
              
              
              
              
              
              
              
              
              
	