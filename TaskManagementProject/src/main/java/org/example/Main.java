package org.example;

import org.example.Controller.*;
import org.example.model.Login;
import org.example.model.Project;
import org.example.model.Registration;
import org.example.model.Task;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/task_management";
    private static final String USER = "root";
    private static final String PASSWORD = "Kalyan@123";

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            LoginController loginController = new LoginController(connection);
            RegistrationController registrationController = new RegistrationController(connection);
            ProjectManagerController projectManagerController = new ProjectManagerController(connection);
            TeamMemberController teamMemberController = new TeamMemberController(connection);

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                Login loggedInUser = loginController.getLogin(username);
                System.out.println(loggedInUser.getUsername());

                String role = registrationController.getRole(loggedInUser.getUsername());

                if (loggedInUser != null) {
                    System.out.println("Login successful. Role: " + role);

                    switch (role.toLowerCase()) {
                        case "admin":
                            AdminController adminController = new AdminController();
                            boolean adminExit = false;
                            while (!adminExit) {
                                System.out.println("1. Create User Account");
                                System.out.println("2. Update User Account");
                                System.out.println("3. Deactivate User Account");
                                System.out.println("4. Access Reporting Tools");
                                System.out.println("5. Exit");
                                System.out.print("Choose an action: ");
                                int adminChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (adminChoice) {
                                    case 1:
                                        System.out.print("Enter new user details: ");
                                        Scanner s1 = new Scanner(System.in);
                                        Registration newUser = new Registration();
                                        System.out.print("Enter new username details: ");
                                        newUser.setUsername_uu(s1.nextLine());
                                        System.out.print("Enter new password details: ");
                                        newUser.setPassword_p(s1.nextLine());
                                        System.out.print("Enter new role details: ");
                                        newUser.setRegrole(s1.nextLine());
                                        System.out.print("Enter new status details: ");
                                        newUser.setRegstatus(s1.nextLine());
                                        System.out.print("Enter new email details: ");
                                        newUser.setEmail(s1.nextLine());
                                        System.out.print("Enter new id details: ");
                                        newUser.setId(s1.nextInt());
                                        System.out.print("Enter new mileid details: ");
                                        newUser.setMile_id(s1.nextInt());

                                        adminController.createUserAccount(newUser);
                                        break;
                                    case 2:
                                        System.out.print("Enter updated user details: ");
                                        Scanner s2 = new Scanner(System.in);
                                        Registration UpdatedUser = new Registration();
                                        System.out.print("Enter new username details: ");
                                        UpdatedUser.setUsername_uu(s2.nextLine());
                                        System.out.print("Enter new password details: ");
                                        UpdatedUser.setPassword_p(s2.nextLine());
                                        System.out.print("Enter new role details: ");
                                        UpdatedUser.setRegrole(s2.nextLine());
                                        System.out.print("Enter new status details: ");
                                        UpdatedUser.setRegstatus(s2.nextLine());
                                        System.out.print("Enter new email details: ");
                                        UpdatedUser.setEmail(s2.nextLine());
                                        System.out.print("Enter new id details: ");
                                        UpdatedUser.setId(s2.nextInt());
                                        System.out.print("Enter new user details: ");
                                        UpdatedUser.setMile_id(s2.nextInt());
                                        adminController.createUserAccount(UpdatedUser);
                                        Registration updatedUser = new Registration();
                                        adminController.updateUserAccount(updatedUser);
                                        break;
                                    case 3:
                                        System.out.print("Enter user ID to deactivate: ");
                                        int userId = scanner.nextInt();
                                        adminController.deactivateUserAccount(userId);
                                        break;
                                    case 4:
                                        adminController.accessReportingTools();
                                        break;
                                    case 5:
                                        adminExit = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                            break;

                        case "projectmanager":
                            boolean pmExit = false;
                            while (!pmExit) {
                                System.out.println("1. Reset Password");
                                System.out.println("2. View All Projects");
                                System.out.println("3. Add Team Member to Project");
                                System.out.println("4. Assign Task to Team Member");
                                System.out.println("5. Predict Task Effort");
                                System.out.println("6. Exit");
                                System.out.print("Choose an action: ");
                                int pmChoice = scanner.nextInt();
                                scanner.nextLine();  // Consume newline

                                switch (pmChoice) {
                                    case 1:
                                        System.out.print("Enter username to reset password: ");
                                        String resetUser = scanner.nextLine();
                                        System.out.print("Enter new password: ");
                                        String newPassword = scanner.nextLine();
                                        projectManagerController.resetPassword(resetUser, newPassword);
                                        break;
                                    case 2:
                                        List<Project> projects = projectManagerController.getAllProjects();
                                        projects.forEach(project -> System.out.println(project.getProjectName()));
                                        break;
                                    case 3:
                                        System.out.print("Enter username of team member: ");
                                        String teamMember = scanner.nextLine();
                                        System.out.print("Enter project ID: ");
                                        int projectId = scanner.nextInt();
                                        projectManagerController.addTeamMemberToProject(teamMember, projectId);
                                        break;
                                    case 4:
                                        System.out.print("Enter task ID: ");
                                        int taskId = scanner.nextInt();
                                        System.out.print("Enter username of team member: ");
                                        scanner.nextLine();  // Consume newline
                                        String memberUser = scanner.nextLine();
                                        projectManagerController.assignTaskToTeamMember(taskId, memberUser);
                                        break;
                                    case 5:
                                        System.out.print("Enter task ID to predict effort: ");
                                        int taskToPredict = scanner.nextInt();
                                        projectManagerController.predictTaskEffort(taskToPredict);
                                        break;
                                    case 6:
                                        pmExit = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                            break;

                        case "teammember":
                            boolean tmExit = false;
                            while (!tmExit) {
                                System.out.println("1. Log In Securely");
                                System.out.println("2. View Assigned Projects");
                                System.out.println("3. View Assigned Tasks");
                                System.out.println("4. Update Task Status");
                                System.out.println("5. Exit");
                                System.out.print("Choose an action: ");
                                int tmChoice = scanner.nextInt();
                                scanner.nextLine();  // Consume newline

                                switch (tmChoice) {
                                    case 1:
                                        System.out.print("Enter username: ");
                                        String loginUser = scanner.nextLine();
                                        System.out.print("Enter password: ");
                                        String loginPass = scanner.nextLine();
                                        teamMemberController.logInSecurely(loginUser, loginPass);
                                        break;
                                    case 2:
                                        System.out.print("Enter your username: ");
                                        String tmUser = scanner.nextLine();
                                        List<Project> assignedProjects = teamMemberController.getAssignedProjects(tmUser);
                                        assignedProjects.forEach(project -> System.out.println(project.getProjectName()));
                                        break;
                                    case 3:
                                        System.out.print("Enter your username: ");
                                        String tmTasksUser = scanner.nextLine();
                                        List<Task> assignedTasks = teamMemberController.getAssignedTasks(tmTasksUser);
                                        assignedTasks.forEach(task -> System.out.println(task.getTaskDescription()));
                                        break;
                                    case 4:
                                        System.out.print("Enter task ID: ");
                                        int taskStatusId = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.print("Enter new status: ");
                                        String newStatus = scanner.nextLine();
                                        teamMemberController.updateTaskStatus(taskStatusId, newStatus);
                                        break;
                                    case 5:
                                        tmExit = true;
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                }
                            }
                            break;

                        default:
                            System.out.println("Unknown role.");
                    }
                } else {
                    System.out.println("Invalid username or password.");
                }

                System.out.print("Do you want to continue? (yes/no): ");
                String continueChoice = scanner.nextLine();
                if (continueChoice.equalsIgnoreCase("no")) {
                    exit = true;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
