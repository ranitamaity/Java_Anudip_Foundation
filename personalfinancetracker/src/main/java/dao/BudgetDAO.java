package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import model.Budget;

public interface BudgetDAO {
	public void setBudget(Budget budget) throws SQLException;
	public Optional<Budget> getBudgetForMonth(int month, int year) throws SQLException;
	public List<Budget> getAllBudgets() throws SQLException;
}
