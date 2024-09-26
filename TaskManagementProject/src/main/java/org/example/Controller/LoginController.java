package org.example.Controller;

import org.example.Service.LoginService;
import org.example.model.Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LoginController {
    private final LoginService loginService;

    public LoginController(Connection connection) {
        this.loginService = new LoginService(connection);
    }

    public List<Login> getAllLogins() throws SQLException {
        return loginService.getAllLogins();
    }

    public Login getLogin(String username) throws SQLException {
        return loginService.getLogin(username);
    }

    public void saveLogin(Login login) throws SQLException {
        loginService.saveLogin(login);
    }

    public void deleteLogin(String username) throws SQLException {
        loginService.deleteLogin(username);
    }
}

