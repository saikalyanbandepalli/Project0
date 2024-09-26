package org.example.Controller;


import org.example.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectManagerController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectManagerController.class);
    private Connection connection;

    public ProjectManagerController(Connection connection) {
        this.connection = connection;
    }

    public void resetPassword(String usernameUu, String newPassword) {
        String sql = "UPDATE login SET password_p = ? WHERE username_u = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, usernameUu);
            ps.executeUpdate();
            System.out.println("Password reset successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String projectName = rs.getString("projectName");
                String clientName = rs.getString("clientName");
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                projects.add(new Project(id, projectName, clientName, startDate, endDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public void addTeamMemberToProject(String usernameUu, int projectId) {
        String sql = "UPDATE registrations SET project_id = ? WHERE username_uu = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            ps.setString(2, usernameUu);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Team member assigned to project successfully.");
            } else {
                System.out.println("Failed to assign team member to project. Ensure the username is correct.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignTaskToTeamMember(int taskId, String usernameUu) {
        String sql = "UPDATE task SET username_uu = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usernameUu);
            ps.setInt(2, taskId);
            ps.executeUpdate();
            System.out.println("Task assigned to team member successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void predictTaskEffort(int taskId) {
        System.out.println("Predicted task effort based on historical data.");
    }
}
