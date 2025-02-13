

# 📊 Personal Finance Tracker  
**Manage your finances effortlessly with a streamlined Java application**  

---

## 🚀 **Overview**  
The **Personal Finance Tracker** is a console-based application designed to simplify financial management. Built with **Java**, **JDBC**, and **MySQL**, it provides essential features to track income, expenses, and budgets. Gain control of your finances, monitor spending habits, and achieve your financial goals efficiently.  

---

## 🌟 **Features**  
- **💰 Income Management**:  
  - Add, update, delete, and view income entries.  
  - Track sources of income like salary, freelance, and more.

- **🛒 Expense Management**:  
  - Categorize and track expenses by type (e.g., groceries, rent, entertainment).  
  - View detailed and categorized expense records.

- **📅 Budget Management**:  
  - Set monthly budgets.  
  - Monitor actual spending against target budgets.  
  - Alerts for overspending.
    
- **📅 Category Management**:  
  - Define custom income and expense categories.
  - View all available categories for streamlined financial tracking.

- **📈 Financial Reports**:  
  - Generate monthly summaries of income, expenses, and savings.  
  - Visualize spending trends (via ASCII charts or third-party libraries).  

---

## 🛠️ **Tech Stack**  
- **Programming Language**: Java (Object-Oriented Programming)  
- **Database**: MySQL (Persistent data storage)  
- **Database Connectivity**: JDBC (Java Database Connectivity)  
- **Design Principles**: Modular architecture, encapsulation, and clean code  

---

## 📂 **Project Structure**  

```plaintext
├── src/
│   ├── dao/
│   │   ├── IncomeDAO.java         # Data Access interface for income
│   │   ├── ExpenseDAO.java        # Data Access interface for expenses
│   │   ├── BudgetDAO.java         # Data Access interface for budgets
│   │   ├── CategoryDAO.java       # Data Access interface for categories
│   ├── daoImpl/
│   │   ├── IncomeDAOImpl.java     # Data Access Object for income
│   │   ├── ExpenseDAOImpl.java    # Data Access Object for expenses
│   │   ├── BudgetDAOImpl.java     # Data Access Object for budgets
│   │   ├── CategoryDAOImpl.java   # Data Access Object for categories
│   ├── model/
│   │   ├── Income.java            # Income entity class
│   │   ├── Expense.java           # Expense entity class
│   │   ├── Budget.java            # Budget entity class
│   │   ├── Category.java          # Category entity class
│   ├── service/
│   │   ├── FinanceService.java    # Business logic layer
│   ├── ui/
│   │   ├── Menu.java              # User interface (console-based)
│   ├── utility/
│   │   ├── ConnectionProvider.java # Utility for MySQL connection
│   ├── App.java                   # Application entry point
├── README.md                      # Documentation (this file)
└── pom.xml                        # Maven dependencies (optional)
```

---

## 🔧 **Setup and Installation**  

### **1. Prerequisites**  
- Java Development Kit (JDK) 8 or higher  
- MySQL Server  
- MySQL Connector JAR (for JDBC connectivity)  

### **2. Clone the Repository**  
```bash
git clone https://github.com/your-username/personal-finance-tracker.git
cd personal-finance-tracker
```

### **3. Configure the Database**  
- Create a database named `personal_finance_tracker`.  
- Import the schema:
  
  ```sql
  CREATE DATABASE personal_finance_tracker;
  USE personal_finance_tracker;
  
  -- Create income table
  CREATE TABLE income (
      id INT AUTO_INCREMENT PRIMARY KEY,
      amount DECIMAL(10, 2) NOT NULL,
      source VARCHAR(255) NOT NULL,
      date DATE NOT NULL,
      description TEXT
  );
  
  -- Create expense table
  CREATE TABLE expense (
      id INT AUTO_INCREMENT PRIMARY KEY,
      amount DECIMAL(10, 2) NOT NULL,
      category_id INT NOT NULL,
      date DATE NOT NULL,
      description TEXT,
      FOREIGN KEY (category_id) REFERENCES category(id)
  );
  
  -- Create category table
  CREATE TABLE category (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(255) NOT NULL UNIQUE
  );
  
  -- Create budget table
  CREATE TABLE budget (
      id INT AUTO_INCREMENT PRIMARY KEY,
      month INT NOT NULL CHECK (month BETWEEN 1 AND 12),
      year INT NOT NULL,
      target_amount DECIMAL(10, 2) NOT NULL,
      actual_amount DECIMAL(10, 2) NOT NULL DEFAULT 0
  );
  ```

### **4. Add MySQL Connector**  
For **Maven projects**, add this dependency to `pom.xml`:  
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

For **non-Maven projects**, download the [MySQL Connector JAR](https://dev.mysql.com/downloads/connector/j/) and add it to your classpath.

### **5. Run the Application**  
- Compile and run the project:
  ```bash
  javac -d bin src/**/*.java
  java -cp bin:lib/mysql-connector-java-8.0.33.jar Main
  ```

---

## 📖 **Usage Guide**
Main Functionalities
- **Add Income**: Add your income details (amount, source, and description).
- **Add Expense**: Record your expenses and categorize them (e.g., "Groceries", "Rent", etc.).
- **Manage Categories**: Add, update, or delete expense categories to organize your spending.
- **Set Budgets**: Set monthly budget goals for categories and track your actual spending.
- **Generate Reports**: View detailed reports of your financial activity for the month, including savings, income vs. expenses, and category breakdowns.

---

## 📈 **Future Enhancements**  
- **Cloud Integration**: Sync financial data across devices.  
- **Advanced Reporting**: Export reports in CSV/PDF formats.  
- **Mobile App**: Create a companion app using JavaFX or Android.  
- **Alerts and Notifications**: Email or SMS reminders for budget breaches.  

---

## 💡 **Contributing**  
We welcome contributions!  
1. Fork the repository.  
2. Create a feature branch (`git checkout -b feature-name`).  
3. Commit changes (`git commit -m "Added feature"`).  
4. Push to your fork and submit a Pull Request.  

---

## 🛡️ **License**  
This project is licensed under the [MIT License](LICENSE).  

---

## ✨ **Acknowledgments**  
- Java community for robust documentation.  
- MySQL for seamless database integration.  

---

Feel free to modify this `README.md` based on your specific customizations or additional features. Let me know if you’d like any further refinements!
