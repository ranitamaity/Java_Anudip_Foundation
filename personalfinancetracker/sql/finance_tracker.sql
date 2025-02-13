create database finance_tracker;

use finance_tracker;

CREATE TABLE income (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    category VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE expense (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE NOT NULL,
    category VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE budget (
    id INT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL,
    month INT NOT NULL CHECK (month BETWEEN 1 AND 12),
    year INT NOT NULL CHECK (year > 1900),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    type ENUM('Income', 'Expense') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Fetch All Incomes
SELECT * FROM income ORDER BY date DESC;

-- Fetch All Expenses
SELECT * FROM expense ORDER BY date DESC;

-- Fetch All Category
SELECT * FROM category;

-- Fetch Monthly Budget
SELECT * FROM budget;

-- Total Income by Month
SELECT SUM(amount) AS total_income 
FROM income 
WHERE MONTH(date) = 12 AND YEAR(date) = 2024;

-- Total Expenses by Month
SELECT SUM(amount) AS total_expense 
FROM expense 
WHERE MONTH(date) = 12 AND YEAR(date) = 2024;

-- Budget Comparison
SELECT 
    b.category,
    b.amount AS budgeted_amount,
    IFNULL(SUM(e.amount), 0) AS spent_amount,
    (b.amount - IFNULL(SUM(e.amount), 0)) AS remaining_budget
FROM budget b
LEFT JOIN expense e 
    ON b.category = e.category 
    AND MONTH(e.date) = b.month 
    AND YEAR(e.date) = b.year
WHERE b.month = 12 AND b.year = 2024
GROUP BY b.category, b.amount;

-- Delete all Data
DELETE FROM income;
DELETE FROM expense;
DELETE FROM budget;


INSERT INTO category (name, type) VALUES
('Groceries', 'Expense'),
('Salary', 'Income'),
('Utilities', 'Expense');

select * from category;
SELECT * FROM category WHERE type = 'Expense';

