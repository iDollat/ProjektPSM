package com.example.projektpsmv12.UserManagement;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projektpsmv12.MainActivity;
import com.example.projektpsmv12.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {

    EditText useremail;
    static String email;
    EditText password_From_Activity;
    String passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void registerFun(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void EnterMainActivity(View view) {
        useremail = findViewById(R.id.useremail);
        password_From_Activity = findViewById(R.id.password_From_Activity);

        email = useremail.getText().toString();
        passwd = password_From_Activity.getText().toString();
        String hashed_password = Base64.encodeToString(passwd.getBytes(), Base64.DEFAULT);

        new LoginTask().execute(email, hashed_password);
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean> {
        private Connection connection;
        private String email;
        private String hashedPassword;

        @Override
        protected Boolean doInBackground(String... params) {
            email = params[0];
            hashedPassword = params[1];

            DatabaseConnectionTask loginCon = new DatabaseConnectionTask();

            try {
                connection = DriverManager.getConnection(loginCon.url, loginCon.user, loginCon.password);
                if (connection != null) {
                    String query = "SELECT password FROM project_psm.users WHERE email = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, email);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String stored_password = resultSet.getString("password");
                        resultSet.close();
                        preparedStatement.close();
                        connection.close();
                        return hashedPassword.equals(stored_password);
                    } else {
                        resultSet.close();
                        preparedStatement.close();
                        connection.close();
                        return false;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                // Jeśli hasła się zgadzają, przechodzimy do MainActivity
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            } else {
                // Jeśli hasła się nie zgadzają lub użytkownik nie został znaleziony, wyświetlamy komunikat
                Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
