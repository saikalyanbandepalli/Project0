package org.example.DAO;

import org.example.model.Registration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.DatabaseConnection;
public class RegistrationDAO {
    private Connection connection = DatabaseConnection.getConnection();;

    public RegistrationDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Registration> getAllRegistrations() throws SQLException {
        List<Registration> registrations = new ArrayList<>();
        String query = "SELECT * FROM registrations";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Registration registration = new Registration(
                    rs.getInt("id"),
                    rs.getString("username_uu"),
                    rs.getString("email"),
                    rs.getString("password_pp"),
                    rs.getString("regrole"),
                    rs.getString("regstatus"),
                    rs.getInt("mile_id")
            );
            registrations.add(registration);
        }
        return registrations;
    }
    public void updateRegistration(Registration registration) throws SQLException {
        String query = "UPDATE registrations SET username_uu = ?, email = ?, password_pp = ?, regrole = ?, regstatus = ?, mile_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, registration.getUsername_uu());
            pstmt.setString(2, registration.getEmail());
            pstmt.setString(3, registration.getPassword_pp());
            pstmt.setString(4, registration.getRegrole());
            pstmt.setString(5, registration.getRegstatus());
            pstmt.setInt(6, registration.getMile_id());
            pstmt.setInt(7, registration.getId());
            pstmt.executeUpdate();
        }
    }
    public Registration getRegistration(int id) throws SQLException {
        String query = "SELECT * FROM registrations WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Registration(
                    rs.getInt("id"),
                    rs.getString("username_uu"),
                    rs.getString("email"),
                    rs.getString("password_pp"),
                    rs.getString("regrole"),
                    rs.getString("regstatus"),
                    rs.getInt("mile_id")
            );
        }
        return null;
    }

    public void saveRegistration(Registration registration) throws SQLException {

            String query = "INSERT INTO registrations (username_uu, email, password_pp, regrole, regstatus, mile_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, registration.getUsername_uu());
            pstmt.setString(2, registration.getEmail());
            pstmt.setString(3, registration.getPassword_pp());
            pstmt.setString(4, registration.getRegrole());
            pstmt.setString(5, registration.getRegstatus());
            pstmt.setInt(6, registration.getMile_id());
            pstmt.executeUpdate();

    }

    public void deleteRegistration(int id) throws SQLException {
        String query = "DELETE FROM registrations WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
    public String returnrole(String username) throws SQLException {
        String query = "SELECT regrole FROM registrations WHERE username_uu = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1,username);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return rs.getString("regrole");
        }
        return null;
    }
}

