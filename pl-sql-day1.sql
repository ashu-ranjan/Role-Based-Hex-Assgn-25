-- create the DB
create database fsd_hex_may25;
use fsd_hex_may25;

-- create table
create table employee(
eid int primary key auto_increment,
ename varchar(255) not null,
ebranch varchar(255),
edepartment varchar(255),
esalary double default 0
);

desc employee;

INSERT INTO EMPLOYEE (eid, ename, ebranch, edepartment, esalary) VALUES
(1, 'Amit Sharma', 'Mumbai', 'HR', 75000.00),
(2, 'Priya Patel', 'Delhi', 'IT', 82000.50),
(3, 'Rajesh Kumar', 'Bangalore', 'Finance', 90000.75),
(4, 'Neha Verma', 'Mumbai', 'HR', 78000.25),
(5, 'Vikram Singh', 'Delhi', 'IT', 85000.00),
(6, 'Anjali Gupta', 'Bangalore', 'Finance', 92000.40),
(7, 'Manish Tiwari', 'Mumbai', 'HR', 76000.90),
(8, 'Kavita Reddy', 'Chennai', 'IT', 89000.30),
(9, 'Arjun Nair', 'Bangalore', 'Finance', 94000.20),
(10, 'Sneha Iyer', 'Chennai', 'HR', 77000.10),
(11, 'Suresh Pillai', 'Mumbai', 'IT', 81000.60),
(12, 'Divya Menon', 'Delhi', 'Finance', 93000.80),
(13, 'Ravi Shankar', 'Chennai', 'HR', 74000.55),
(14, 'Pooja Deshmukh', 'Bangalore', 'IT', 86000.95),
(15, 'Karan Mehta', 'Mumbai', 'Finance', 97000.70),
(16, 'Meera Joshi', 'Delhi', 'HR', 73000.15),
(17, 'Akash Bansal', 'Chennai', 'IT', 88000.45),
(18, 'Rohan Agarwal', 'Delhi', 'Finance', 95000.60),
(19, 'Swati Saxena', 'Bangalore', 'HR', 72000.85),
(20, 'Vivek Choudhary', 'Chennai', 'Finance', 96000.90);

select * from employee;

/* PL/SQL : Procedure Language : SQL */

/* Proc 1: Display list of employess */

DELIMITER $$
CREATE PROCEDURE proc_emp_list()
BEGIN 
	SELECT * FROM employee;
END$$
DELIMITER ;

CALL proc_emp_list();

show procedure STATUS where db = 'fsd_hex_may25';

/* Proc 2: Display list of employess by branch */

DELIMITER $$
CREATE PROCEDURE proc_emp_by_branch(IN pbranch varchar(255))
BEGIN
	SELECT * FROM employee WHERE ebranch = pbranch;
END$$
DELIMITER ;

CALL proc_emp_by_branch('mumbai');
CALL proc_emp_by_branch('chennai');

/* Proc 3: Compute percentage based on total_marks, marks_scored */

DELIMITER $$
CREATE PROCEDURE proc_compute_percent(
		IN p_total_marks double, 
        IN p_marks_scored double, 
        OUT percent double)
BEGIN
	IF p_marks_scored > p_total_marks THEN
		SET percent = 0;
	ELSE
		SET percent = (p_marks_scored / p_total_marks) * 100;
	END IF;
END$$
DELIMITER ;

DROP PROCEDURE proc_compute_percent;

-- create a global/session variable to save this OUT param value of the procedure
SET @percent = 0;

CALL proc_compute_percent(500, 430, @percent);
SELECT @percent AS "Percentage Scored";

/* Sample Study Proc: Compute consumption bill by taking units and rate as per below calculation 
						for first 200 Units: rate = 8
                        for above 200 Units: rate = 10
                        
			Your procedure must give final bill value.
            do ensure that if the units are less than 10,
            we still charge 25 units which is our base
*/
DELIMITER $$
CREATE PROCEDURE proc_compute_bill(
		IN punits int, 
        OUT bill double)
BEGIN
	IF punits < 10 THEN
		SET bill = 25 * 8;
	ELSEIF punits >=10 AND punits <= 200 THEN
		SET bill = punits * 8;
	ELSE
		SET bill = (200 * 8) + ((punits - 200) * 10);
	END IF;
END$$
DELIMITER ;

SET @bill = 0;
CALL proc_compute_bill(250, @bill);
CALL proc_compute_bill(9, @bill);
CALL proc_compute_bill(180, @bill);
SELECT @bill AS "Total Bill";

