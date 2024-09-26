package org.example.model;

public class Login extends Registration {
    public Login(String usernameU, String passwordP) {
    this.username_u = usernameU;
    this.password_p = passwordP;

    }

    public String getPassword() {
        return password_p;
    }

    public void setPassword(String password) {
        this.password_p = password;
    }

    public String getUsername() {
        return username_u;
    }

    public void setUsername(String username) {
        this.username_u = username;
    }

    private String username_u;
    private String password_p;
}


