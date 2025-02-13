package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import utility.ConnectionProvider;
import model.Category;

public class CategoryDAOImpl implements CategoryDAO{
	
	public void addCategory(Category category) throws SQLException {
        String query = "INSERT INTO category (name, type) VALUES (?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getType());
            stmt.executeUpdate();
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection conn = ConnectionProvider.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type")
                ));
            }
        }
        return categories;
    }

    public List<Category> getCategoriesByType(String type) throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category WHERE type = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(new Category(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("type")
                    ));
                }
            }
        }
        return categories;
    }
}
