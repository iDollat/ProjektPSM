package com.example.projektpsmv12.UserManagment;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectionTask extends AsyncTask<Void, Void, Void> {
    private String email;
    private String username;
    private String passwordStr;
    private Connection connection;
    public DatabaseConnectionTask(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.passwordStr = password;
    }
    @Override
    protected Void  doInBackground(Void... voids) {
        connection = null;
        try {
            String user = "2023_chmura_daniel";
            String url = "jdbc:postgresql://195.150.230.208:5432/2023_chmura_daniel";
            String password = "okon";
            connection = DriverManager.getConnection(url, user, password);
            if(connection != null){
                String query = "INSERT INTO project_psm.users (login, password, email) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);  // Pierwszy parametr to login
                preparedStatement.setString(2, passwordStr); // Drugi parametr to hasło
                preparedStatement.setString(3, email); // Trzeci parametr to email
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
