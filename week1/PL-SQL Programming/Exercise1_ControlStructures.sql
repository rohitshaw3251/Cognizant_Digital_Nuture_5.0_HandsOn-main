BEGIN
    FOR r IN (
        SELECT c.CustomerID, l.LoanID, l.InterestRate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
        WHERE MONTHS_BETWEEN(SYSDATE, c.DOB) / 12 > 60
    ) LOOP
        UPDATE Loans
        SET InterestRate = InterestRate - 1
        WHERE LoanID = r.LoanID;
    END LOOP;
    COMMIT;
END;
/

BEGIN
    FOR r IN (
        SELECT CustomerID, Balance
        FROM Customers
    ) LOOP
        IF r.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = r.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

DECLARE
    CURSOR due_loans IS
        SELECT c.Name, l.LoanID, l.EndDate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
    
    r_loan due_loans%ROWTYPE;
BEGIN
    OPEN due_loans;
    LOOP
        FETCH due_loans INTO r_loan;
        EXIT WHEN due_loans%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || r_loan.Name || ' has loan ' || r_loan.LoanID || ' due on ' || TO_CHAR(r_loan.EndDate, 'YYYY-MM-DD'));
    END LOOP;
    CLOSE;
END;
/
