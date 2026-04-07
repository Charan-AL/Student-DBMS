package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://containers-us-west-XX.railway.app:6543/railway?useSSL=true&requireSSL=true&verifyServerCertificate=false";

    private static final String USER = "root";
    private static final String PASSWORD = "qNcOLWwtEhGCGDdPQzAFAeUzPxwfhxMJ";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | java.sql.SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            return null;
        }
    }
}