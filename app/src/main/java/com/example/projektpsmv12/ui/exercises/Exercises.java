package com.example.projektpsmv12.ui.exercises;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    private static class Exercise {
        String name;
        String link;

        Exercise(String name, String link) {
            this.name = name;
            this.link = link;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises);

        Back = findViewById(R.id.Back);
        muscleName = findViewById(R.id.muscleName);
        String muscleGroup = getIntent().getStringExtra("muscleGroup");
        Exercise[] exercises = getExercisesForMuscleGroup(muscleGroup);
        exercisesLayout = findViewById(R.id.exercisesLayout);

        for (Exercise exercise : exercises) {
            // Tworzenie LinearLayout
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Tworzenie TextView
            TextView exerciseTextView = new TextView(this);
            exerciseTextView.setText(exercise.name);
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
                    String url = exercise.link;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
        }
    }

    private Exercise[] getExercisesForMuscleGroup(String muscleGroup) {
        muscleName.setText(muscleGroup);
        switch (muscleGroup) {
            case "chest":
                return new Exercise[]{
                        new Exercise("Chest Exercise 1", "https://www.youtube.com/watch?v=5Fx4t3orDdk"),
                        new Exercise("Chest Exercise 2", "https://www.youtube.com/watch?v=FZtzuOYzvnQ"),
                        new Exercise("Chest Exercise 3", "https://www.youtube.com/watch?v=gA9PAnkeAoY")
                };
            case "shoulder":
                return new Exercise[]{
                        new Exercise("Shoulder Exercise 1", "https://www.youtube.com/watch?v=5g5U2dIoeQ0"),
                        new Exercise("Shoulder Exercise 2", "https://www.youtube.com/watch?v=M2rwvNhTOu0"),
                        new Exercise("Shoulder Exercise 3", "https://www.youtube.com/watch?v=evXOlgLTPCw"),
                        new Exercise("Shoulder Exercise 4", "https://www.youtube.com/watch?v=um3VVzqunPU"),
                        new Exercise("Shoulder Exercise 5", "https://www.youtube.com/watch?v=WiFxVCB50oo")
                };
            case "biceps":
                return new Exercise[]{
                        new Exercise("Biceps Exercise 1", "https://www.youtube.com/watch?v=ykJmrZ5v0Oo"),
                        new Exercise("Biceps Exercise 2", "https://www.youtube.com/watch?v=6bTcFTRoqcw"),
                        new Exercise("Biceps Exercise 3", "https://www.youtube.com/watch?v=CFBZ4jN1CMI"),
                        new Exercise("Biceps Exercise 4", "https://www.youtube.com/watch?v=BbxA1QF3TxY"),
                        new Exercise("Biceps Exercise 5", "https://www.youtube.com/watch?v=aTYlqC_JacQ")
                };
            case "trapezius":
                return new Exercise[]{
                        new Exercise("Trapezius Exercise 1", "https://www.youtube.com/watch?v=Qb6Bd1J954o"),
                        new Exercise("Trapezius Exercise 2", "https://www.youtube.com/watch?v=zQRcZjp3ZVI&t=369s")
                };
            case "abdominal":
                return new Exercise[]{
                        new Exercise("Abdominal Exercise 1", "https://www.youtube.com/watch?v=MKmrqcoCZ-M"),
                        new Exercise("Abdominal Exercise 2", "https://www.youtube.com/watch?v=wiFNA3sqjCA"),
                        new Exercise("Abdominal Exercise 3", "https://www.youtube.com/watch?v=U4L_6JEv9Jg"),
                        new Exercise("Abdominal Exercise 4", "https://www.youtube.com/watch?v=N_s9em1xTqU"),
                        new Exercise("Abdominal Exercise 5", "https://www.youtube.com/watch?v=wkD8rjkodUI")
                };
            case "forearm":
                return new Exercise[]{
                        new Exercise("Forearm Exercise 1", "https://www.youtube.com/watch?v=FW7URAaC-vE"),
                        new Exercise("Forearm Exercise 2", "https://www.youtube.com/watch?v=kTMJp7hILmk"),
                        new Exercise("Forearm Exercise 3", "https://www.youtube.com/watch?v=hnxTScazRs0"),
                        new Exercise("Forearm Exercise 4", "https://www.youtube.com/watch?v=xrS1UCC24do")
                };
            case "quad":
                return new Exercise[]{
                        new Exercise("Quad Exercise 1", "https://www.youtube.com/watch?v=oQ2qU4Cab0w"),
                        new Exercise("Quad Exercise 2", "https://www.youtube.com/watch?v=v_c67Omje48"),
                        new Exercise("Quad Exercise 3", "https://www.youtube.com/watch?v=uYumuL_G_V0"),
                        new Exercise("Quad Exercise 4", "https://www.youtube.com/watch?v=0tn5K9NlCfo"),
                        new Exercise("Quad Exercise 5", "https://www.youtube.com/watch?v=m0FOpMEgero"),
                        new Exercise("Quad Exercise 6", "https://www.youtube.com/watch?v=_meXEWq5MOQ")
                };
            case "calf":
                return new Exercise[]{
                        new Exercise("Calf Exercise 1", "https://www.youtube.com/watch?v=3ZRe_QpvRPg"),
                        new Exercise("Calf Exercise 2", "https://www.youtube.com/watch?v=3UWi44yN-wM"),
                        new Exercise("Calf Exercise 3", "https://www.youtube.com/watch?v=XNDxCuY4l1U")
                };
            case "lats":
                return new Exercise[]{
                        new Exercise("Lats Exercise 1", "https://www.youtube.com/watch?v=JGeRYIZdojU"),
                        new Exercise("Lats Exercise 2", "https://www.youtube.com/watch?v=G9uNaXGTJ4w"),
                        new Exercise("Lats Exercise 3", "https://www.youtube.com/watch?v=7IV729pBFUc"),
                        new Exercise("Lats Exercise 4", "https://www.youtube.com/watch?v=zVNSVxv8M8A"),
                        new Exercise("Lats Exercise 5", "https://www.youtube.com/watch?v=ca95JZzzsGs"),
                        new Exercise("Lats Exercise 6", "https://www.youtube.com/watch?v=VprlTxpB1rk")
                };
            case "hamstring":
                return new Exercise[]{
                        new Exercise("Hamstring Exercise 1", "https://www.youtube.com/watch?v=CN_7cz3P-1U"),
                        new Exercise("Hamstring Exercise 2", "https://www.youtube.com/watch?v=GxsLrTzyGUU"),
                        new Exercise("Hamstring Exercise 3", "https://www.youtube.com/watch?v=vHMRcECLwFM"),
                        new Exercise("Hamstring Exercise 4", "https://www.youtube.com/watch?v=p-EfiGOK7XQ")
                };
            case "glute":
                return new Exercise[]{
                        new Exercise("Glute Exercise 1", "https://www.youtube.com/watch?v=ph3pddpKzzw"),
                        new Exercise("Glute Exercise 2", "https://www.youtube.com/watch?v=L1qG25DhAk4"),
                        new Exercise("Glute Exercise 3", "https://www.youtube.com/watch?v=YA-h3n9L4YU"),
                        new Exercise("Glute Exercise 4", "https://www.youtube.com/watch?v=9O3lA9HsZU8")
                };
            case "loins":
                return new Exercise[]{
                        new Exercise("Loins Exercise 1", "https://www.youtube.com/watch?v=h2iKcNldw-g"),
                        new Exercise("Loins Exercise 2", "https://www.youtube.com/watch?v=p6KK6yHxd4k"),
                        new Exercise("Loins Exercise 3", "https://www.youtube.com/watch?v=yYUD2GwXlI8")
                };
            default:
                return new Exercise[]{new Exercise("No exercises found", "")};
        }
    }

    public void BackToFront(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
