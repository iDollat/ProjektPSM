package com.example.projektpsmv12.UserManagement;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.projektpsmv12.R;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Verification extends Fragment {

    private boolean isVerified = false;
    private TextView verified;
    private TextView unVerified;
    private EditText verificationCode;
    private Button checkCode;
    private String email = Login.email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_verification, container, false);

        // Inicjalizacja elementów UI
        verified = rootView.findViewById(R.id.verified);
        unVerified = rootView.findViewById(R.id.unVerified);
        verificationCode = rootView.findViewById(R.id.verificationCode);
        checkCode = rootView.findViewById(R.id.checkCode);

        // Ustawienie OnClickListener dla przycisku checkCode
        checkCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pobierz kod z pola EditText
                String code = verificationCode.getText().toString();

                // Uruchom AsyncTask i obsłuż wynik
                new VerificationTask(email, code) {
                    @Override
                    protected void onPostExecute(Boolean result) {
                        super.onPostExecute(result);
                        isVerified = result;

                        if (isVerified) {
                            verified.setVisibility(View.VISIBLE);
                            unVerified.setVisibility(View.INVISIBLE);
                        } else {
                            verified.setVisibility(View.INVISIBLE);
                            unVerified.setVisibility(View.VISIBLE);
                        }
                    }
                }.execute();
            }
        });

        return rootView;
    }

    // Metoda do pobierania wartości z EditText i konwersji na int

}





