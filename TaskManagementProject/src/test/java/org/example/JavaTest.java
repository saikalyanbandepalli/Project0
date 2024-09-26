/*package org.example;
import org.example.DAO.RegistrationDAO;
import org.example.model.Registration;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/task_management";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    private Connection connection;

    @BeforeEach
    public void setup() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserCreation() throws SQLException {
        RegistrationDAO adminDAO = new RegistrationDAO(connection);

        Registration newUser = new Registration();
        boolean result = adminDAO.saveRegistration(newUser);

        Assertions.assertTrue(result);
        Assertions.assertNotEquals(0, newUser.getId());
    }

    @Test
    public void testFindByUsername() {
       RegistrationDAO pmDAO = new RegistrationDAO(connection);

        String username = "testuser";
        Registration foundUser = pmDAO.getRegistration(username);

        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(username, foundUser.getUsername_uu());
    }

}

*/