CREATE TABLE company
(
  id INTEGER NOT NULL,
  name CHARACTER VARYING,
  CONSTRAINT company_pkey PRIMARY KEY (id)
);
CREATE TABLE person
(
  id INTEGER NOT NULL,
  name CHARACTER VARYING,
  company_id INTEGER,
  CONSTRAINT person_pkey PRIMARY KEY (id)
);
INSERT INTO company (id, name) VALUES
  (1, 'company 1'),
  (2, 'company 2'),
  (3, 'company 3'),
  (4, 'company 4'),
  (5, 'company 5'),
  (6, 'company 6');
INSERT INTO person (id, name, company_id) VALUES
  (1, 'person 01', 1),
  (2, 'person 02', 2),
  (3, 'person 03', 3),
  (4, 'person 04', 3),
  (5, 'person 05', 2),
  (6, 'person 06', 1),
  (7, 'person 07', 4),
  (8, 'person 08', 5),
  (9, 'person 09', 5),
  (10, 'person 10', 5),
  (11, 'person 11', 5),
  (12, 'person 12', 5),
  (13, 'person 13', 6),
  (14, 'person 14', 6);
SELECT p.name AS Person, c.name AS Company FROM person p JOIN company c ON p.company_id = c.id
WHERE c.id != 5;
SELECT c.name AS Company, count (p.company_id) AS Staff FROM person p JOIN company c
ON p.company_id = c.id GROUP BY Company ORDER BY Staff DESC LIMIT 1;
