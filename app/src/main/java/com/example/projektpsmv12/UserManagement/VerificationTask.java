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


    public VerificationTask(String email) {
        this.email = email;
    }

    public VerificationTask(String email, String userEnteredCode) {
        this.email = email;
        this.userEnteredCode = userEnteredCode;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean isVerified = false;
        boolean check = false;
        String storedCode="";

        DatabaseConnectionTask loginCon = new DatabaseConnectionTask();

        try {
            Connection connection = DriverManager.getConnection(loginCon.url, loginCon.user, loginCon.password);
            if (connection != null) {
                String checkQuery = "SELECT verified FROM project_psm.users WHERE email = ?";
                PreparedStatement preparedStatementCheckQuery = connection.prepareStatement(checkQuery);
                preparedStatementCheckQuery.setString(1,email);
                ResultSet resultSetCheckQuery = preparedStatementCheckQuery.executeQuery();
                if(resultSetCheckQuery.next()){
                    check=resultSetCheckQuery.getBoolean("verified");
                    Log.d("VerificationTask", "Zweryfikowany? : " + check);
                    if(check){
                        isVerified = true;
                    }else{
                        String queryCode = "SELECT verification_code FROM project_psm.users WHERE email = ?";
                        PreparedStatement preparedStatementCode = connection.prepareStatement(queryCode);
                        preparedStatementCode.setString(1, email);
                        ResultSet resultSetCode = preparedStatementCode.executeQuery();
                        if (resultSetCode.next()) {
                            storedCode = resultSetCode.getString("verification_code");
                            Log.d("VerificationTask", "Stored verification code: " + storedCode.toString());
                        }

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
                    }
                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isVerified;
    }
}
