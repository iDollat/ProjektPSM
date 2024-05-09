package com.example.projektpsmv12.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projektpsmv12.MainActivity;
import com.example.projektpsmv12.R;

public class Exercises extends AppCompatActivity {
    Button Back;
    TextView nazwa_patrii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises);
        Back = findViewById(R.id.Back);
        String muscleGroup = getIntent().getStringExtra("muscleGroup");
        nazwa_patrii = findViewById(R.id.nazwa_partii);
        nazwa_patrii.setText(muscleGroup);


    }
    public void BackToFront(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
