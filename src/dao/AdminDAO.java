package dao;

import db.DBConnection;
import model.Admin;

import java.sql.*;

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
    // put the code here

}
