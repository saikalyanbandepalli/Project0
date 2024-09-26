package org.example.Service;

import org.example.DAO.LoginDAO;
import org.example.DatabaseConnection;
import org.example.model.Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LoginService {

    private final LoginDAO loginDAO;

    public LoginService(Connection connection) {
        this.loginDAO = new LoginDAO(connection);
    }

    public List<Login> getAllLogins() throws SQLException {
        return loginDAO.getAllLogins();
    }

    public Login getLogin(String username) throws SQLException {
        return loginDAO.getLogin(username);
    }

    public void saveLogin(Login login) throws SQLException {
        loginDAO.saveLogin(login);
    }

    public void deleteLogin(String username) throws SQLException {
        loginDAO.deleteLogin(username);
    }
}

