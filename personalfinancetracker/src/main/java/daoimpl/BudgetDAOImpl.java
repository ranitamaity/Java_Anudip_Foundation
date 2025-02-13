package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dao.BudgetDAO;
import utility.ConnectionProvider;
import model.Budget;

public class BudgetDAOImpl implements BudgetDAO {
	
	@Override
	public void setBudget(Budget budget) throws SQLException {
        String query = "INSERT INTO budget (category, amount, month, year) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, budget.getCategory());
            stmt.setDouble(2, budget.getAmount());
            stmt.setInt(3, budget.getMonth());
            stmt.setInt(4, budget.getYear());
            stmt.executeUpdate();
        }
    }

	@Override
    public Optional<Budget> getBudgetForMonth(int month, int year) throws SQLException {
        String query = "SELECT * FROM budget WHERE month = ? AND year = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Budget(
                            rs.getInt("id"),
                            rs.getString("category"),
                            rs.getDouble("amount"),
                            rs.getInt("month"),
                            rs.getInt("year")
                    ));
                }
            }
        }
        return Optional.empty();
    }
	
	//Added
	public List<Budget> getAllBudgets() throws SQLException {
	    String query = "SELECT * FROM budget";
	    List<Budget> budgets = new ArrayList<>();

	    try (Connection conn = ConnectionProvider.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            budgets.add(new Budget(
	                    rs.getString("category"),
	                    rs.getDouble("amount"),
	                    rs.getInt("month"),
	                    rs.getInt("year")
	            ));
	        }
	    }

	    return budgets;
	}


}
