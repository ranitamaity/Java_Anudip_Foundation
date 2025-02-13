package dao;

import java.sql.SQLException;
import java.util.List;

import model.Income;

public interface IncomeDAO {
	public void addIncome(Income income) throws SQLException;
	public List<Income> getAllIncome() throws SQLException;
	public double getTotalIncomeByMonth(int month, int year) throws SQLException;
	public double getTotalIncomeByYear(int year) throws SQLException;
	public List<Income> getIncomeByMonth(int month, int year) throws SQLException;
}
