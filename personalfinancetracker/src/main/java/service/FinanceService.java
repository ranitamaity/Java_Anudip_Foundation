package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import daoimpl.BudgetDAOImpl;
import daoimpl.CategoryDAOImpl;
import daoimpl.ExpenseDAOImpl;
import daoimpl.IncomeDAOImpl;
import model.Budget;
import model.Category;
import model.Expense;
import model.Income;

public class FinanceService {
    private IncomeDAOImpl incomeDAOImpl;
    private ExpenseDAOImpl expenseDAOImpl;
    private BudgetDAOImpl budgetDAOImpl;
    private CategoryDAOImpl categoryDAOImpl;

    // Constructor
    public FinanceService() {
        this.incomeDAOImpl = new IncomeDAOImpl();
        this.expenseDAOImpl = new ExpenseDAOImpl();
        this.budgetDAOImpl = new BudgetDAOImpl();
        this.categoryDAOImpl = new CategoryDAOImpl();
    }

    public void addIncome(Income income) {
        try {
        	incomeDAOImpl.addIncome(income);
        } catch (SQLException e) {
            System.err.println("\nError adding income : " + e.getMessage());
        }
    }

    public void generateMonthlyIncomeReport(int month, int year) {
        try {
            List<Income> incomes = incomeDAOImpl.getIncomeByMonth(month, year); // Fetch incomes for the month
            double totalIncome = 0.0;

            if (incomes.isEmpty()) {
                System.out.println("\nNo income records found for " + month + "/" + year);
                return;
            }

            // Print the header for the income table
            System.out.println("\n--- Monthly Income Report for " + month + "/" + year + " ---");
            System.out.printf("%-5s %-15s %-15s %-25s %-30s\n", "ID", "Amount", "Category", "Date", "Description");
            System.out.println("--------------------------------------------------------------------------");

            // Print each income record and calculate the total income
            for (Income income : incomes) {
                totalIncome += income.getAmount();
                System.out.printf("%-5d %-15.2f %-15s %-25s %-30s\n", 
                    income.getId(), 
                    income.getAmount(), 
                    income.getCategory(), 
                    income.getDate(), 
                    income.getDescription());
            }

            System.out.println("--------------------------------------------------------------------------");
            System.out.printf("Total Income for %d/%d: %.2f\n", month, year, totalIncome);

        } catch (SQLException e) {
            System.err.println("\nError generating income report: " + e.getMessage());
        }
    }

    
    public void displayIncome() {
        try {
            List<Income> incomes = incomeDAOImpl.getAllIncome();

            if (incomes.isEmpty()) {
                System.out.println("\nNo income records found.");
                return;
            }

            System.out.println("\n--- Your Income Records ---\n");
            System.out.printf("%-5s %-15s %-20s %-20s %-30s\n", "ID", "Amount", "Category", "Date", "Description");
            System.out.println("-------------------------------------------------------------------------------------------");

            double totalIncome = 0.0;

            for (Income income : incomes) {
                System.out.printf("%-5d %-15.2f %-20s %-20s %-30s\n",
                        income.getId(),
                        income.getAmount(),
                        income.getCategory(),
                        income.getDate(),
                        income.getDescription());
                totalIncome += income.getAmount();
            }

            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("Total Income: %.2f\n", totalIncome);

        } catch (SQLException e) {
            System.err.println("\nError fetching income records: " + e.getMessage());
        }
    }

    
    public void addExpense(Expense expense) {
        try {
            expenseDAOImpl.addExpense(expense);
        } catch (SQLException e) {
            System.err.println("\nError adding expense : " + e.getMessage());
        }
    }

    public void displayExpenses() {
        try {
            List<Expense> expenses = expenseDAOImpl.getAllExpenses();

            if (expenses.isEmpty()) {
                System.out.println("\nNo expense records found.");
                return;
            }

            System.out.println("\n--- Your Expense Records ---\n");
            System.out.printf("%-5s %-15s %-20s %-20s %-30s\n", "ID", "Amount", "Category", "Date", "Description");
            System.out.println("-------------------------------------------------------------------------------------------");

            double totalExpense = 0.0;

            for (Expense expense : expenses) {
                System.out.printf("%-5d %-15.2f %-20s %-20s %-30s\n",
                        expense.getId(),
                        expense.getAmount(),
                        expense.getCategory(),
                        expense.getDate(),
                        expense.getDescription());
                totalExpense += expense.getAmount();
            }

            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("Total Expense: %.2f\n", totalExpense);

        } catch (SQLException e) {
            System.err.println("\nError fetching expense records: " + e.getMessage());
        }
    }

