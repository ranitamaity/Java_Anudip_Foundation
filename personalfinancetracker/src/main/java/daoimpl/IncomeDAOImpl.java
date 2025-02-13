package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utility.ConnectionProvider;
import dao.IncomeDAO;
import model.Income;

public class IncomeDAOImpl implements IncomeDAO{
	public void addIncome(Income income) throws SQLException {
        String query = "INSERT INTO income (amount, category, date, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, income.getAmount());
            stmt.setString(2, income.getCategory());
            stmt.setDate(3, new java.sql.Date(income.getDate().getTime()));
            stmt.setString(4, income.getDescription());
            stmt.executeUpdate();
        }
    }

    public List<Income> getAllIncome() throws SQLException {
        List<Income> incomes = new ArrayList<>();
        String query = "SELECT * FROM income";
        try (Connection conn = ConnectionProvider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Income income = new Income(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getDate("date"),
                        rs.getString("description")
                );
                incomes.add(income);
            }
        }
        return incomes;
    }
    
    public double getTotalIncomeByMonth(int month, int year) throws SQLException {
        String query = "SELECT SUM(amount) AS total FROM income WHERE MONTH(date) = ? AND YEAR(date) = ?";
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
    
    public double getTotalIncomeByYear(int year) throws SQLException {
        String query = "SELECT SUM(amount) AS total FROM income WHERE YEAR(date) = ?";
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
    
    public List<Income> getIncomeByMonth(int month, int year) throws SQLException {
        String query = "SELECT * FROM income WHERE MONTH(date) = ? AND YEAR(date) = ?";
        List<Income> incomes = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    incomes.add(new Income(
                        rs.getInt("id"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getDate("date"),
                        rs.getString("description")
                    ));
                }
            }
        }
        return incomes;
    }

}
