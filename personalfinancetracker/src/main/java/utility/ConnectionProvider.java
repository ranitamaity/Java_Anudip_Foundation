package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database_name"; // Replace with MySQL database name 
    private static final String DB_USER = "username"; // Replace with MySQL Username 
    private static final String DB_PASSWORD = "password"; // Replace with MySQL password

    // Method to establish and return a connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Optional: Method to test the connection
    public static void main(String[] args) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            if (connection != null) {
                System.out.println("\nDatabase connection established successfully!!!");
            }
        } catch (SQLException e) {
            System.err.println("\nError establishing database connection : " + e.getMessage());
        }
    }
}
