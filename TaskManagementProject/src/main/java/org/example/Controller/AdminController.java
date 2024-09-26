package org.example.Controller;
import org.example.DAO.LoginDAO;
import org.example.DatabaseConnection;
import org.example.model.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private Connection connection;
    private RegistrationController registrationController;
    private final LoginDAO loginDAO;

    public AdminController() throws SQLException {
        connection = DatabaseConnection.getConnection();
        this.loginDAO = new LoginDAO(connection);
        registrationController = new RegistrationController(connection);
    }

    public void createUserAccount(Registration registration) throws SQLException {
        //loginDAO.saveLogin(registration.getUsername_uu(), registration.getPassword_pp());
        registrationController.saveRegistration(registration);
    }

    public void updateUserAccount(Registration registration) throws SQLException {
        registrationController.updateRegistration(registration);
    }

    public void deactivateUserAccount(int id) throws SQLException {
        registrationController.deleteRegistration(id);
    }

    public void accessReportingTools() throws SQLException {
        List<Registration> registrations = registrationController.getAllRegistrations();
        for (Registration registration : registrations) {
            System.out.println("User: " + registration.getUsername_uu() + ", Role: " + registration.getRegrole());
        }
    }
}
