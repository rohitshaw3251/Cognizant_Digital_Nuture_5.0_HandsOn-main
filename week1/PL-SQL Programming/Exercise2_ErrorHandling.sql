CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_source IN NUMBER,
    p_dest IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
    insufficient_balance EXCEPTION;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source FOR UPDATE;
    IF v_balance < p_amount THEN
        RAISE insufficient_balance;
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount, LastModified = SYSDATE WHERE AccountID = p_source;
    UPDATE Accounts SET Balance = Balance + p_amount, LastModified = SYSDATE WHERE AccountID = p_dest;
    COMMIT;
EXCEPTION
    WHEN insufficient_balance THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Source account has insufficient balance.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Source or destination account not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected exception during fund transfer.');
END;
/

CREATE OR REPLACE PROCEDURE UpdateSalaryWithErrorHandling (
    p_emp_id IN NUMBER,
    p_percentage IN NUMBER
) AS
    v_salary NUMBER;
BEGIN
    SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_emp_id FOR UPDATE;
    
    UPDATE Employees
    SET Salary = Salary * (1 + p_percentage / 100)
    WHERE EmployeeID = p_emp_id;
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_emp_id || ' does not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected exception during salary update.');
END;
/

CREATE OR REPLACE PROCEDURE AddNewCustomerSafe (
    p_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_id || ' already exists.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Unexpected exception during customer insertion.');
END;
/
