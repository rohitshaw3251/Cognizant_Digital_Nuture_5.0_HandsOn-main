CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER AS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_amount IN NUMBER,
    p_rate IN NUMBER,
    p_years IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate NUMBER;
    v_months NUMBER;
BEGIN
    v_monthly_rate := p_rate / 12 / 100;
    v_months := p_years * 12;
    IF v_monthly_rate = 0 THEN
        RETURN p_amount / v_months;
    END IF;
    RETURN (p_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)) / (POWER(1 + v_monthly_rate, v_months) - 1);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount IN NUMBER
) RETURN VARCHAR2 AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'FALSE';
END;
/
