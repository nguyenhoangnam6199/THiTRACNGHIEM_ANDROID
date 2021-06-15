package com.example.retrofitdemo.model;

public class AccountLogIn {
    public String usernameOrEmail;
    public String password;

    public AccountLogIn(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountLogIn{" +
                "usernameOrEmail='" + usernameOrEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
