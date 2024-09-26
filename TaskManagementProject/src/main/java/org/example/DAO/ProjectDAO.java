package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private Connection connection = DatabaseConnection.getConnection();

    public ProjectDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Project project = new Project(
                    rs.getInt("id"),
                    rs.getString("projectName"),
                    rs.getString("clientName"),
                    rs.getString("startDate"),
                    rs.getString("endDate")
            );
            projects.add(project);
        }
        return projects;
    }

    public Project getProject(int id) throws SQLException {
        String query = "SELECT * FROM projects WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Project(
                    rs.getInt("id"),
                    rs.getString("projectName"),
                    rs.getString("clientName"),
                    rs.getString("startDate"),
                    rs.getString("endDate")
            );
        }
        return null;
    }

    public void saveProject(Project project) throws SQLException {
        String query = "INSERT INTO projects (projectName, clientName, startDate, endDate) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, project.getProjectName());
        pstmt.setString(2, project.getClientName());
        pstmt.setString(3, project.getStartDate());
        pstmt.setString(4, project.getEndDate());
        pstmt.executeUpdate();
    }

    public void deleteProject(int id) throws SQLException {
        String query = "DELETE FROM projects WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

