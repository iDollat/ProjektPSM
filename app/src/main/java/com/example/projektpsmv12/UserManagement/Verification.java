package com.example.projektpsmv12.UserManagement;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.projektpsmv12.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification extends Fragment {

    private boolean isVerified = false;
    private Connection connection;
    private String email = Login.email; // Pobieramy email z klasy Login

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loading, container, false);

        // Wykonujemy zapytanie do bazy danych w tle
        new VerificationTask().execute();

        return rootView;
    }

    private class VerificationTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            DatabaseConnectionTask loginCon = new DatabaseConnectionTask();

            try {
                connection = DriverManager.getConnection(loginCon.url, loginCon.user, loginCon.password);
                if (connection != null) {
                    String query = "SELECT verified FROM project_psm.users WHERE email = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, email);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        isVerified = resultSet.getBoolean("verified");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return isVerified;
        }

        @Override
        protected void onPostExecute(Boolean isVerified) {
            // Na podstawie isVerified wybieramy odpowiedni fragment
            int layoutId = isVerified ? R.layout.fragment_verified : R.layout.fragment_verification;
            loadFragment(layoutId);
        }
    }

    private void loadFragment(int layoutId) {
        // Tworzymy i zastępujemy fragment odpowiednim layoutem
        Fragment fragment;
        if (layoutId == R.layout.fragment_verified) {
            fragment = new FragmentVerified(); // Załadowanie fragmentu dla weryfikacji
        } else {
            fragment = new FragmentVerification(); // Załadowanie fragmentu dla niezweryfikowanych
        }

        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
