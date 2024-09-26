package org.example.Controller;

import org.example.Service.RegistrationService;
import org.example.model.Registration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(Connection connection) {
        this.registrationService = new RegistrationService(connection);
    }

    public List<Registration> getAllRegistrations() throws SQLException {
        return registrationService.getAllRegistrations();
    }

    public Registration getRegistration(int id) throws SQLException {
        return registrationService.getRegistration(id);
    }

    public void saveRegistration(Registration registration) throws SQLException {
        registrationService.saveRegistration(registration);
    }

    public void deleteRegistration(int id) throws SQLException {
        registrationService.deleteRegistration(id);
    }

    public void updateRegistration(Registration registration) throws SQLException {
        registrationService.updateRegistration(registration);
    }

    public String getRole(String username){
        return registrationService.getrole(username);
    }
}

