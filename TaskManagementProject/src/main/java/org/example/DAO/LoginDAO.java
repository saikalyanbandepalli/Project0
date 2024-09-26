package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
    private Connection connection = DatabaseConnection.getConnection();

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Login> getAllLogins() throws SQLException {
        List<Login> logins = new ArrayList<>();
        String query = "SELECT * FROM login";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Login login = new Login(rs.getString("username_u"), rs.getString("password_p"));
            logins.add(login);
        }
        return logins;
    }

    public Login getLogin(String username) throws SQLException {
        String query = "SELECT * FROM login WHERE username_u = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Login(rs.getString("username_u"), rs.getString("password_p"));
        }
        return null;
    }
    public void updateLogin(Login login) throws SQLException {
        String query = "UPDATE login SET password_p = ? WHERE username_u = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, login.getPassword());
            pstmt.setString(2, login.getUsername());
            pstmt.executeUpdate();
        }
    }

    public void saveLogin(Login login) throws SQLException {
        String query = "INSERT INTO login (username_u, password_p) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, login.getUsername());
        pstmt.setString(2, login.getPassword());
        pstmt.executeUpdate();
    }

    public void deleteLogin(String username) throws SQLException {
        String query = "DELETE FROM login WHERE username_u = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.executeUpdate();
    }
}

