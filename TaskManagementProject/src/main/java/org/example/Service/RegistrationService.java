package org.example.Service;

import org.example.DAO.LoginDAO;
import org.example.DAO.RegistrationDAO;
import org.example.model.Login;
import org.example.model.Registration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


    public class RegistrationService {
        private final RegistrationDAO registrationDAO;
        public LoginDAO loginDAO;


        public RegistrationService(Connection connection) {
            this.registrationDAO = new RegistrationDAO(connection);
            this.loginDAO = new LoginDAO(connection);
        }

        public List<Registration> getAllRegistrations() throws SQLException {
            return registrationDAO.getAllRegistrations();
        }

        public Registration getRegistration(int id) throws SQLException {
            return registrationDAO.getRegistration(id);
        }

        public void saveRegistration(Registration registration) throws SQLException {
            registrationDAO.saveRegistration(registration);
            Login a = new Login(registration.getUsername_uu(), registration.getPassword_pp());
            loginDAO.saveLogin(a);

        }

        public void deleteRegistration(int id) throws SQLException {
            registrationDAO.deleteRegistration(id);
        }

        public void updateRegistration(Registration registration) throws SQLException {
            registrationDAO.updateRegistration(registration);
            Login a = new Login(registration.getUsername_uu(), registration.getPassword_pp());
            loginDAO.saveLogin(a);
        }

        public String getrole(String username){
            try {
                return registrationDAO.returnrole(username);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

