package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Milestone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MilestoneDAO {
    private Connection connection = DatabaseConnection.getConnection();

    public MilestoneDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Milestone> getAllMilestones() throws SQLException {
        List<Milestone> milestones = new ArrayList<>();
        String query = "SELECT * FROM milestones";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Milestone milestone = new Milestone(
                    rs.getInt("id"),
                    rs.getString("milestonename"),
                    rs.getString("milestonedescription"),
                    rs.getString("milestonedate"),
                    rs.getString("createdate"),
                    rs.getInt("project_id")
            );
            milestones.add(milestone);
        }
        return milestones;
    }

    public Milestone getMilestone(int id) throws SQLException {
        String query = "SELECT * FROM milestones WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Milestone(
                    rs.getInt("id"),
                    rs.getString("milestonename"),
                    rs.getString("milestonedescription"),
                    rs.getString("milestonedate"),
                    rs.getString("createdate"),
                    rs.getInt("project_id")
            );
        }
        return null;
    }

    public void saveMilestone(Milestone milestone) throws SQLException {
        String query = "INSERT INTO milestones (milestonename, milestonedescription, milestonedate, createdate, project_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, milestone.getMilestoneName());
        pstmt.setString(2, milestone.getMilestoneDescription());
        pstmt.setString(3, milestone.getMilestoneDate());
        pstmt.setString(4, milestone.getCreatedAt());
        pstmt.setInt(5, milestone.getProject_id());
        pstmt.executeUpdate();
    }

    public void deleteMilestone(int id) throws SQLException {
        String query = "DELETE FROM milestones WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}