/* Proc 4: Procedure to give increments to employee based on their department. 
		take department and percentage as input.
        update salaries accordingly.
*/

DELIMITER $$
CREATE PROCEDURE proc_salary_incr(IN p_dept varchar(255), p_incr_percent double)
BEGIN
	UPDATE employee
    SET esalary = esalary + (esalary * (p_incr_percent / 100))
    WHERE edepartment = p_dept;
END ;

CALL proc_salary_incr("HR", 2);

SET SQL_SAFE_UPDATES = 0;
select * from employee where edepartment = 'HR';

/* Loops : Basic Loop , while loop */

-- Proc to display numbers in loop

DELIMITER $$
CREATE PROCEDURE proc_basic_loop(IN final_num INT)
BEGIN
	DECLARE i INT DEFAULT 1;
    DECLARE result VARCHAR(255) DEFAULT "";
    loop_lbl: -- loop lable
    LOOP -- loop begins
		SET result = CONCAT(result, " ", i);
        SET i = i+1;
        
        -- loop termination condition
        IF i > final_num THEN
			LEAVE loop_lbl;
		END IF;
	END LOOP loop_lbl; -- loop ends
    SELECT result;
END ;

CALL proc_basic_loop(5);

-- proc for while loop

DELIMITER $$
CREATE PROCEDURE proc_while_loop(IN final_num INT)
BEGIN
	DECLARE i INT DEFAULT 1;
    DECLARE result VARCHAR(255) DEFAULT "";
    
    WHILE i <= final_num DO
		SET result = CONCAT(result, " ", i); -- 1 2 3 4 5
        SET i = i+1; -- increment 
	END WHILE;
    SELECT result;
END ;

CALL proc_while_loop(5);

-- Proc: fetch all ids from the table that satisfy given criteria of department.

DELIMITER $$
CREATE PROCEDURE proc_all_ids()
BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE total_rows INT DEFAULT 0;
    DECLARE ids VARCHAR(255) DEFAULT "";
    DECLARE result VARCHAR(255) DEFAULT "";
    
    -- count nos of records in a table
    SELECT COUNT(eid) INTO total_rows FROM employee;
    
    WHILE i < total_rows DO
		SELECT eid INTO ids FROM employee LIMIT i,1;
		SET result = CONCAT(result, " ", ids); 
		SET i = i+1; 
	END WHILE;
    SELECT result;
END ; 
DROP PROCEDURE proc_all_ids;
CALL proc_all_ids();


/* Proc: Procedure to give increments to employee based on their department. 
		take department and percentage as input.
        update salaries accordingly.
        
        Fetch all the ids from table satisfying department criteria 1,4,7,9,13,17
        Since I will inside the loop,
        I can call update on these IDs and increment Salary.
*/

DELIMITER $$
CREATE PROCEDURE proc_emp_update_sal_v2(IN p_dept VARCHAR(255), p_incr DOUBLE)
BEGIN
	DECLARE num_rows INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE p_id INT DEFAULT 0;
    
	SELECT COUNT(eid) into num_rows 
    FROM employee
    WHERE edepartment = p_dept;
    
    WHILE i < num_rows DO
		SELECT eid INTO p_id
        FROM employee
        WHERE edepartment = p_dept
        LIMIT i,1;
        
        UPDATE employee
        SET esalary = esalary + (esalary * (p_incr / 100))
        WHERE eid = p_id;
        
        SET i = i + 1;
	END WHILE;
END ;

drop procedure proc_emp_update_sal_v2;
call proc_emp_update_sal_v2("IT", 10);

select * from employee where edepartment = "IT";

-- Reattempt the above code

DELIMITER $$
CREATE PROCEDURE proc_emp_update_sal_test(IN p_dept VARCHAR(255), p_incr DOUBLE)
BEGIN
	DECLARE num_rows INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE p_id INT DEFAULT 0;
    
    SELECT COUNT(eid) INTO num_rows 
    FROM employee
    WHERE edepartment = p_dept;
    
    WHILE i < num_rows DO
		SELECT eid INTO P_id
        FROM employee
        WHERE edepartment = p_dept
        LIMIT i,1;
        
        UPDATE employee
        SET esalary = esalary + (esalary * (p_incr / 100))
        WHERE eid = p_id;
        
        SET i = i+1;
    END WHILE;
