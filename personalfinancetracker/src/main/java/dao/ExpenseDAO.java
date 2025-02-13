package dao;

import java.sql.SQLException;
import java.util.List;

import model.Expense;

public interface ExpenseDAO {
	public void addExpense(Expense expense) throws SQLException;
	public List<Expense> getAllExpenses() throws SQLException;
	public double getTotalExpenseByMonth(int month, int year) throws SQLException;
	public double getTotalExpenseByYear(int year) throws SQLException;
	public double getTotalExpenseByCategoryAndPeriod(String category, int month, int year) throws SQLException;
	public List<Expense> getExpensesByMonth(int month, int year) throws SQLException;
}
