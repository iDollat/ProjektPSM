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
        if (muscleGroup.equals("chest")) {
            return new String[]{"https://www.youtube.com/watch?v=5Fx4t3orDdk", "https://www.youtube.com/watch?v=FZtzuOYzvnQ", "https://www.youtube.com/watch?v=gA9PAnkeAoY"};
        } else if (muscleGroup.equals("shoulder")) {
            return new String[]{"https://www.youtube.com/watch?v=5g5U2dIoeQ0","https://www.youtube.com/watch?v=M2rwvNhTOu0","https://www.youtube.com/watch?v=evXOlgLTPCw","https://www.youtube.com/watch?v=um3VVzqunPU","https://www.youtube.com/watch?v=WiFxVCB50oo"};
        } else if (muscleGroup.equals("biceps")){
            return new String[]{"https://www.youtube.com/watch?v=ykJmrZ5v0Oo","https://www.youtube.com/watch?v=6bTcFTRoqcw","https://www.youtube.com/watch?v=CFBZ4jN1CMI","https://www.youtube.com/watch?v=BbxA1QF3TxY","https://www.youtube.com/watch?v=aTYlqC_JacQ"};
        }else if (muscleGroup.equals("trapezius")){
            return new String[]{"https://www.youtube.com/watch?v=Qb6Bd1J954o","https://www.youtube.com/watch?v=zQRcZjp3ZVI&t=369s"};
        }else if (muscleGroup.equals("abdominal")){
            return new String[]{"https://www.youtube.com/watch?v=MKmrqcoCZ-M","https://www.youtube.com/watch?v=wiFNA3sqjCA","https://www.youtube.com/watch?v=U4L_6JEv9Jg","https://www.youtube.com/watch?v=N_s9em1xTqU","https://www.youtube.com/watch?v=wkD8rjkodUI"};
        }else if (muscleGroup.equals("forearm")){
            return new String[]{"https://www.youtube.com/watch?v=FW7URAaC-vE","https://www.youtube.com/watch?v=kTMJp7hILmk","https://www.youtube.com/watch?v=hnxTScazRs0","https://www.youtube.com/watch?v=xrS1UCC24do"};
        }else if (muscleGroup.equals("quad")){
            return new String[]{"https://www.youtube.com/watch?v=oQ2qU4Cab0w","https://www.youtube.com/watch?v=v_c67Omje48","https://www.youtube.com/watch?v=uYumuL_G_V0","https://www.youtube.com/watch?v=0tn5K9NlCfo","https://www.youtube.com/watch?v=m0FOpMEgero","https://www.youtube.com/watch?v=_meXEWq5MOQ"};
        }else if (muscleGroup.equals("calf")) {
            return new String[]{"https://www.youtube.com/watch?v=3ZRe_QpvRPg","https://www.youtube.com/watch?v=3UWi44yN-wM","https://www.youtube.com/watch?v=XNDxCuY4l1U"};
        }
        return new String[]{"dupa"};
    }


    public void BackToFront(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
