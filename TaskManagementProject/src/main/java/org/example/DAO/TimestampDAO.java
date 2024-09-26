package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Timestamps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimestampDAO {
    private Connection connection= DatabaseConnection.getConnection();

    public TimestampDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Timestamps> getAllTimestamps() throws SQLException {
        List<Timestamps> timestamps = new ArrayList<>();
        String query = "SELECT * FROM timestamps";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Timestamps timestamp = new Timestamps(
                    rs.getInt("id"),
                    rs.getString("stampdescription"),
                    rs.getString("stamptime"),
                    rs.getInt("t_id")
            );
            timestamps.add(timestamp);
        }
        return timestamps;
    }

    public Timestamps getTimestamp(int id) throws SQLException {
        String query = "SELECT * FROM timestamps WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Timestamps(
                    rs.getInt("id"),
                    rs.getString("stampdescription"),
                    rs.getString("stamptime"),
                    rs.getInt("t_id")
            );
        }
        return null;
    }

    public void saveTimestamp(Timestamps timestamp) throws SQLException {
        String query = "INSERT INTO timestamps (stampdescription, stamptime, t_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, timestamp.getStampdescription());
        pstmt.setString(2, timestamp.getStamptime());
        pstmt.setInt(3, timestamp.getId());
        pstmt.executeUpdate();
    }

    public void deleteTimestamp(int id) throws SQLException {
        String query = "DELETE FROM timestamps WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}
