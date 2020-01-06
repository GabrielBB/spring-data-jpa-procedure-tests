create table EMPLOYEE
(
    ID NUMBER generated as identity,
    NAME VARCHAR2(50) not null
)

/

INSERT INTO SYS.EMPLOYEE (ID, NAME) VALUES (3, 'Fanny');
INSERT INTO SYS.EMPLOYEE (ID, NAME) VALUES (4, 'Gabriel');

COMMIT;

/

CREATE OR REPLACE PROCEDURE sys.get_employees (p_employees OUT SYS_REFCURSOR)
IS BEGIN OPEN p_employees FOR SELECT * FROM sys.employee; END;

/

CREATE OR REPLACE PROCEDURE sys.get_single_employee (p_employee OUT SYS_REFCURSOR)
    IS BEGIN OPEN p_employee FOR SELECT * FROM sys.employee where ID = 3; END;

/

CREATE OR REPLACE PROCEDURE sys.get_employees_count (p_count OUT number)
    IS BEGIN SELECT count(*) into p_count FROM sys.employee; END;

/