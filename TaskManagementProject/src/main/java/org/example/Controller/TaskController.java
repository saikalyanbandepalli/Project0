package org.example.Controller;

import org.example.Service.TaskService;
import org.example.model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TaskController {
    private final TaskService taskService;

    public TaskController(Connection connection) {
        this.taskService = new TaskService(connection);
    }

    public List<Task> getAllTasks() throws SQLException {
        return taskService.getAllTasks();
    }

    public Task getTask(int id) throws SQLException {
        return taskService.getTask(id);
    }

    public void saveTask(Task task) throws SQLException {
        taskService.saveTask(task);
    }

    public void deleteTask(int id) throws SQLException {
        taskService.deleteTask(id);
    }
}

