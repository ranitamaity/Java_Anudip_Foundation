package dao;

import java.sql.SQLException;
import java.util.List;

import model.Category;

public interface CategoryDAO {
	public void addCategory(Category category) throws SQLException;
	public List<Category> getAllCategories() throws SQLException;
	public List<Category> getCategoriesByType(String type) throws SQLException;
}
