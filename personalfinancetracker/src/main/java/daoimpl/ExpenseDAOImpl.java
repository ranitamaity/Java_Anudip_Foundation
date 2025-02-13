package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import dao.ExpenseDAO;
import utility.ConnectionProvider;
import model.Expense;

public class ExpenseDAOImpl implements ExpenseDAO{

	public void addExpense(Expense expense) throws SQLException {
        String query = "INSERT INTO expense (amount, category, date, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, expense.getAmount());
            stmt.setString(2, expense.getCategory());
            stmt.setDate(3, new java.sql.Date(expense.getDate().getTime()));
            stmt.setString(4, expense.getDescription());
            stmt.executeUpdate();
        }
    }

    public List<Expense> getAllExpenses() throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expense";
        try (Connection conn = ConnectionProvider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getDate("date"),
                        rs.getString("description")
                );
                expenses.add(expense);
            }
        }
        return expenses;
    }
    
    public double getTotalExpenseByMonth(int month, int year) throws SQLException {
        String query = "SELECT SUM(amount) AS total FROM expense WHERE MONTH(date) = ? AND YEAR(date) = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }
        return 0.0;
    }
    
    public double getTotalExpenseByYear(int year) throws SQLException {
        String query = "SELECT SUM(amount) AS total FROM expense WHERE YEAR(date) = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, year);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }
        return 0.0;
    }
    // Added
    public double getTotalExpenseByCategoryAndPeriod(String category, int month, int year) throws SQLException {
        String query = "SELECT SUM(amount) AS total FROM expense WHERE category = ? AND MONTH(date) = ? AND YEAR(date) = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }

        return 0.0; // Return 0 if no expenses are found
    }
    
    public List<Expense> getExpensesByMonth(int month, int year) throws SQLException {
        String query = "SELECT * FROM expense WHERE MONTH(date) = ? AND YEAR(date) = ?";
        List<Expense> expenses = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Expense expense = new Expense(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getDate("date"),
                        rs.getString("description")
                    );
                    expenses.add(expense);
                }
            }
        }
        return expenses;
    }


}
