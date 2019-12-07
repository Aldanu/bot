INSERT INTO cp_zone(name,status) VALUES ('San Miguel',1);
INSERT INTO cp_zone(name,status) VALUES ('Obrajes',1);
INSERT INTO cp_zone(name,status) VALUES ('Calacoto',1);
INSERT INTO cp_zone(name,status) VALUES ('Los Pinos',1);
INSERT INTO cp_zone(name,status) VALUES ('Auquisamaña',1);
INSERT INTO cp_zone(name,status) VALUES ('Irpavi',1);
INSERT INTO cp_zone(name,status) VALUES ('Achumani',1);
INSERT INTO cp_zone(name,status) VALUES ('Cota Cota',1);

INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (1,'Iglesia de San Miguel',-16.539086,-68.077682,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (1,'Parque San Miguel',-16.539086,-68.077682,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (2,'Plaza de la Loba',-16.527872, -68.105893,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (2,'UCB La Paz',-16.523202, -68.112137,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (3,'Plaza de Humbolt',-16.541739, -68.091438,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (3,'Instituto Domingo Savio',-16.539380, -68.084879,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (4,'Ingreso Calle 21',-16.545071, -68.076546,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (4,'Circulo Aeronautico',-16.539380, -68.084879,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (5,'Plaza de Auquisamaña',-16.547123, -68.077893,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (5,'Colegio Montessori',-16.546876, -68.070104,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (6,'Megacenter',-16.531819, -68.086476,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (6,'Mercado Irpavi',-16.524208, -68.087334,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (7,'Mercado de Achumani',-16.530441, -68.073558,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (7,'Santuario de Schoenstatt',-16.525257, -68.071498,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (8,'Laguna de Cota Cota',-16.541590, -68.064353,1);
INSERT INTO cp_place(zone_id,name,latitude,longitude,status) VALUES (8,'UMSA Cota Cota',-16.539265, -68.063580,1);

ALTER TABLE cp_travel ALTER COLUMN departure_time SET DATA TYPE varchar(100);