package com.example.projektpsmv12.UserManagement;

public class DataValidation {
    public boolean validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null || password.length() < 9) {
            return false;
        }
        return true;
    }


}
