package com.example.projektpsmv12.ui.exercises;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projektpsmv12.MainActivity;
import com.example.projektpsmv12.R;

public class Exercises extends AppCompatActivity {
    Button Back;
    TextView muscleName;
    LinearLayout exercisesLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises);

        Back = findViewById(R.id.Back);
        muscleName = findViewById(R.id.muscleName);
        String muscleGroup = getIntent().getStringExtra("muscleGroup");
        String[] exercises = getExercisesForMuscleGroup(muscleGroup);
        exercisesLayout = findViewById(R.id.exercisesLayout);

        for (String exercise : exercises) {
            // Tworzenie LinearLayout
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Tworzenie TextView
            TextView exerciseTextView = new TextView(this);
            exerciseTextView.setText(exercise);
            exerciseTextView.setTextSize(16);
            exerciseTextView.setTextColor(getResources().getColor(R.color.white));
            exerciseTextView.setHeight(150);
            exerciseTextView.setWidth(700);
            exerciseTextView.setBackground(getResources().getDrawable(R.drawable.border));
            exerciseTextView.setGravity(Gravity.CENTER);

            // Tworzenie ImageView
            ImageView exerciseImageView = new ImageView(this);
            exerciseImageView.setImageResource(R.drawable.przycisk);

            // Ustawienie układu dla TextView i ImageView
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1
            );
            textParams.setMargins(0, 0, 10, 30); // Dodaj margines między TextView a ImageView
            exerciseTextView.setLayoutParams(textParams);
            exerciseImageView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            // Dodanie TextView i ImageView do LinearLayout
            linearLayout.addView(exerciseTextView);
            linearLayout.addView(exerciseImageView);

            // Dodanie LinearLayout do Layoutu
            exercisesLayout.addView(linearLayout);
            // Dodanie obsługi kliknięcia na ImageView
            exerciseImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textToCopy = exerciseTextView.getText().toString();
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Copied Text", textToCopy);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Skopiowano do schowka", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String[] getExercisesForMuscleGroup(String muscleGroup) {
        muscleName.setText(muscleGroup);
            return new String[]{"https://www.youtube.com/watch?v=5Fx4t3orDdk", "https://www.youtube.com/watch?v=FZtzuOYzvnQ", "https://www.youtube.com/watch?v=gA9PAnkeAoY"};

           //tu dorobic warunek i linki dopisac


    }


    public void BackToFront(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
