package com.example.projektpsmv12.UserManagement;

import android.os.AsyncTask;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import android.util.Base64;

public class DatabaseConnectionTask extends AsyncTask<Void, Void, Void> {
    private String email;
    private String username;
    private String passwordStr;
    private Connection connection;
    String user = "2023_chmura_daniel";
    String url = "jdbc:postgresql://195.150.230.208:5432/2023_chmura_daniel";
    String password = "Danielchmura22553307022002!";

    public DatabaseConnectionTask(){}
    public DatabaseConnectionTask(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.passwordStr = password;
    }

    public static String generateVerificationCode() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000000) + 1;
        return String.format("%07d", randomNumber);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            if(connection != null){
                String query = "INSERT INTO project_psm.users (login, password, email, verification_code) VALUES (?, ?, ?, ?)";
                String hashed_password = Base64.encodeToString(passwordStr.getBytes(), Base64.DEFAULT); // Hashowanie hasła
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);  // Pierwszy parametr to login
                preparedStatement.setString(2, hashed_password); // Drugi parametr to hasło
                preparedStatement.setString(3, email); // Trzeci parametr to email
                String verification_code = generateVerificationCode(); // Generowanie kodu do weryfikacji
                preparedStatement.setString(4, verification_code);
                preparedStatement.executeUpdate();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    protected void onPostExecute(Void result) {
        System.out.println("Operacja zakończona");
    }
}
