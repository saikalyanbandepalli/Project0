package org.example.Controller;

import org.example.Service.ProjectService;
import org.example.model.Project;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(Connection connection) {
        this.projectService = new ProjectService(connection);
    }

    public List<Project> getAllProjects() throws SQLException {
        return projectService.getAllProjects();
    }

    public Project getProject(int id) throws SQLException {
        return projectService.getProject(id);
    }

    public void saveProject(Project project) throws SQLException {
        projectService.saveProject(project);
    }

    public void deleteProject(int id) throws SQLException {
        projectService.deleteProject(id);
    }
}

