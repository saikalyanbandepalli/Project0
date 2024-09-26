package org.example.Service;

import org.example.DAO.ProjectDAO;
import org.example.model.Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    private final ProjectDAO projectDAO;

    public ProjectService(Connection connection) {
        this.projectDAO = new ProjectDAO(connection);
    }

    public List<Project> getAllProjects() throws SQLException {
        return projectDAO.getAllProjects();
    }

    public Project getProject(int id) throws SQLException {
        return projectDAO.getProject(id);
    }

    public void saveProject(Project project) throws SQLException {
        projectDAO.saveProject(project);
    }

    public void deleteProject(int id) throws SQLException {
        projectDAO.deleteProject(id);
    }
}

