package com.example.projektpsmv12.ui.exercises;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projektpsmv12.R;
import com.example.projektpsmv12.databinding.FragmentExercisesBinding;

public class ExercisesFragment extends Fragment {
    private FragmentExercisesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExercisesViewModel homeViewModel =
                new ViewModelProvider(this).get(ExercisesViewModel.class);

        binding = FragmentExercisesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button glowaButton = root.findViewById(R.id.glowa);
        Button czworobocznyFrontButton = root.findViewById(R.id.czworoboczny_front);
        Button klatkaButton = root.findViewById(R.id.klatka);
        Button barkLewyButton = root.findViewById(R.id.bark_lewy);
        Button barkPrawyButton = root.findViewById(R.id.bark_prawy);
        Button brzuchButton = root.findViewById(R.id.brzuch);
        Button biceps_lewy = root.findViewById(R.id.biceps_lewy);
        Button biceps_prawy = root.findViewById(R.id.biceps_prawy);
        Button przedramie_prawe = root.findViewById(R.id.przedramie_prawe);
        Button przedramie_lewe = root.findViewById(R.id.przedramie_lewe);
        Button udo_lewe =  root.findViewById(R.id.udo_lewe);
        Button udo_prawe =  root.findViewById(R.id.udo_prawe);
        Button lydka_lewa =  root.findViewById(R.id.lydka_lewa);
        Button lydka_prawa =  root.findViewById(R.id.lydka_prawa);

        glowaButton.setOnClickListener(createClickListener("glowa"));
        czworobocznyFrontButton.setOnClickListener(createClickListener("czworoboczny_front"));
        klatkaButton.setOnClickListener(createClickListener("klatka"));
        barkLewyButton.setOnClickListener(createClickListener("bark"));
        barkPrawyButton.setOnClickListener(createClickListener("bark"));
        brzuchButton.setOnClickListener(createClickListener("brzuch"));
        biceps_prawy.setOnClickListener(createClickListener("biceps"));
        biceps_lewy.setOnClickListener(createClickListener("biceps"));
        przedramie_lewe.setOnClickListener(createClickListener("przedramiona"));
        przedramie_prawe.setOnClickListener(createClickListener("przedramiona"));
        udo_lewe.setOnClickListener(createClickListener("czworoglowe"));
        udo_prawe.setOnClickListener(createClickListener("czworoglowe"));
        lydka_lewa.setOnClickListener(createClickListener("lydki"));
        lydka_prawa.setOnClickListener(createClickListener("lydki"));
        return root;

    }
    private View.OnClickListener createClickListener(final String muscleGroup) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Przekazanie informacji o klikniętej grupie mięśniowej do aktywności Exercises
                Intent intent = new Intent(getActivity(), Exercises.class);
                intent.putExtra("muscleGroup", muscleGroup);
                startActivity(intent);
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}