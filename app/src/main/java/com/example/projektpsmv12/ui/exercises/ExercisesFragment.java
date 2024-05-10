package com.example.projektpsmv12.ui.exercises;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projektpsmv12.R;
import com.example.projektpsmv12.databinding.FragmentExercisesBinding;

public class ExercisesFragment extends Fragment {
    private FragmentExercisesBinding binding;
    private ImageView[] muscleImageViews;
    private SharedPreferences sharedPreferences;
    private AlertDialog popupDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExercisesViewModel exercisesViewModel =
                new ViewModelProvider(this).get(ExercisesViewModel.class);
        binding = FragmentExercisesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);

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
      showDialog();
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

    private void showDialog() {
        // Sprawdzenie czy popup został już wyświetlony
        boolean isPopupDisplayed = sharedPreferences.getBoolean("popup_displayed", false);
        Log.d("PopupDisplayed", "Popup displayed: " + isPopupDisplayed); // Log sprawdzający

        // Jeśli popup nie został jeszcze wyświetlony, wyświetlamy go
        if (!isPopupDisplayed) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            LayoutInflater inflater = requireActivity().getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.mainpopup, null);
            builder.setView(dialogView);

            popupDialog = builder.create();

            Button startButton = dialogView.findViewById(R.id.start);
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hidePopUp();
                }
            });

            // Po wyświetleniu dialogu ustawiamy flagę w SharedPreferences
            popupDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("popup_displayed", true);
                    editor.apply();
                }
            });

            popupDialog.show();
        }
    }

    private void hidePopUp() {
        if (popupDialog != null && popupDialog.isShowing()) {
            popupDialog.dismiss();
        }
    }

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