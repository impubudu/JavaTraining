INSERT INTO student (name,address_aid) VALUES ('pubudu1',1);
INSERT INTO student (name,address_aid) VALUES ('pubudu2',2);
INSERT INTO student (name,address_aid) VALUES ('pubudu3',3);
INSERT INTO student (name,address_aid) VALUES ('pubudu4',4);
INSERT INTO student (name,address_aid) VALUES ('pubudu5',5);

INSERT INTO telephone (number,student_sid) VALUES ('t190987856',1);
INSERT INTO telephone (number,student_sid) VALUES ('t290987856',4);
INSERT INTO telephone (number,student_sid) VALUES ('t390987856',1);
INSERT INTO telephone (number,student_sid) VALUES ('t490987856',2);
INSERT INTO telephone (number,student_sid) VALUES ('t590987856',4);
INSERT INTO telephone (number,student_sid) VALUES ('t690987856',1);
INSERT INTO telephone (number,student_sid) VALUES ('t190987856',3);


INSERT INTO project (name) VALUES ('p1');
INSERT INTO project (name) VALUES ('p2');
INSERT INTO project (name) VALUES ('p3');
INSERT INTO project (name) VALUES ('p4');
INSERT INTO project (name) VALUES ('p5');

INSERT INTO address (city) VALUES ('city1');
INSERT INTO address (city) VALUES ('city2');
INSERT INTO address (city) VALUES ('city3');
INSERT INTO address (city) VALUES ('city4');
INSERT INTO address (city) VALUES ('city5');

INSERT INTO students_projects (sid,pid) VALUES (1,1);
INSERT INTO students_projects (sid,pid) VALUES (1,2);
INSERT INTO students_projects (sid,pid) VALUES (2,1);
INSERT INTO students_projects (sid,pid) VALUES (4,1);
INSERT INTO students_projects (sid,pid) VALUES (1,3);
INSERT INTO students_projects (sid,pid) VALUES (5,1);
INSERT INTO students_projects (sid,pid) VALUES (3,1);
INSERT INTO students_projects (sid,pid) VALUES (5,5);
INSERT INTO students_projects (sid,pid) VALUES (1,5);
INSERT INTO students_projects (sid,pid) VALUES (3,4);
