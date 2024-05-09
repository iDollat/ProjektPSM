package com.example.projektpsmv12.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projektpsmv12.R;

public class Exercises extends AppCompatActivity {
    Button Back;
    TextView nazwa_patrii2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises);
        Back = findViewById(R.id.Back);
        nazwa_patrii2 = findViewById(R.id.nazwa_partii);
        String muscleGroup = getIntent().getStringExtra("muscleGroup");
        generateExerciseVideo(muscleGroup);

        nazwa_patrii2.setText(muscleGroup);
    }
    public void BackToFront(View view){
        Intent intent = new Intent(this, ExercisesFragment.class);
        startActivity(intent);
    }
    private void generateExerciseVideo(String muscleGroup) {
        // Tutaj możesz napisać logikę generowania filmu
        // na podstawie klikniętej grupy mięśniowej
        // Na przykład, jeśli kliknięto "glowa", możesz wygenerować film dla ćwiczeń głowy.
    }
}
