package org.example.Service;

import org.example.DAO.TaskDAO;
import org.example.model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


    public class TaskService {
        private final TaskDAO taskDAO;

        public TaskService(Connection connection) {
            this.taskDAO = new TaskDAO(connection);
        }

        public List<Task> getAllTasks() throws SQLException {
            return taskDAO.getAllTasks();
        }

        public Task getTask(int id) throws SQLException {
            return taskDAO.getTask(id);
        }

        public void saveTask(Task task) throws SQLException {
            taskDAO.saveTask(task);
        }

        public void deleteTask(int id) throws SQLException {
            taskDAO.deleteTask(id);
        }
    }