END ;
drop procedure proc_emp_update_sal_test;
call proc_emp_update_sal_test("HR", 10);

select * from employee where edepartment = "HR";

/*
	3 types of parameter
    - IN
    - OUT
    - INOUT
*/

-- Proc for return salary of employee based on given ID

DELIMITER $$
CREATE PROCEDURE proc_salary_by_id(INOUT my_var VARCHAR(255)) -- can use NUMBER as datatype
BEGIN
	SELECT esalary INTO my_var
    FROM employee
    WHERE eid = my_var;
END ;

SET @x = 1;
CALL proc_salary_by_id(@x);
SELECT @x AS "Current Salary";

/* DAY 2 */

/* TRIGGER 

CREATE TRIGGER <trigger-name>
BEFORE | AFTER ON <table-name>
FOR EACH ROW
BEGIN

END

always go for BEFORE the ops

*/

CREATE TABLE employee_log(
	id INT PRIMARY KEY AUTO_INCREMENT,
    old_salary DOUBLE,
    new_salary DOUBLE,
    date_of_op DATE,
    username VARCHAR(255)
);
ALTER TABLE employee_log ADD COLUMN eid INT;

DELIMITER $$
CREATE TRIGGER trg_employee_update
BEFORE UPDATE ON employee
FOR EACH ROW
BEGIN
	INSERT INTO employee_log(old_salary, new_salary, date_of_op, username,eid)
    VALUES (OLD.esalary, NEW.esalary, now(), user(), OLD.eid);
END ;

DROP TRIGGER trg_employee_update;
UPDATE employee SET esalary = 200000 WHERE eid = 6;


-- Trigger for Query Validation
/* Salary should not be more than 5 lacs while inserting new employee */

INSERT INTO employee (ename, ebranch, edepartment, esalary) VALUES ("John Doe", "Mumbai", "Finance", 60000);

DELIMITER $$
CREATE TRIGGER trg_employee_insert
BEFORE INSERT ON employee
FOR EACH ROW
BEGIN
	IF NEW.esalary > 500000 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = "Salary should not be more than 5 lacs while inserting new employee";
    END IF;
END ;

DROP TRIGGER trg_employee_insert;

/* Views : Views are created to safeguard critical info from outside world */

-- create a view to sageguard amployee salary

DELIMITER $$
CREATE VIEW view_employee 
AS
SELECT eid, ename, ebranch, edepartment
FROM employee;


-- create a view for showing employee stat based on department

DELIMITER $$
CREATE VIEW view_employee_department_stats
AS
SELECT edepartment, count(eid) AS employee_count
FROM employee
GROUP BY edepartment;

drop view view_employee_department_stats;

-- create a view for showing employee stat based on department with analysis simulation

DELIMITER $$
CREATE VIEW view_department_employee_stats
AS
SELECT 
	edepartment AS Department, 
    COUNT(eid) AS Total_Employee,
	ROUND(AVG(esalary), 2) AS Avg_Salary,
    ROUND(MAX(esalary), 2) AS Maximum_Salary,
    ROUND(MIN(esalary), 2) AS Minimum_Salary
FROM employee
GROUP BY edepartment;

drop view view_department_employee_stats;

-- create a view for showing employee stat based on branch with analysis simulation

DELIMITER $$
CREATE VIEW view_employee_branch_stats
AS
SELECT ebranch, count(eid) AS employee_count
FROM employee
GROUP BY ebranch;

drop view view_employee_branch_stats;

select * from view_employee_branch_stats;

/** Functions 
	Procedures cannot return a value (workaround-use OUT param), but functions can and must return a value
*/
-- func to fetch employee salary based in id

DELIMITER $$
CREATE FUNCTION emp_sal_fun(p_id INT)
RETURNS DOUBLE
DETERMINISTIC
BEGIN
	DECLARE sal DOUBLE;
    SELECT esalary INTO sal
    FROM employee
    WHERE eid = p_id;
    
    RETURN sal;
END ;

SELECT emp_sal_fun(6) AS "Salary";


-- SET GLOBAL log_bin_trust_function_creators = 1;


DELIMITER $$
CREATE FUNCTION random_func()
RETURNS DOUBLE
DETERMINISTIC
BEGIN
	DECLARE num DOUBLE;
    SELECT rand() INTO num;
    SET num = num * 1000000;
    
    RETURN num;
END ;

SELECT random_func() AS "Random Number";

/*
	Anonymous procedure: one that do not have name
    BEGIN
		statements
    END ;
*/



