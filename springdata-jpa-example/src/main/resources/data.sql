INSERT INTO student (sid,name,address_aid) VALUES (10000,'pubudu1',40000);
INSERT INTO student (sid,name,address_aid) VALUES (10001,'pubudu2',40001);
INSERT INTO student (sid,name,address_aid) VALUES (10002,'pubudu3',40002);
INSERT INTO student (sid,name,address_aid) VALUES (10003,'pubudu4',40003);
INSERT INTO student (sid,name,address_aid) VALUES (10004,'pubudu5',40004);

INSERT INTO telephone (tid,number,student_sid) VALUES (20000,'t190987856',10000);
INSERT INTO telephone (tid,number,student_sid) VALUES (20001,'t290987856',10004);
INSERT INTO telephone (tid,number,student_sid) VALUES (20002,'t390987856',10000);
INSERT INTO telephone (tid,number,student_sid) VALUES (20003,'t490987856',10002);
INSERT INTO telephone (tid,number,student_sid) VALUES (20004,'t590987856',10004);
INSERT INTO telephone (tid,number,student_sid) VALUES (20005,'t690987856',10000);
INSERT INTO telephone (tid,number,student_sid) VALUES (20006,'t190987856',10001);


INSERT INTO project (pid,name) VALUES (30000,'p1');
INSERT INTO project (pid,name) VALUES (30001,'p2');
INSERT INTO project (pid,name) VALUES (30002,'p3');
INSERT INTO project (pid,name) VALUES (30003,'p4');
INSERT INTO project (pid,name) VALUES (30004,'p5');

INSERT INTO address (aid,city) VALUES (40000,'city1');
INSERT INTO address (aid,city) VALUES (40001,'city2');
INSERT INTO address (aid,city) VALUES (40002,'city3');
INSERT INTO address (aid,city) VALUES (40003,'city4');
INSERT INTO address (aid,city) VALUES (40004,'city5');

INSERT INTO students_projects (sid,pid) VALUES (10000,30000);
INSERT INTO students_projects (sid,pid) VALUES (10000,30001);
INSERT INTO students_projects (sid,pid) VALUES (10001,30000);
INSERT INTO students_projects (sid,pid) VALUES (10000,30000);
INSERT INTO students_projects (sid,pid) VALUES (10000,30002);
INSERT INTO students_projects (sid,pid) VALUES (10004,30000);
INSERT INTO students_projects (sid,pid) VALUES (10000,30000);
INSERT INTO students_projects (sid,pid) VALUES (10004,30004);
INSERT INTO students_projects (sid,pid) VALUES (10000,30000);
INSERT INTO students_projects (sid,pid) VALUES (10002,30003
);
