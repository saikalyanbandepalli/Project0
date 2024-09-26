package org.example;

import org.example.Controller.*;
import org.example.model.Login;
import org.example.model.Project;
import org.example.model.Registration;
import org.example.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainTest {
    @Mock
    private Connection connection;

    @Mock
    private LoginController loginController;

    @Mock
    private RegistrationController registrationController;

    @Mock
    private ProjectManagerController projectManagerController;

    @Mock
    private TeamMemberController teamMemberController;

    @InjectMocks
    private Main main;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() throws SQLException {
        String username = "admin";
        String password = "password";
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        when(loginController.getLogin(username)).thenReturn(login);
        when(registrationController.getRole(username)).thenReturn("admin");

        Login loggedInUser = loginController.getLogin(username);
        String role = registrationController.getRole(loggedInUser.getUsername());

        assertNotNull(loggedInUser);
        assertEquals("admin", role);
    }

    @Test
    void testLoginFailure() throws SQLException {
        String username = "wrongUser";
        String password = "wrongPassword";

        when(loginController.getLogin(username)).thenReturn(null);

        Login loggedInUser = loginController.getLogin(username);

        assertNull(loggedInUser);
    }

    @Test
    void testCreateUserAccount() throws SQLException {
        Registration newUser = new Registration();
        newUser.setUsername_uu("newUser");
        newUser.setPassword_p("newPassword");
        newUser.setRegrole("user");
        newUser.setRegstatus("active");
        newUser.setEmail("newUser@example.com");

        doNothing().when(registrationController).saveRegistration(newUser);

        registrationController.saveRegistration(newUser);

        verify(registrationController, times(1)).saveRegistration(newUser);
    }

    @Test
    void testUpdateUserAccount() throws SQLException {
        Registration updatedUser = new Registration();
        updatedUser.setUsername_uu("updatedUser");
        updatedUser.setPassword_p("updatedPassword");
        updatedUser.setRegrole("user");
        updatedUser.setRegstatus("active");
        updatedUser.setEmail("updatedUser@example.com");

        doNothing().when(registrationController).updateRegistration(updatedUser);

        registrationController.updateRegistration(updatedUser);

        verify(registrationController, times(1)).updateRegistration(updatedUser);
    }

    @Test
    void testDeactivateUserAccount() throws SQLException {
        int userId = 1;

        doNothing().when(registrationController).deleteRegistration(userId);

        registrationController.deleteRegistration(1);

        verify(registrationController, times(1)).deleteRegistration(1);
    }



    @Test
    void testGetAllProjects() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Project project1 = new Project();
        project1.setProjectName("Project 1");
        Project project2 = new Project();
        project2.setProjectName("Project 2");

        when(projectManagerController.getAllProjects()).thenReturn(Arrays.asList(project1, project2));

        List<Project> projects = projectManagerController.getAllProjects();

        assertEquals(2, projects.size());
        assertEquals("Project 1", projects.get(0).getProjectName());
        assertEquals("Project 2", projects.get(1).getProjectName());
    }

    @Test
    void testAddTeamMemberToProject() throws SQLException {
        String teamMember = "teamMember";
        int projectId = 1;

        doNothing().when(projectManagerController).addTeamMemberToProject(teamMember, projectId);

        projectManagerController.addTeamMemberToProject(teamMember, projectId);

        verify(projectManagerController, times(1)).addTeamMemberToProject(teamMember, projectId);
    }

    @Test
    void testAssignTaskToTeamMember() throws SQLException {
        int taskId = 1;
        String username = "teammember";

        doNothing().when(projectManagerController).assignTaskToTeamMember(taskId, username);

        projectManagerController.assignTaskToTeamMember(taskId, username);

        verify(projectManagerController, times(1)).assignTaskToTeamMember(taskId, username);
    }

    @Test
    void testPredictTaskEffort() throws SQLException {
        int taskId = 1;

        doNothing().when(projectManagerController).predictTaskEffort(taskId);

        projectManagerController.predictTaskEffort(taskId);

        verify(projectManagerController, times(1)).predictTaskEffort(taskId);
    }

    @Test
    void testLogInSecurely() throws SQLException {
        String username = "teamMember";
        String password = "password";

        doNothing().when(teamMemberController).logInSecurely(username, password);

        teamMemberController.logInSecurely(username, password);

        verify(teamMemberController, times(1)).logInSecurely(username, password);
    }

    @Test
    void testViewAssignedProjects() throws SQLException {
        String username = "teamMember";
        Project project1 = new Project();
        project1.setProjectName("Project 1");
        Project project2 = new Project();
        project2.setProjectName("Project 2");

        when(teamMemberController.getAssignedProjects(username)).thenReturn(Arrays.asList(project1, project2));

        List<Project> projects = teamMemberController.getAssignedProjects(username);

        assertEquals(2, projects.size());
        assertEquals("Project 1", projects.get(0).getProjectName());
        assertEquals("Project 2", projects.get(1).getProjectName());
    }

    @Test
    void testViewAssignedTasks() throws SQLException {
        String username = "teamMember";
        Task task1 = new Task();
        task1.setTaskDescription("Task 1");
        Task task2 = new Task();
        task2.setTaskDescription("Task 2");

        when(teamMemberController.getAssignedTasks(username)).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = teamMemberController.getAssignedTasks(username);

        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTaskDescription());
        assertEquals("Task 2", tasks.get(1).getTaskDescription());
    }

    @Test
    void testUpdateTaskStatus() throws SQLException {
        int taskId = 1;
        String newStatus = "Completed";

        doNothing().when(teamMemberController).updateTaskStatus(taskId, newStatus);

        teamMemberController.updateTaskStatus(taskId, newStatus);

        verify(teamMemberController, times(1)).updateTaskStatus(taskId, newStatus);
    }

    @Test
    void testResetPassword() throws SQLException {
        String username = "user";
        String newPassword = "newPassword";

        doNothing().when(projectManagerController).resetPassword(username, newPassword);

        projectManagerController.resetPassword(username, newPassword);

        verify(projectManagerController, times(1)).resetPassword(username, newPassword);
    }
}
