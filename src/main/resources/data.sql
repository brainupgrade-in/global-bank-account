INSERT INTO account(account_Id,customer_Id,current_Balance,account_Type,owner_Name)
VALUES (10000,'eric',100000.0, 'Savings', 'Eric D'),
	(20000,'john',100000.0, 'Savings', 'John Rhodes'),
	(20001,'john',20000.0, 'Current', 'John Rhodes'),
	(30000,'ratan',100000.0, 'Savings', 'Ratan Sharma'),
	(30001,'ratan',20000.0, 'Current', 'Ratan Sharma');

-- Ledger accounts for the posting API (/api/v1/postings). Amounts are long minor units (paise).
INSERT INTO ledger_account (id, name, currency) VALUES ('ACC-CLIENT-001', 'Client Money Pool', 'INR');
INSERT INTO ledger_account (id, name, currency) VALUES ('ACC-CLIENT-002', 'Client Money Pool II', 'INR');
INSERT INTO ledger_account (id, name, currency) VALUES ('ACC-SUSPENSE',   'Suspense',           'INR');
INSERT INTO ledger_account (id, name, currency) VALUES ('ACC-FEES',       'Fee Income',         'INR');
INSERT INTO ledger_account (id, name, currency) VALUES ('ACC-PAYROLL',    'Payroll Funding',    'INR');
INSERT INTO ledger_account (id, name, currency) VALUES ('ACC-USD-001',    'USD Client Pool',    'USD');
