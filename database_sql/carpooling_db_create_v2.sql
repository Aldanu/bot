-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-10-31 15:18:46.336

-- tables

-- Table: cp_carpooler
CREATE TABLE cp_carpooler (
    carpooler_id serial NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    ci_number varchar(30) NOT NULL,
    cellphone_number varchar(30) NOT NULL,
    reputation numeric(15,5) NULL,
    status int NOT NULL,
    tx_user varchar(50) NOT NULL,
    tx_host varchar(150) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT cp_carpooler_pk PRIMARY KEY (carpooler_id)
);

-- Table: cp_car
CREATE TABLE cp_car (
    car_id serial NOT NULL AUTO_INCREMENT,
    carpooler_id bigint unsigned NOT NULL,
    enrollment_number varchar(20) NOT NULL,
    brand varchar(50) NOT NULL,
    model varchar(50) NOT NULL,
    status int NOT NULL,
    tx_user varchar(50) NOT NULL,
    tx_host varchar(150) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT cp_car_pk PRIMARY KEY (car_id)
);

-- Table: cp_zone
CREATE TABLE cp_zone (
    zone_id serial NOT NULL AUTO_INCREMENT,
    name varchar(200) NOT NULL,
    status int NOT NULL,
    CONSTRAINT cp_zone_pk PRIMARY KEY (zone_id)
);

-- Table: cp_place
CREATE TABLE cp_place (
    place_id serial NOT NULL AUTO_INCREMENT,
    name varchar(200) NOT NULL,
    zone_id bigint unsigned NOT NULL,
    latitude numeric(15,6) NOT NULL,
    longitude numeric(15,6) NOT NULL,
    status int NOT NULL,
    CONSTRAINT cp_place_pk PRIMARY KEY (place_id)
);

-- Table: cp_rider
CREATE TABLE cp_rider (
    rider_id serial NOT NULL AUTO_INCREMENT,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    reputation numeric(15,5) NULL,
    status int NOT NULL,
    tx_user varchar(50) NOT NULL,
    tx_host varchar(150) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT cp_rider_pk PRIMARY KEY (rider_id)
);

-- Table: cp_travel
CREATE TABLE cp_travel (
    travel_id serial NOT NULL AUTO_INCREMENT,
    car_id bigint unsigned NOT NULL,
    carpooler_id bigint unsigned NOT NULL,
    departure_time time NOT NULL,
    cost numeric(15,5) NOT NULL,
    number_passengers int NOT NULL,
    pet_friendly int NOT NULL,
    status int NOT NULL,
    tx_user varchar(50) NOT NULL,
    tx_host varchar(150) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT cp_travel_pk PRIMARY KEY (travel_id)
);

-- Table: cp_travel_place
CREATE TABLE cp_travel_place (
    travel_place_id serial NOT NULL AUTO_INCREMENT,
    place_id bigint unsigned NOT NULL,
    travel_id bigint unsigned NOT NULL,
    `order` int NOT NULL,
    status int NOT NULL,
    tx_user varchar(50) NOT NULL,
    tx_host varchar(150) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT cp_travel_place_pk PRIMARY KEY (travel_place_id)
);

-- Table: cp_travel_rider
CREATE TABLE cp_travel_rider (
    travel_rider_id serial NOT NULL AUTO_INCREMENT,
    travel_id bigint unsigned NOT NULL,
    rider_id bigint unsigned NOT NULL,
    experience numeric(15,5) NOT NULL,
    status int NOT NULL,
    tx_user varchar(50) NOT NULL,
    tx_host varchar(50) NOT NULL,
    tx_date date NOT NULL,
    CONSTRAINT cp_travel_rider_pk PRIMARY KEY (travel_rider_id)
);



-- foreign keys
-- Reference: car_id (table: cp_travel)
ALTER TABLE cp_travel ADD CONSTRAINT car_id FOREIGN KEY car_id (car_id)
    REFERENCES cp_car (car_id);

-- Reference: carpooler_id (table: cp_car)
ALTER TABLE cp_car ADD CONSTRAINT carpooler_id FOREIGN KEY carpooler_id (carpooler_id)
    REFERENCES cp_carpooler (carpooler_id);

-- Reference: cp_place_cp_zone (table: cp_place)
ALTER TABLE cp_place ADD CONSTRAINT cp_place_cp_zone FOREIGN KEY cp_place_cp_zone (zone_id)
    REFERENCES cp_zone (zone_id);

-- Reference: cp_travel_cp_carpooler (table: cp_travel)
ALTER TABLE cp_travel ADD CONSTRAINT cp_travel_cp_carpooler FOREIGN KEY cp_travel_cp_carpooler (carpooler_id)
    REFERENCES cp_carpooler (carpooler_id);

-- Reference: cp_travel_place_cp_place (table: cp_travel_place)
ALTER TABLE cp_travel_place ADD CONSTRAINT cp_travel_place_cp_place FOREIGN KEY cp_travel_place_cp_place (place_id)
    REFERENCES cp_place (place_id);

-- Reference: cp_travel_place_cp_travel (table: cp_travel_place)
ALTER TABLE cp_travel_place ADD CONSTRAINT cp_travel_place_cp_travel FOREIGN KEY cp_travel_place_cp_travel (travel_id)
    REFERENCES cp_travel (travel_id);

-- Reference: cp_travel_rider_cp_rider (table: cp_travel_rider)
ALTER TABLE cp_travel_rider ADD CONSTRAINT cp_travel_rider_cp_rider FOREIGN KEY cp_travel_rider_cp_rider (rider_id)
    REFERENCES cp_rider (rider_id);

-- Reference: cp_travel_rider_cp_travel (table: cp_travel_rider)
ALTER TABLE cp_travel_rider ADD CONSTRAINT cp_travel_rider_cp_travel FOREIGN KEY cp_travel_rider_cp_travel (travel_id)
    REFERENCES cp_travel (travel_id);

-- End of file.

