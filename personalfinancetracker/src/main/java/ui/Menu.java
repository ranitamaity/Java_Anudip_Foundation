package ui;

import model.Income;
import model.Expense;
import model.Budget;
import model.Category;

import service.FinanceService;

import utility.ValidationUtil;

import java.io.IOException;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private FinanceService service;
    private BufferedReader reader;

    public Menu() {
        service = new FinanceService();
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void displayMenu() throws IOException {
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n--- Personal Finance Tracker ---\n");
            System.out.println("\t1. Manage Incomes");
            System.out.println("\t2. Manage Expenses");
            System.out.println("\t3. Manage Categories");
            System.out.println("\t4. Reports");
            System.out.println("\t5. Budget Management");
            System.out.println("\t6. Exit");
            System.out.print("\nChoose an option: ");

            String input = reader.readLine();
            int mainChoice;

            try {
                mainChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (mainChoice) {
                case 1:
                    manageIncomes();
                    break;

                case 2:
                    manageExpenses();
                    break;

                case 3:
                    manageCategories();
                    break;

                case 4:
                    manageReports();
                    break;

                case 5:
                    manageBudget();
                    break;

                case 6:
                    System.out.println("\nExited Successfully. Please visit us again...");
                    continueMenu = false;
                    break;

                default:
                    System.err.println("Invalid choice. Try again...");
            }

            if (continueMenu) {
                System.out.print("\nDo you want to continue to the main menu? (y/n): ");
                String response = reader.readLine().trim().toLowerCase();
                if (response.equals("n")) {
                    System.out.println("\nExited Successfully. Please visit us again...");
                    continueMenu = false;
                } else if (!response.equals("y")) {
                    System.err.println("Invalid input! Exiting the program...");
                    continueMenu = false;
                }
            }
        }
    }

    private void manageIncomes() throws IOException {
        System.out.println("\n--- Income Management ---\n");
        System.out.println("\t1. Add Income");
        System.out.println("\t2. View Monthly Incomes");
        System.out.println("\t3. View All Incomes");
        
        System.out.print("\nChoose an option: ");
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                addIncome();
                break;
            case 2:
            	monthlyIncome();
            	break;
            case 3:
                service.displayIncome();
                break;
            default:
                System.err.println("Invalid choice. Returning to main menu...");
        }
    }

    private void manageExpenses() throws IOException {
        System.out.println("\n--- Expense Management ---\n");
        System.out.println("\t1. Add Expense");
        System.out.println("\t2. View Monthly Expense");
        System.out.println("\t3. View All Expenses");
        System.out.print("\nChoose an option: ");
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                addExpense();
                break;
            case 2:
            	monthlyExpense();
            	break;
            case 3:
                service.displayExpenses();
                break;
            default:
                System.err.println("Invalid choice. Returning to main menu...");
        }
    }

    private void manageCategories() throws IOException {
        System.out.println("\n--- Category Management ---\n");
        System.out.println("\t1. Add Category");
        System.out.println("\t2. View Categories");
        System.out.print("\nChoose an option: ");
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                addCategory();
                break;
            case 2:
                listCategories();
                break;
            default:
                System.err.println("Invalid choice. Returning to main menu...");
        }
    }

    private void manageReports() throws IOException {
        System.out.println("\n--- Reports ---\n");
        System.out.println("\t1. Generate Monthly Report");
        System.out.println("\t2. Generate Yearly Report");
        System.out.print("\nChoose an option: ");
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                generateMonthlyReport();
                break;
            case 2:
                generateYearlyReport();
                break;
            default:
                System.err.println("Invalid choice. Returning to main menu...");
        }
    }

    private void manageBudget() throws IOException {
        System.out.println("\n--- Budget Management ---\n");
        System.out.println("\t1. Set Budget");
        System.out.println("\t2. View Budget Notifications");
        System.out.print("\nChoose an option: ");
        int choice = Integer.parseInt(reader.readLine());

        switch (choice) {
            case 1:
                setBudget();
                break;
            case 2:
                viewNotifications();
                break;
            default:
                System.err.println("Invalid choice. Returning to main menu...");
        }
    }

    private void addIncome() throws IOException {
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(reader.readLine());

        System.out.print("Enter category: ");
        String category = reader.readLine().trim().toLowerCase();  // Convert to lowercase

        System.out.print("Enter description: ");
        String description = reader.readLine();

        service.addIncome(new Income(amount, category, new Date(), description));
        System.out.println("\nIncome added successfully!");
    }

    private void monthlyIncome() throws IOException{
    	System.out.print("Enter month (1-12): ");
        int month = Integer.parseInt(reader.readLine());

        System.out.print("Enter year (e.g., 2024): ");
        int year = Integer.parseInt(reader.readLine());
        
        service.generateMonthlyIncomeReport(month, year);
    }
    
    private void addExpense() throws IOException {
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(reader.readLine());

        System.out.print("Enter category: ");
        String category = reader.readLine().trim().toLowerCase();  // Convert to lowercase

        System.out.print("Enter description: ");
        String description = reader.readLine();

        service.addExpense(new Expense(amount, category, new Date(), description));
        System.out.println("\nExpense added successfully!");
    }

    private void monthlyExpense() throws IOException{
    	System.out.print("Enter month (1-12): ");
        int month = Integer.parseInt(reader.readLine());

        System.out.print("Enter year (e.g., 2024): ");
        int year = Integer.parseInt(reader.readLine());
        
        service.generateMonthlyExpenseReport(month, year);
    }

    private void addCategory() throws IOException {
    	System.out.print("Enter category type (Income/Expense): ");
        String type = reader.readLine();
        
        System.out.print("Enter category name: ");
        String name = reader.readLine().trim().toLowerCase();  // Convert to lowercase

        service.addCategory(new Category(name, type));
        System.out.println("\nCategory added successfully!");
    }

    private void listCategories() {
        System.out.println("\n--- List of All Categories ---\n");
        service.listAllCategories();
    }

    private void generateMonthlyReport() throws IOException {
        System.out.print("Enter month (1-12): ");
        int month = Integer.parseInt(reader.readLine());

        System.out.print("Enter year (e.g., 2024): ");
        int year = Integer.parseInt(reader.readLine());

        service.generateMonthlyReport(month, year);
    }

    private void generateYearlyReport() throws IOException {
        System.out.print("Enter year (e.g., 2024): ");
        int year = Integer.parseInt(reader.readLine());

        service.generateYearlyReport(year);
    }

    private void setBudget() throws IOException {
        System.out.print("Enter category: ");
        String category = reader.readLine().trim().toLowerCase();  // Convert to lowercase

        System.out.print("Enter budget amount: ");
        double amount = Double.parseDouble(reader.readLine());

        System.out.print("Enter month (1-12): ");
        int month = Integer.parseInt(reader.readLine());

        System.out.print("Enter year (e.g., 2024): ");
        int year = Integer.parseInt(reader.readLine());

        service.setBudget(new Budget(category, amount, month, year));
        System.out.println("\nBudget set successfully!");
    }
 
    private void viewNotifications() throws IOException {
        System.out.println("\n--- View Budget Notifications ---\n");

        // Call the service method to retrieve all budgets and their details
        List<Budget> budgets = service.getAllBudgets();

        if (budgets.isEmpty()) {
            System.out.println("No budgets have been set yet.");
            return;
        }

        System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", 
                          "Category", "Budget Limit", "Expense Incurred", 
                          "Remaining Budget", "Status");

        for (Budget budget : budgets) {
            // Get total expenses for the category and month/year
            double totalExpenses = service.getTotalExpenseByCategoryAndPeriod(
                    budget.getCategory(), budget.getMonth(), budget.getYear());

            // Calculate remaining budget and status
            double remainingBudget = budget.getAmount() - totalExpenses;
            String status = (remainingBudget < 0) ? "Exceeded" : "Within Budget";

            // Display details
            System.out.printf("%-20s %-20.2f %-20.2f %-20.2f %-20s\n",
                    budget.getCategory(), budget.getAmount(), totalExpenses,
                    remainingBudget, status);
        }
    }

}
