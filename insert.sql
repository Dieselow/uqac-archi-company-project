USE archi;

--Hibernate values
INSERT INTO `hibernate_sequence`(next_val)
     VALUES (24);

--Insertion des equipements
INSERT INTO `equipment`(id,
                              installation_date,
                              equipment_type_id,
                              room_id)
     VALUES (15,
             '2019-01-22 19:00:00.0',
             12,
             2000);
INSERT INTO `equipment`(id,
                              installation_date,
                              equipment_type_id,
                              room_id)
     VALUES (16,
             '2019-01-22 19:00:00.0',
             12,
             2000);
INSERT INTO `equipment`(id,
                              installation_date,
                              equipment_type_id,
                              room_id)
     VALUES (17,
             '2019-01-22 19:00:00.0',
             12,
             2001);
INSERT INTO `equipment`(id,
                              installation_date,
                              equipment_type_id,
                              room_id)
     VALUES (18,
             '2012-10-02 20:00:00.0',
             11,
             2003);
INSERT INTO `equipment`(id,
                              installation_date,
                              equipment_type_id,
                              room_id)
     VALUES (19,
             '2012-10-02 20:00:00.0',
             11,
             2002);
INSERT INTO `equipment`(id,
                              installation_date,
                              equipment_type_id,
                              room_id)
     VALUES (20,
             '2013-10-02 20:00:00.0',
             14,
             2002);
INSERT INTO `equipment`(id,
                              installation_date,
                              equipment_type_id,
                              room_id)
     VALUES (21,
             '2013-10-02 20:00:00.0',
             14,
             2003);

--Insertion des equipment_type
INSERT INTO `equipment_type`(id, name)
     VALUES (11, 'Imprimante');
INSERT INTO `equipment_type`(id, name)
     VALUES (12, 'Chaise dentiste');
INSERT INTO `equipment_type`(id, name)
     VALUES (13, 'Table de massage');
INSERT INTO `equipment_type`(id, name)
     VALUES (14, 'Bureau');

--Insertion des roles users
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (2, 3);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (3, 3);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (4, 1);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (5, 1);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (6, 2);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (7, 2);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (8, 1);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (9, 3);
INSERT INTO `user_roles`(user_id, role_id)
     VALUES (10, 2);

--Insertion des employees
INSERT INTO `employee`(employment_date,
                             salary,
                             work_schedule,
                             employee_id)
     VALUES ('2020-12-31 19:00:00.0',
             5000.0,
             'Monday - 10 AM',
             1);
INSERT INTO `employee`(employment_date,
                             salary,
                             work_schedule,
                             employee_id)
     VALUES ('2019-12-31 19:00:00.0',
             10000.0,
             'Monday - 10 AM',
             2);
INSERT INTO `employee`(employment_date,
                             salary,
                             work_schedule,
                             employee_id)
     VALUES ('2020-12-31 19:00:00.0',
             5000.0,
             'Tuesday',
             3);
INSERT INTO `employee`(employment_date,
                             salary,
                             work_schedule,
                             employee_id)
     VALUES ('2020-12-31 19:00:00.0',
             5000.0,
             'Monday - 10 AM',
             6);
INSERT INTO `employee`(employment_date,
                             salary,
                             work_schedule,
                             employee_id)
     VALUES ('2019-10-02 20:00:00.0',
             2000.0,
             'Monday - Tuesday - Thurday',
             7);
INSERT INTO `employee`(employment_date,
                             salary,
                             work_schedule,
                             employee_id)
     VALUES ('1989-12-31 19:00:00.0',
             15000.0,
             'Tuesday Wednesday Thursday',
             9);
INSERT INTO `employee`(employment_date,
                             salary,
                             work_schedule,
                             employee_id)
     VALUES ('2005-12-20 19:00:00.0',
             5000.0,
             'Monday - Tuesday - Thurday',
             10);

--Insertion des rooms
INSERT INTO `room`(id, name)
     VALUES (2000, 'Salle 1');
INSERT INTO `room`(id, name)
     VALUES (2001, 'Salle 2');
INSERT INTO `room`(id, name)
     VALUES (2002, 'Bureau John Paul');
INSERT INTO `room`(id, name)
     VALUES (2003, 'Accueil');

-- Insertion des rights
INSERT INTO `user_rights`(id, name)
     VALUES (0, 'ADMIN');
INSERT INTO `user_rights`(id, name)
     VALUES (1, 'PATIENT');
INSERT INTO `user_rights`(id, name)
     VALUES (2, 'SECRETARY');
INSERT INTO `user_rights`(id, name)
     VALUES (3, 'CAREGIVER');
INSERT INTO `user_rights`(id, name)
     VALUES (4, 'USER');

--Insertion des secretaires
INSERT INTO `secretary`(secretary_id)
     VALUES (7);
INSERT INTO `secretary`(secretary_id)
     VALUES (10);

