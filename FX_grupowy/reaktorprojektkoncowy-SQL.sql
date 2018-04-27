DROP database pwngrup;
CREATE DATABASE pwngrup;
USE pwngrup;

create table admin(
id_admin int primary key not null auto_increment,
login varchar(30),
paswd varchar(30)
);

create table projekt(
id_user int primary key not null auto_increment,
imie varchar(15),
nazwisko varchar(20),
email varchar(20),
paswd varchar(15),
git varchar(15),
telefon varchar(12),
p1 int,
p2 int,
p3 int,
p4 int,
o1 varchar(200),
o2 varchar(200),
o3 varchar(200),
o4 varchar(200)
);

SHOW tables;
INSERT INTO admin (login, paswd) VALUES ('admin', 'admin');
INSERT INTO admin (login, paswd) VALUES ('ewa', 'ewa');
INSERT INTO admin (login, paswd) VALUES ('', '');
INSERT INTO admin (login, paswd) VALUES ('greg', '');

INSERT INTO projekt (imie, nazwisko) VALUES ('Ada', 'Kos');
INSERT INTO projekt (imie, nazwisko) VALUES ('Mackenzie', 'Wielka');
INSERT INTO projekt (imie, nazwisko) VALUES ('Ewan', 'Git') ;
INSERT INTO projekt (imie, nazwisko) VALUES ('Molly', 'NieGit') ;
INSERT INTO projekt (imie, nazwisko) VALUES ('Ida', 'Polska') ;
INSERT INTO projekt (imie, nazwisko) VALUES ('Enzo', 'Gol') ;

SELECT * FROM admin;
SELECT * FROM projekt;