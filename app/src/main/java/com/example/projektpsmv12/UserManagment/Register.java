package com.example.projektpsmv12.UserManagment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projektpsmv12.MainActivity;
import com.example.projektpsmv12.R;

public class Register extends AppCompatActivity {
    EditText email;
    EditText username;
    EditText password_From_Activity;
    Button registerButton;

    public static final String title = "Thanks for joining in!";
    public static final String message = "We really appreciate it.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        email = findViewById(R.id.Email);
        username = findViewById(R.id.username);
        password_From_Activity = findViewById(R.id.password_From_Activity);
        registerButton = findViewById(R.id.registerButton);
    }

    public void TryToConnect() {
        String em = email.getText().toString();
        String uN = username.getText().toString();
        String pass = password_From_Activity.getText().toString();
        DataValidation dataValidation = new DataValidation();
        Boolean isUsernameValid=dataValidation.validateUsername(uN);
        Boolean isEmailValid=dataValidation.validateEmail(em);
        Boolean isPasswordValid=dataValidation.validatePassword(pass);

        if(!isUsernameValid) {
            Toast.makeText(this, "Nieprawidłowa nazwa uzytkownika.", Toast.LENGTH_SHORT).show();
        } else if (!isEmailValid) {
            Toast.makeText(this, "Nieprawidłowy adres email.", Toast.LENGTH_SHORT).show();
        } else if (!isPasswordValid) {
            Toast.makeText(this, "Nieprawidłowe hasło,", Toast.LENGTH_SHORT).show();
        } else {
            new DatabaseConnectionTask(em, uN, pass).execute();
            Toast.makeText(Register.this, "Utworzono użytkownika", Toast.LENGTH_SHORT).show();
            sendMail();
        }
    }

    public void sendMail() {
        String mail = email.getText().toString().trim();
        JavaMailAPI javaMail = new JavaMailAPI(this, mail, title, message);
        javaMail.execute();
    }

    public void registerFun(View view) {
        TryToConnect();
    }

    public void Login(View view) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void EnterMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