    public void generateMonthlyExpenseReport(int month, int year) {
        try {
            List<Expense> monthlyExpenses = expenseDAOImpl.getExpensesByMonth(month, year);
            if (monthlyExpenses.isEmpty()) {
                System.out.println("\nNo expenses found for " + month + "/" + year + ".");
                return;
            }

            System.out.println("\n--- Monthly Expense Report for " + month + "/" + year + " ---");
            System.out.println("+----+-------------+---------------+------------+-------------------+");
            System.out.printf("| %-2s | %-11s | %-13s | %-10s | %-17s |\n", 
                              "ID", "Amount", "Category", "Date", "Description");
            System.out.println("+----+-------------+---------------+------------+-------------------+");

            double totalExpense = 0.0;
            for (Expense expense : monthlyExpenses) {
                System.out.printf("| %-2d | %-11.2f | %-13s | %-10s | %-17s |\n",
                                  expense.getId(), 
                                  expense.getAmount(), 
                                  expense.getCategory(), 
                                  expense.getDate(), 
                                  expense.getDescription());
                totalExpense += expense.getAmount();
            }

            System.out.println("+----+-------------+---------------+------------+-------------------+");
            System.out.printf("Total Monthly Expense: %.2f\n", totalExpense);

        } catch (SQLException e) {
            System.err.println("Error generating monthly expense report: " + e.getMessage());
        }
    }

    
    public void addCategory(Category category) {
        try {
        	categoryDAOImpl.addCategory(category);
            System.out.println("\nCategory added successfully : " + category.getName());
        } catch (SQLException e) {
            System.err.println("\nError adding category : " + e.getMessage());
        }
    }

    public void listAllCategories() {
        try {
            List<Category> categories = categoryDAOImpl.getAllCategories();

            if (categories.isEmpty()) {
                System.out.println("\nNo categories found...");
                return;
            }

            System.out.println("\n--- Category Report ---\n");
            System.out.printf("%-5s %-30s %-15s\n", "ID", "Category Name", "Type");
            System.out.println("-----------------------------------------------");

            for (Category category : categories) {
                System.out.printf("%-5d %-30s %-15s\n",
                        category.getId(),
                        category.getName(),
                        category.getType());
            }

            System.out.println("-----------------------------------------------");
            System.out.printf("Total Categories: %d\n", categories.size());
        } catch (SQLException e) {
            System.err.println("\nError retrieving categories: " + e.getMessage());
        }
    }


    
    public void generateMonthlyReport(int month, int year) {
        try {
            double totalIncome = incomeDAOImpl.getTotalIncomeByMonth(month, year);
            double totalExpense = expenseDAOImpl.getTotalExpenseByMonth(month, year);
            double savings = totalIncome - totalExpense;

            System.out.println("\n-----------------------------------");
            System.out.printf("  Monthly Report: %02d/%d  \n", month, year);
            System.out.println("-----------------------------------");
            System.out.printf("%-20s: %.2f\n", "Total Income", totalIncome);
            System.out.printf("%-20s: %.2f\n", "Total Expenses", totalExpense);
            System.out.printf("%-20s: %.2f\n", "Savings", savings);
            System.out.println("-----------------------------------");

        } catch (SQLException e) {
            System.err.println("\nError generating monthly report: " + e.getMessage());
        }
    }

    public void generateYearlyReport(int year) {
        try {
            double totalIncome = incomeDAOImpl.getTotalIncomeByYear(year);
            double totalExpense = expenseDAOImpl.getTotalExpenseByYear(year);
            double savings = totalIncome - totalExpense;

            System.out.println("\n-----------------------------------");
            System.out.printf("  Yearly Report: %d  \n", year);
            System.out.println("-----------------------------------");
            System.out.printf("%-20s: %.2f\n", "Total Incomes (" + year + ")", totalIncome);
            System.out.printf("%-20s: %.2f\n", "Total Expenses (" + year + ")", totalExpense);
            System.out.printf("%-20s: %.2f\n", "Savings (" + year + ")", savings);
            System.out.println("-----------------------------------");

        } catch (SQLException e) {
            System.err.println("\nError generating yearly report: " + e.getMessage());
        }
    }

    
    public void setBudget(Budget budget) {
        try {
        	budgetDAOImpl.setBudget(budget); // Calls the DAO to save the budget in the database
            System.out.println("\nBudget set successfully for category : " + budget.getCategory() +
                    ", Amount : " + budget.getAmount() +
                    ", Month : " + budget.getMonth() +
                    ", Year : " + budget.getYear());
        } catch (SQLException e) {
            System.err.println("\nError setting budget : " + e.getMessage());
        }
    }
    
    public void checkBudgetNotifications(int month, int year) {
        try {
            Optional<Budget> budgetOpt = budgetDAOImpl.getBudgetForMonth(month, year);
            if (budgetOpt.isPresent()) {
                Budget budget = budgetOpt.get();
                double totalExpenses = expenseDAOImpl.getTotalExpenseByMonth(month, year);
                if (totalExpenses > budget.getAmount()) {
                    System.err.println("\n[Notification] You have exceeded your budget for " + month + "/" + year + ".");
                } else {
                    System.out.println("\n[Notification] Your expenses are within the budget...");
                }
            } else {
                System.out.println("\n[Notification] No budget set for this month...");
            }
        } catch (SQLException e) {
            System.err.println("\nError checking budget notifications : " + e.getMessage());
        }
    }

    public List<Budget> getAllBudgets() {
        try {
            return budgetDAOImpl.getAllBudgets(); // Call DAO to fetch budgets
        } catch (SQLException e) {
            System.err.println("Error retrieving budgets: " + e.getMessage());
            return new ArrayList<>(); // Return empty list in case of failure
        }
    }
    
    public double getTotalExpenseByCategoryAndPeriod(String category, int month, int year) {
        try {
            return expenseDAOImpl.getTotalExpenseByCategoryAndPeriod(category, month, year);
        } catch (SQLException e) {
            System.err.println("Error retrieving expenses: " + e.getMessage());
            return 0.0;
        }
    }


}