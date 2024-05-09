package com.example.projektpsmv12.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projektpsmv12.R;
import com.example.projektpsmv12.databinding.FragmentExercisesBinding;

public class ExercisesFragment extends Fragment {
    private FragmentExercisesBinding binding;

    private ImageView[] muscleImageViews;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExercisesViewModel exercisesViewModel =
                new ViewModelProvider(this).get(ExercisesViewModel.class);

        binding = FragmentExercisesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicjalizacja tablicy z referencjami do ImageView
        muscleImageViews = new ImageView[]{
                binding.bicepsLeftBigger,
                binding.bicepsLeftSmaller,
                binding.chestRight,
                binding.chestLeft,
                binding.shoulderRight,
                binding.shoulderLeft,
                binding.trapeziusLeft,
                binding.trapeziusRight,
                binding.abdominalMusclesMaxLeft,
                binding.abdominalMusclesLeft,
                binding.abdominalMusclesRight,
                binding.abdominalMusclesMaxRight,
                binding.bicepsRightBigger,
                binding.bicepsRightSmaller,
                binding.forearmRight,
                binding.forearmLeft,
                binding.quadLeft,
                binding.quadRight,
                binding.calfLeft,
                binding.calfRight,
        };

        // Przypisanie nasłuchiwacza zdarzeń dla każdego obrazka
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

        return root;
    }

    // Metoda pomocnicza do uzyskiwania nazwy grupy mięśniowej na podstawie indeksu obrazka
    private String getMuscleGroup(int index) {
        switch (index) {
            case 0:
            case 1:
            case 12:
            case 13:
                return "biceps";
            case 2:
            case 3:
                return "chest";
            case 4:
            case 5:
                return "shoulder";
            case 6:
            case 7:
                return "trapezius";
            case 8:
            case 9:
            case 10:
            case 11:
                return "abdominal";
            case 14:
            case 15:
                return "forearm";
            case 16:
            case 17:
                return "quad";
            case 18:
            case 19:
                return "calf";
            default:
                return "";
        }
    }

    // Metoda pomocnicza do rozpoczęcia aktywności Exercises
    private void startExercisesActivity(String muscleGroup) {
        Intent intent = new Intent(getActivity(), Exercises.class);
        intent.putExtra("muscleGroup", muscleGroup);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}