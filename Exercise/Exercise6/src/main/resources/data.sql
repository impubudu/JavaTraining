-- ALTER TABLE employee
-- DROP CONSTRAINT fkbejtwvg9bxus2mffsm3swj3u9;

INSERT INTO employee (first_name,last_name,birth_day,age,address,salary,marital_status,created_date,department_id) VALUES
('Pubudu','Prasanna','1994.08.01',25,'Tangalle',20000.0,false,now(),1),
('Nimal','Perera','1984.09.01',45,'Colombo',120000.0,true,now(),2),
('Gayan','Nuwan','1990.09.01',28,'Colombo',220000.0,false,now(),2),
('Kasun','Rajitha','1984.09.01',32,'Hambanthota',20000.0,false,now(),1),
('Nuwan','Madhushanka','1995.05.21',24,'Negambo',20000.0,true,now(),3),
('Charith','Rasika','1995.05.21',24,'Mathara',200000.0,false,now(),4),
('Nipun','Thilakshan','1982.05.21',44,'Mathara',200000.0,false,now(),4),
('Lalith','Bhanuka','1990.11.21',34,'Badulla',500000.0,true,now(),2),
('Nimantha','Piumal','1988.01.26',30,'Galle',100000.0,false,now(),5),
('Saman','Nilanka','1972.10.11',55,'Galle',50000.0,true,now(),1);


INSERT INTO department (name) VALUES
('Dept1'),
('Dept2'),
('Dept3'),
('Dept4'),
('Dept5');