--Insertion des docteurs
INSERT INTO `caregiver`(licence_number, caregiver_id)
     VALUES ('AB555', 1);
INSERT INTO `caregiver`(licence_number, caregiver_id)
     VALUES ('AB555', 2);
INSERT INTO `caregiver`(licence_number, caregiver_id)
     VALUES ('TW557', 3);
INSERT INTO `caregiver`(licence_number, caregiver_id)
     VALUES ('XT887', 9);

--Insertion des patients
INSERT INTO `patient`(patient_id,
                            healthfile_id,
                            primary_doctor_caregiver_id)
     VALUES (4, 22, 9);
INSERT INTO `patient`(patient_id,
                            healthfile_id,
                            primary_doctor_caregiver_id)
     VALUES (5, 23, 9);
INSERT INTO `patient`(patient_id,
                            healthfile_id,
                            primary_doctor_caregiver_id)
     VALUES (8, NULL, NULL);

--Insertion des dossiers patients
INSERT INTO `health_file`(id,
                                chronic_conditions,
                                emergency_contact,
                                medications)
     VALUES (22,
             'Lors de la visite du 5 nomvembre 2014, le patient souffrait d''une aorte percée au coeur droit',
             'Jean Hamilton 418 545 8778',
             '5 dose d''ibuprofene par jour');
INSERT INTO `health_file`(id,
                                chronic_conditions,
                                emergency_contact,
                                medications)
     VALUES (23,
             'Rupture du fémur en janvier 2012',
             'Karin Lasso 418 545 8778',
             '');

--Insertion des users
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (2,
             '5 rue des Lombards G7H 0S5 Saguenay',
             '1990-05-04 20:00:00.0',
             'john.paul@gmail.com',
             'John',
             'Paul',
             '$2a$10$BRtuJfNHU8q5P7HtKCF5Zuvt0FJ73JL6W3dVFEb7FR4AgZCxcDH5C',
             '4185121441',
             'JPaul01');
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (3,
             '14 rue des Ananas G4B 0W8 Montreal',
             '1992-05-04 20:00:00.0',
             'paul.ducasse@free.fr',
             'Paul',
             'Ducasse',
             '$2a$10$ebOQm8WOur9XKGb/Yb3m3OAzF4cME57ECQKetMOb1P0OJCUhcK90W',
             '4185187442',
             'PDucasse01');
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (4,
             '',
             '1982-08-21 20:00:00.0',
             'jean.dumoulin@videotron.ca',
             'Jean',
             'Du Moulin',
             '$2a$10$.YSfUpVy9.4Fk0mfw5F1LOJfhe0B8Gkf7/ZSoXqrWahD9/uX8RAfG',
             NULL,
             'JDuMoulin');
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (5,
             '14 avenue des Parisiens A4W 8E4 Toronto',
             '2002-10-07 20:00:00.0',
             'anna.lasso@mail.com',
             'Anna',
             'Lasso',
             '$2a$10$6iHpRw9A0a4L7HiPwTrDauW6tQBBjfB9zC0vBVX.c.Dxg/jOJTdTq',
             '33770471615',
             'ALasso');
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (7,
             '6711 rue Price G7H 0S5 Chicoutimi',
             '2000-12-04 19:00:00.0',
             'pierre.dutronc@mail.com',
             'Pierre',
             'Dutronc',
             '$2a$10$rqn06vj6cv7YcHwDel7iRudpa4AW.pJ7D2yQwEV.uANykrv6cnCDW',
             '4185459867',
             'PDutronc');
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (8,
             '14 avenue Hugo A4W 8E4 Toronto',
             '1970-10-07 20:00:00.0',
             'archibald.doch@mail.com',
             'Archibald',
             'Doch',
             '$2a$10$f6mn7rdqPy7lwNPqfsUWduoXJ7mwF5rvOF8aaVAhBGI7qYCpsXw7.',
             '+3387458745',
             'ADoch');
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (9,
             '14 rue des Courgettes G4B 0W8 Montreal',
             '1993-01-21 19:00:00.0',
             'alain@chabat.com',
             'Alain',
             'Chabat',
             '$2a$10$p0YG6cP.vje71ucGO9SThuLCurk/L1TW42tOKQvlqPq5jEp66CF.e',
             '4185189848',
             'AChabat01');
INSERT INTO `user`(id,
                         address,
                         date_of_birth,
                         email,
                         first_name,
                         last_name,
                         password,
                         phone_number,
                         username)
     VALUES (10,
             '6711 rue Denfery G7H 0S5 Chicoutimi',
             '1985-08-04 20:00:00.0',
             'claude.hami@yahoo.com',
             'Claude',
             'Hami',
             '$2a$10$0OAvNpNtuKuo5qFQBOLHfuhtu3VvfCzS/0CJ.bQIrvbomniCzs.W.',
             '4185476998',
             'CHami');

