DECLARE
    CURSOR c_accounts IS
        SELECT a.AccountID, c.Name
        FROM Accounts a
        JOIN Customers c ON a.CustomerID = c.CustomerID;
    
    CURSOR c_tx (cp_account_id NUMBER) IS
        SELECT TransactionID, TransactionDate, Amount, TransactionType
        FROM Transactions
        WHERE AccountID = cp_account_id;
BEGIN
    FOR r_acc IN c_accounts LOOP
        DBMS_OUTPUT.PUT_LINE('--- Statement for Customer: ' || r_acc.Name || ' (Account: ' || r_acc.AccountID || ') ---');
        FOR r_tx IN c_tx(r_acc.AccountID) LOOP
            DBMS_OUTPUT.PUT_LINE('TX ID: ' || r_tx.TransactionID || ' | Date: ' || TO_CHAR(r_tx.TransactionDate, 'YYYY-MM-DD') || ' | Type: ' || r_tx.TransactionType || ' | Amount: $' || r_tx.Amount);
        END LOOP;
        DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    END LOOP;
END;
/

DECLARE
    CURSOR c_active_accounts IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;
    v_fee CONSTANT NUMBER := 10.0;
BEGIN
    FOR r IN c_active_accounts LOOP
        UPDATE Accounts
        SET Balance = Balance - v_fee,
            LastModified = SYSDATE
        WHERE CURRENT OF c_active_accounts;
    END LOOP;
    COMMIT;
END;
/

DECLARE
    CURSOR c_loans IS
        SELECT LoanID, InterestRate
        FROM Loans
        FOR UPDATE;
BEGIN
    FOR r IN c_loans LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + 0.25
        WHERE CURRENT OF c_loans;
    END LOOP;
    COMMIT;
END;
/
