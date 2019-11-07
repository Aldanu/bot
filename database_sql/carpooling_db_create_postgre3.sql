-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-11-07 16:42:59.133

-- tables
-- Table: cp_car
CREATE TABLE cp_car (
    car_id serial  NOT NULL,
    person_id int  NOT NULL,
    enrollment_number varchar(20)  NOT NULL,
    brand varchar(50)  NOT NULL,
    model varchar(50)  NOT NULL,
    capacity int  NOT NULL,
    status int  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(150)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT cp_car_pk PRIMARY KEY (car_id)
);

-- Table: cp_person
CREATE TABLE cp_person (
    person_id serial  NOT NULL,
    first_name varchar(50)  NOT NULL,
    last_name varchar(50)  NOT NULL,
    ci_number varchar(30)  NULL,
    cellphone_number varchar(30)  NULL,
    reputation numeric(15,5)  NULL,
    carpool int  NOT NULL,
    status int  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(150)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT cp_person_pk PRIMARY KEY (person_id)
);

-- Table: cp_place
CREATE TABLE cp_place (
    place_id serial  NOT NULL,
    zone_id int  NOT NULL,
    name varchar(200)  NOT NULL,
    latitude numeric(15,6)  NOT NULL,
    longitude numeric(15,6)  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT cp_place_pk PRIMARY KEY (place_id)
);

-- Table: cp_travel
CREATE TABLE cp_travel (
    travel_id serial  NOT NULL,
    car_id int  NOT NULL,
    departure_time time  NOT NULL,
    cost numeric(15,5)  NOT NULL,
    number_passengers int  NOT NULL,
    pet_friendly int  NOT NULL,
    description varchar(255)  NULL,
    status int  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(150)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT cp_travel_pk PRIMARY KEY (travel_id)
);

-- Table: cp_travel_place
CREATE TABLE cp_travel_place (
    travel_place_id serial  NOT NULL,
    place_id int  NOT NULL,
    travel_id int  NOT NULL,
    "order" int  NOT NULL,
    status int  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(150)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT cp_travel_place_pk PRIMARY KEY (travel_place_id)
);

-- Table: cp_travel_rider
CREATE TABLE cp_travel_rider (
    travel_rider_id serial  NOT NULL,
    person_id int  NOT NULL,
    travel_id int  NOT NULL,
    experience numeric(15,5)  NULL,
    status int  NOT NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(150)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT cp_travel_rider_pk PRIMARY KEY (travel_rider_id)
);

-- Table: cp_user
CREATE TABLE cp_user (
    user_id serial  NOT NULL,
    person_id int  NOT NULL,
    bot_user_id varchar(50)  NOT NULL,
    chat_user_id varchar(50)  NOT NULL,
    conversation_id int  NULL,
    subconversation_id int  NULL,
    last_message_sent varchar(250)  NULL,
    last_message_received varchar(250)  NULL,
    tx_user varchar(50)  NOT NULL,
    tx_host varchar(150)  NOT NULL,
    tx_date date  NOT NULL,
    CONSTRAINT cp_user_ak_1 UNIQUE (person_id) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT cp_user_pk PRIMARY KEY (user_id)
);

-- Table: cp_zone
CREATE TABLE cp_zone (
    zone_id serial  NOT NULL,
    name varchar(200)  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT cp_zone_pk PRIMARY KEY (zone_id)
);

-- foreign keys
-- Reference: cp_car_cp_person (table: cp_car)
ALTER TABLE cp_car ADD CONSTRAINT cp_car_cp_person
    FOREIGN KEY (person_id)
    REFERENCES cp_person (person_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cp_place_cp_zone (table: cp_place)
ALTER TABLE cp_place ADD CONSTRAINT cp_place_cp_zone
    FOREIGN KEY (zone_id)
    REFERENCES cp_zone (zone_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cp_travel_cp_car (table: cp_travel)
ALTER TABLE cp_travel ADD CONSTRAINT cp_travel_cp_car
    FOREIGN KEY (car_id)
    REFERENCES cp_car (car_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cp_travel_place_cp_place (table: cp_travel_place)
ALTER TABLE cp_travel_place ADD CONSTRAINT cp_travel_place_cp_place
    FOREIGN KEY (place_id)
    REFERENCES cp_place (place_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cp_travel_place_cp_travel (table: cp_travel_place)
ALTER TABLE cp_travel_place ADD CONSTRAINT cp_travel_place_cp_travel
    FOREIGN KEY (travel_id)
    REFERENCES cp_travel (travel_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cp_travel_rider_cp_person (table: cp_travel_rider)
ALTER TABLE cp_travel_rider ADD CONSTRAINT cp_travel_rider_cp_person
    FOREIGN KEY (person_id)
    REFERENCES cp_person (person_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cp_travel_rider_cp_travel (table: cp_travel_rider)
ALTER TABLE cp_travel_rider ADD CONSTRAINT cp_travel_rider_cp_travel
    FOREIGN KEY (travel_id)
    REFERENCES cp_travel (travel_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: cp_user_cp_person (table: cp_user)
ALTER TABLE cp_user ADD CONSTRAINT cp_user_cp_person
    FOREIGN KEY (person_id)
    REFERENCES cp_person (person_id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

