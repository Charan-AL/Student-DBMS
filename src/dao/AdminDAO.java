package dao;

import db.DBConnection;
import java.sql.*;
import model.Admin;

public class AdminDAO {

    private String lastErrorMessage;

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    // Registration admin
    public boolean register(Admin admin) {
        lastErrorMessage = null;

        String sql = "Insert into admin (username,password) values(?,?)";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            lastErrorMessage = "Username already exists.";
            return false;
        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            return false;
        }
    }

    // login admin
    public boolean login(String username, String password) {
        lastErrorMessage = null;

        String sql = "SELECT 1 FROM admin WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            return false;
        }
    }

}
