package com.example.projektpsmv12.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.projektpsmv12.databinding.BackExercisesBinding;

public class BackExercises extends AppCompatActivity {
    private BackExercisesBinding binding;
    private ImageView[] muscleImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BackExercisesBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);

        muscleImageViews = new ImageView[]{
                binding.ForearmLeftBack,
                binding.ForearmRightBack,
                binding.gluteLeft,
                binding.gluteRight,
                binding.LatsLeft,
                binding.LatsRight,
                binding.LoinsLeft,
                binding.LoinsRight,
                binding.QuadLeftBack,
                binding.QuadRightBack,
                binding.ShoulderLeftBack,
                binding.ShoulderRightBack,
                binding.TrapeziusLeftBack,
                binding.TrapeziusRightBack,
                binding.TricepsLeft,
                binding.TricepsRight,
        };

        for (int i = 0; i < muscleImageViews.length; i++) {
            final int finalI = i;
            muscleImageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String muscleGroup = getMuscleGroup(finalI);
                    startExercisesActivity(muscleGroup);
                }
            });
        }
    }

    private String getMuscleGroup(int index) {
        switch (index) {
            case 0:
            case 1:
                return "forearm";
            case 2:
            case 3:
                return "glute";
            case 4:
            case 5:
            case 6:
                return "lats";
            case 7:
            case 8:
                return "loins";
            case 9:
            case 10:
                return "hamstring";
            case 11:
            case 12:
                return "shoulder";
            case 13:
            case 14:
                return "trapezius";
            case 15:
            case 16:
                return "triceps";
            default:
                return "";
        }
    }


    private void startExercisesActivity(String muscleGroup) {
        Intent intent = new Intent(this, Exercises.class);
        intent.putExtra("muscleGroup", muscleGroup);
        startActivity(intent);
    }
}
