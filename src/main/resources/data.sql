INSERT INTO tipas (id, pavadinimas) VALUES (1, 'ROLE_ADMINISTRATORIUS');
INSERT INTO tipas (id, pavadinimas) VALUES (2, 'ROLE_SKAITYTOJAS');
INSERT INTO vartotojai (id, vardas, pavarde, email, slaptazodis, aktyvus) VALUES (1, "administratorius", "domas", "administratorius@domas.lt", "$2a$10$q1TkysvvXtMkAAI4zEoW8uDYfXf/cv3PRqzjlZGaX0O3pI4oYtLV2", 1);
INSERT INTO vartotojai (id, vardas, pavarde, email, slaptazodis, aktyvus) VALUES (2, "skaitytojas", "domas", "skaitytojas@domas.lt", "$2a$10$q1TkysvvXtMkAAI4zEoW8uDYfXf/cv3PRqzjlZGaX0O3pI4oYtLV2", 1);
INSERT INTO vartotojai (id, vardas, pavarde, email, slaptazodis, aktyvus) VALUES (3, "Paulius", "Vasalas", "pv@gmail.lt", "$2a$10$q1TkysvvXtMkAAI4zEoW8uDYfXf/cv3PRqzjlZGaX0O3pI4oYtLV2", 1);
INSERT INTO vartotojai (id, vardas, pavarde, email, slaptazodis, aktyvus) VALUES (4, "Luka", "Kapinckiene", "laukak@gmail.lt", "$2a$10$q1TkysvvXtMkAAI4zEoW8uDYfXf/cv3PRqzjlZGaX0O3pI4oYtLV2", 1);
INSERT INTO vartotoju_tipai (vartotojo_id, tipo_id) VALUES (1, 1);
INSERT INTO vartotoju_tipai (vartotojo_id, tipo_id) VALUES (2, 2);
INSERT INTO vartotoju_tipai (vartotojo_id, tipo_id) VALUES (3, 2);
INSERT INTO vartotoju_tipai (vartotojo_id, tipo_id) VALUES (4, 2);
INSERT INTO kategorijos (id, pavadinimas) VALUES (1, "Komedijos");
INSERT INTO kategorijos (id, pavadinimas) VALUES (2, "Detektyvai");
INSERT INTO kategorijos (id, pavadinimas) VALUES (3, "Fantastika");
INSERT INTO kategorijos (id, pavadinimas) VALUES (4, "Distopijos");
INSERT INTO kategorijos (id, pavadinimas) VALUES (5, "Utopijos");
INSERT INTO kategorijos (id, pavadinimas) VALUES (6, "Dramos");
INSERT INTO kategorijos (id, pavadinimas) VALUES (7, "Siaubo filmai");
INSERT INTO kategorijos (id, pavadinimas) VALUES (8, "Romanai");
INSERT INTO filmai (id, pavadinimas, aprasas, reitingas, kategorija_id) VALUES (1, "1984", "1984-ieji – anglų rašytojo Džordžo Orvelo distopinė novelė.", 4, 1);
# INSERT INTO kategorijos (id, pavadinimas) VALUES (9, "Trileriai");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (10, "Jaunimo literatūra");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (11, "Klasika");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (12, "Grafinės novelės – komiksai");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (13, "Biografijos ir autobiografijos");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (14, "Receptų knygos");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (15, "Istorinės knygos");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (16, "Saviugda");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (17, "Meno knygos");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (18, "Kelionių knygos");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (19, "Religinės knygos");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (20, "Vaikų auklėjimo ir tėvystės knygos");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (21, "Filosofija");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (22, "Knygos apie sveikatą");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (23, "Populiarioji psichologija");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (24, "Mokslo populiarinimas");
# INSERT INTO kategorijos (id, pavadinimas) VALUES (25, "Verslo knygos");
# INSERT INTO knygos (id, isbn, kopiju_skaicius, pavadinimas, puslapiu_skaicius, santrauka, kategorija_id, virselis) VALUES (2, "978-609-441-201-1", 3,"Sidabrinis asiliukas", 148, "Asiliukas – ne pats ryškiausias knygų veikėjas.", 1, "/virseliai/978-609-441-201-1.jpg");
# INSERT INTO knygos (id, isbn, kopiju_skaicius, pavadinimas, puslapiu_skaicius, santrauka, kategorija_id, virselis) VALUES (3, "0-451-13215-7", 1,"Atlas shrugged", 1084, "Businesses suffer under burdensome laws and regulations.", 11, "/virseliai/0-451-13215-7.jpg");
# INSERT INTO knygos (id, isbn, kopiju_skaicius, pavadinimas, puslapiu_skaicius, santrauka, kategorija_id, virselis) VALUES (4, "978-609-8161-39-7", 2,"Brisiaus galas : trys apsakymai", 59, "Jis apibūdinamas autoriaus žodžiais – senas, žilas, apžabalęs.", 1, "/virseliai/978-609-8161-39-7.jpg");
# INSERT INTO knygos (id, isbn, kopiju_skaicius, pavadinimas, puslapiu_skaicius, santrauka, kategorija_id, virselis) VALUES (5, "0-14-081581-3", 1,"StarGate", 148, "Screenplay and novelization by Dean Devlin & Roland Emmerich", 3, "/virseliai/0-14-081581-3.jpg");
# INSERT INTO pamegtos_knygos (vartotojo_id, knygos_id) VALUES (2, 1);
# INSERT INTO pamegtos_knygos (vartotojo_id, knygos_id) VALUES (4, 2);
# INSERT INTO rezervave_vartotojai (knygos_id, vartotojo_id) VALUES (1, 2);
# INSERT INTO rezervave_vartotojai (knygos_id, vartotojo_id) VALUES (4, 2);
# INSERT INTO rezervave_vartotojai (knygos_id, vartotojo_id) VALUES (4, 3);
# INSERT INTO rezervave_vartotojai (knygos_id, vartotojo_id) VALUES (5, 4);