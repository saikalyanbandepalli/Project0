package org.example.DAO;

import org.example.DatabaseConnection;
import org.example.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private Connection connection = DatabaseConnection.getConnection();;

    public TaskDAO(Connection connection) {
        this.connection = connection;
    }

    public TaskDAO() {

    }

    public int predictTaskEffort(int taskId) {
        String sql = "SELECT AVG(completion_time) AS avg_completion_time FROM task WHERE taskdescription IN (SELECT taskdescription FROM task WHERE id = ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, taskId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int avgCompletionTime = rs.getInt("avg_completion_time");
                System.out.println("Predicted effort for task ID " + taskId + ": " + avgCompletionTime + " hours");
                return avgCompletionTime;
            } else {
                System.out.println("No historical data available to predict effort.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Task task = new Task();
            rs.getInt("id");
            rs.getString("taskdescription");
            rs.getString("taskstatus");
            rs.getInt("p_id");
            tasks.add(task);
        }
        return tasks;
    }

    public Task getTask(int id) throws SQLException {
        String query = "SELECT * FROM task WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Task(
                    rs.getInt("id"),
                    rs.getString("taskdescription"),
                    rs.getString("taskstatus"),
                    rs.getInt("p_id")
            );
        }
        return null;
    }

    public void saveTask(Task task) throws SQLException {
        String query = "INSERT INTO task (taskdescription, taskstatus, p_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, task.getTaskDescription());
        pstmt.setString(2, task.getStatus());
        pstmt.setInt(3, task.getP_id());
        pstmt.executeUpdate();
    }

    public void deleteTask(int id) throws SQLException {
        String query = "DELETE FROM task WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

