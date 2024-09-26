package org.example.Controller;


import org.example.model.Project;
import org.example.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamMemberController {
    private Connection connection;
    private static final Logger logger =  LoggerFactory.getLogger(ProjectManagerController.class);

    public TeamMemberController(Connection connection) {
        this.connection = connection;
    }

    public void logInSecurely(String usernameUu, String passwordPp) {
        String sql = "SELECT * FROM login WHERE username_u = ? AND password_p = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usernameUu);
            ps.setString(2, passwordPp);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getAssignedProjects(String usernameUu) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects JOIN task ON projects.id = task.project_id WHERE task.username_uu = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usernameUu);
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

    public List<Task> getAssignedTasks(String usernameUu) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task WHERE userna = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usernameUu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String taskDescription = rs.getString("taskdescription");
                String taskStatus = rs.getString("taskstatus");
                tasks.add(new Task(id, taskDescription, taskStatus, 0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        String sql = "UPDATE task SET taskstatus = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, taskId);
            ps.executeUpdate();
            System.out.println("Task status updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

