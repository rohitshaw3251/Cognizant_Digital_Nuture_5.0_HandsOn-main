CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER);
    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER);
    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id NUMBER, p_name VARCHAR2, p_dob DATE, p_balance NUMBER) AS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
    END AddCustomer;

    PROCEDURE UpdateCustomer(p_id NUMBER, p_name VARCHAR2, p_balance NUMBER) AS
    BEGIN
        UPDATE Customers
        SET Name = p_name, Balance = p_balance, LastModified = SYSDATE
        WHERE CustomerID = p_id;
        COMMIT;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(p_id NUMBER) RETURN NUMBER AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2);
    PROCEDURE UpdateEmployee(p_id NUMBER, p_position VARCHAR2, p_salary NUMBER);
    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_id NUMBER, p_name VARCHAR2, p_position VARCHAR2, p_salary NUMBER, p_dept VARCHAR2) AS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_position, p_salary, p_dept, SYSDATE);
        COMMIT;
    END HireEmployee;

    PROCEDURE UpdateEmployee(p_id NUMBER, p_position VARCHAR2, p_salary NUMBER) AS
    BEGIN
        UPDATE Employees
        SET Position = p_position, Salary = p_salary
        WHERE EmployeeID = p_id;
        COMMIT;
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary(p_id NUMBER) RETURN NUMBER AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER);
    PROCEDURE CloseAccount(p_acc_id NUMBER);
    PROCEDURE Deposit(p_acc_id NUMBER, p_amount NUMBER);
    PROCEDURE Withdraw(p_acc_id NUMBER, p_amount NUMBER);
    PROCEDURE Transfer(p_source NUMBER, p_dest NUMBER, p_amount NUMBER);
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id NUMBER, p_cust_id NUMBER, p_type VARCHAR2, p_balance NUMBER) AS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_acc_id, p_cust_id, p_type, p_balance, SYSDATE);
        COMMIT;
    END OpenAccount;

    PROCEDURE CloseAccount(p_acc_id NUMBER) AS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_acc_id;
        COMMIT;
    END CloseAccount;

    PROCEDURE Deposit(p_acc_id NUMBER, p_amount NUMBER) AS
    BEGIN
        UPDATE Accounts SET Balance = Balance + p_amount, LastModified = SYSDATE WHERE AccountID = p_acc_id;
        COMMIT;
    END Deposit;

    PROCEDURE Withdraw(p_acc_id NUMBER, p_amount NUMBER) AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_acc_id FOR UPDATE;
        IF v_balance < p_amount THEN
            RAISE_APPLICATION_ERROR(-20004, 'Insufficient balance.');
        END IF;
        UPDATE Accounts SET Balance = Balance - p_amount, LastModified = SYSDATE WHERE AccountID = p_acc_id;
        COMMIT;
    END Withdraw;

    PROCEDURE Transfer(p_source NUMBER, p_dest NUMBER, p_amount NUMBER) AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source FOR UPDATE;
        IF v_balance < p_amount THEN
            RAISE_APPLICATION_ERROR(-20005, 'Insufficient balance.');
        END IF;
        UPDATE Accounts SET Balance = Balance - p_amount, LastModified = SYSDATE WHERE AccountID = p_source;
        UPDATE Accounts SET Balance = Balance + p_amount, LastModified = SYSDATE WHERE AccountID = p_dest;
        COMMIT;
    END Transfer;
END AccountOperations;
/
