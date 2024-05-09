package com.example.projektpsmv12.ui.calorie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projektpsmv12.R;

public class CalorieCalculator extends Fragment {

    private EditText weightEditText;
    private EditText heightEditText;
    private EditText ageEditText;
    private Spinner genderSpinner;
    private Spinner activityLevelSpinner;
    private Button calculateButton;
    private TextView calorieResultTextView;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calorie, container, false);

        weightEditText = root.findViewById(R.id.weight_cal);
        heightEditText = root.findViewById(R.id.height_cal);
        ageEditText = root.findViewById(R.id.age);
        genderSpinner = root.findViewById(R.id.gender_spinner);
        activityLevelSpinner = root.findViewById(R.id.activity_level_spinner);
        calculateButton = root.findViewById(R.id.calculate_button);
        calorieResultTextView = root.findViewById(R.id.calorie_result);

        // Ustawienie adaptera z niestandardowym layoutem dla genderSpinner
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                inflater.getContext(), R.array.gender_array, R.layout.spinner_item);
        genderAdapter.setDropDownViewResource(R.layout.spinner_item); // dla rozwijanej listy
        genderSpinner.setAdapter(genderAdapter);

        // Ustawienie adaptera z niestandardowym layoutem dla activityLevelSpinner
        ArrayAdapter<CharSequence> activityLevelAdapter = ArrayAdapter.createFromResource(
                inflater.getContext(), R.array.activity_level_array, R.layout.spinner_item);
        activityLevelAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityLevelSpinner.setAdapter(activityLevelAdapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCalories();
            }
        });

        return root;
    }

    public void calculateCalories() {
        if (weightEditText.getText().toString().isEmpty() ||
                heightEditText.getText().toString().isEmpty() ||
                ageEditText.getText().toString().isEmpty()) {
            calorieResultTextView.setText("Please fill in all fields.");
            return;
        }

        double weight = Double.parseDouble(weightEditText.getText().toString());
        double height = Double.parseDouble(heightEditText.getText().toString());
        int age = Integer.parseInt(ageEditText.getText().toString());
        String gender = genderSpinner.getSelectedItem().toString();

        int activityLevelIndex = activityLevelSpinner.getSelectedItemPosition();
        String[] activityLevels = getResources().getStringArray(R.array.activity_level_array);
        String activityLevelString = activityLevels[activityLevelIndex];

        double activityLevel;
        switch (activityLevelString) {
            case "Sedentary (little or no exercise)":
                activityLevel = 1.2;
                break;
            case "Lightly active (light exercise 1-3 days a week)":
                activityLevel = 1.375;
                break;
            case "Moderately active (moderate exercise 3-5 days a week)":
                activityLevel = 1.55;
                break;
            case "Very active (intense exercise 6-7 days a week)":
                activityLevel = 1.725;
                break;
            case "Extra active (very intense exercise or physical job)":
                activityLevel = 1.9;
                break;
            default:
                activityLevel = 1.0;
        }

        double result = calculateCalories(weight, height, age, gender, activityLevel);
        calorieResultTextView.setVisibility(View.VISIBLE);
        calorieResultTextView.setText("Estimated daily calorie requirement: " + result);
    }

    private double calculateCalories(double weight, double height, int age, String gender, double activityLevel) {
        double bmr;
        if (gender.equals("Male")) {
            bmr = 10 * weight + 6.25 * height - 5 * age + 5;
        } else {
            bmr = 10 * weight + 6.25 * height - 5 * age - 161;
        }
        return bmr * activityLevel;
    }
}
