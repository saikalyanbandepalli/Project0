package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class Registration {
    private int id;
    private String username_uu;
    private String email;
    private String password_pp;
    private String regrole;
    private String regstatus;
    private int mile_id;

    public Registration() {
    }

    private static Map<String, Registration> userDatabase = new HashMap<>();

    public Registration(int id, String usernameUu, String email, String passwordPp, String regrole, String regstatus, int mileId) {
        this.id = id;
        this.username_uu = usernameUu;
        this.email = email;
        this.password_pp = passwordPp;
        this.regrole = regrole;
        this.regstatus = regstatus;
        this.mile_id = mileId;
        userDatabase.put(usernameUu, this);
    }

    public String getPassword_pp() {
        return password_pp;
    }

    public void setPassword_p(String password_pp) {
        this.password_pp = password_pp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername_uu() {
        return username_uu;
    }

    public void setUsername_uu(String username_uu) {
        this.username_uu = username_uu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegrole() {
        return regrole;
    }

    public void setRegrole(String regrole) {
        this.regrole = regrole;
    }

    public String getRegstatus() {
        return regstatus;
    }

    public void setRegstatus(String regstatus) {
        this.regstatus = regstatus;
    }

    public int getMile_id() {
        return mile_id;
    }

    public void setMile_id(int mile_id) {
        this.mile_id = mile_id;
    }

    // Role-based methods
    public void createUserAccount(String usernameUu, String email, String passwordPp, String regrole, String regstatus, int mileId) {
        if (this.regrole.equals("admin")) {
            Registration newUser = new Registration(userDatabase.size() + 1, usernameUu, email, passwordPp, regrole, regstatus, mileId);
            userDatabase.put(usernameUu, newUser);
            System.out.println("User account created: " + usernameUu);
        }
    }

    public void updateUserAccount(String usernameUu, String newEmail, String newPassword, String newRegrole, String newRegstatus, int newMileId) {
        if (this.regrole.equals("admin")) {
            Registration user = userDatabase.get(usernameUu);
            if (user != null) {
                user.setEmail(newEmail);
                user.setPassword_p(newPassword);
                user.setRegrole(newRegrole);
                user.setRegstatus(newRegstatus);
                user.setMile_id(newMileId);
                System.out.println("User account updated: " + usernameUu);
            } else {
                System.out.println("User not found: " + usernameUu);
            }
        }
    }

    public void deactivateUserAccount(String usernameUu) {
        if (this.regrole.equals("admin")) {
            Registration user = userDatabase.get(usernameUu);
            if (user != null) {
                user.setRegstatus("deactivated");
                System.out.println("User account deactivated: " + usernameUu);
            } else {
                System.out.println("User not found: " + usernameUu);
            }
        }
    }

    public void assignAccessLevels(String usernameUu, String newRegrole) {
        if (this.regrole.equals("admin")) {
            Registration user = userDatabase.get(usernameUu);
            if (user != null) {
                user.setRegrole(newRegrole);
                System.out.println("Access level assigned: " + usernameUu + " -> " + newRegrole);
            } else {
                System.out.println("User not found: " + usernameUu);
            }
        }
    }

    public void accessReportingTools() {
        if (this.regrole.equals("admin")) {
            // Simulate reporting tool access
            System.out.println("Accessing reporting tools.");
        }
    }

    public void loginAndResetPassword(String usernameUu, String newPassword) {
        if (this.regrole.equals("projectmanager")) {
            Registration user = userDatabase.get(usernameUu);
            if (user != null) {
                user.setPassword_p(newPassword);
                System.out.println("Password reset successfully for: " + usernameUu);
            } else {
                System.out.println("User not found: " + usernameUu);
            }
        }
    }

    public void manageClientInfo() {
        if (this.regrole.equals("projectmanager")) {
            // Simulate managing client information
            System.out.println("Client information managed.");
        }
    }

    public void addTeamMembers(String usernameUu, String email, String passwordPp, String regrole, String regstatus, int mileId) {
        if (this.regrole.equals("projectmanager")) {
            createUserAccount(usernameUu, email, passwordPp, regrole, regstatus, mileId);
            System.out.println("Team member added: " + usernameUu);
        }
    }

    public void assignTasks() {
        if (this.regrole.equals("projectmanager")) {
            // Simulate task assignment
            System.out.println("Tasks assigned.");
        }
    }

    public void login() {
        if (this.regrole.equals("teammember")) {
            // Simulate login
            System.out.println("Logged in successfully.");
        }
    }

    public void viewProjectDetails() {
        if (this.regrole.equals("teammember")) {
            // Simulate viewing project details
            System.out.println("Viewing project details.");
        }
    }

    public void updateTaskStatus() {
        if (this.regrole.equals("teammember")) {
            // Simulate updating task status
            System.out.println("Task status updated.");
        }
    }
}