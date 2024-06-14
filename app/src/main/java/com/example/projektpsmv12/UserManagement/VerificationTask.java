package com.example.projektpsmv12.UserManagement;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerificationTask extends AsyncTask<Void, Void, Boolean> {
    private String email;
    private String userEnteredCode;

    public VerificationTask(String email, String userEnteredCode) {
        this.email = email;
        this.userEnteredCode = userEnteredCode;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean isVerified = false;
        String storedCode="";

        DatabaseConnectionTask loginCon = new DatabaseConnectionTask();

        try {
            Connection connection = DriverManager.getConnection(loginCon.url, loginCon.user, loginCon.password);
            if (connection != null) {
                // Pobranie zapisanego kodu weryfikacyjnego z bazy danych
                String queryCode = "SELECT verification_code FROM project_psm.users WHERE email = ?";
                PreparedStatement preparedStatementCode = connection.prepareStatement(queryCode);
                preparedStatementCode.setString(1, email);
                ResultSet resultSetCode = preparedStatementCode.executeQuery();
                if (resultSetCode.next()) {
                    storedCode = resultSetCode.getString("verification_code");
                    Log.d("VerificationTask", "Stored verification code: " + storedCode.toString());
                }

                // Porównanie kodu wprowadzonego przez użytkownika z kodem z bazy danych
                if (storedCode.equals(userEnteredCode)) {
                    // Jeśli się zgadzają, ustawienie verified=1 w bazie danych
                    String updateQuery = "UPDATE project_psm.users SET verified = true WHERE email = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setString(1, email);
                    updateStatement.executeUpdate();
                    isVerified = true;
                }

                resultSetCode.close();
                preparedStatementCode.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isVerified;
    }
}
