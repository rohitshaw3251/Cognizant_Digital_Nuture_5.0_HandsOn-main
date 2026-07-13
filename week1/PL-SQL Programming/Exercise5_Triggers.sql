CREATE OR REPLACE TRIGGER UpdateLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :new.LastModified := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, ActionDate, Action, Amount)
    VALUES (:new.TransactionID, :new.AccountID, SYSDATE, :new.TransactionType, :new.Amount);
END;
/

CREATE OR REPLACE TRIGGER EnforceBusinessRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :new.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Transaction amount must be positive.');
    END IF;

    IF :new.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :new.AccountID FOR UPDATE;
        IF v_balance < :new.Amount THEN
            RAISE_APPLICATION_ERROR(-20003, 'Insufficient balance for withdrawal.');
        END IF;
    END IF;
END;
/
