create database Egg_Cell;

use Egg_Cell;

create table Lote(
id int auto_increment not null,
raza varchar(30) not null,
edad_mes int not null,
cantidad int not null,
fecha_ingreso date not null,
galpon int not null,
primary key(id)
);

create table Inventario(
id int auto_increment not null,
id_lote int not null,
cantidad_B int not null,
cantidad_A int not null,
cantidad_AA int not null,
cantidad_AAA int not null,
cantidad_JUMBO int not null,
fecha datetime not null,
foreign key(id_lote) references Lote(id)
on delete cascade,
primary key(id)
);

create table Insumo(
codigo int auto_increment not null,
nombre varchar(50) not null,
fecha_compra date not null,
id_lote_aplicacion int not null,
foreign key(id_lote_aplicacion) references Lote(id)
on delete cascade,
primary key(codigo)
);