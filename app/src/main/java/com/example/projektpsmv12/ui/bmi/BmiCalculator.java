package com.example.projektpsmv12.ui.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projektpsmv12.R;
import com.example.projektpsmv12.databinding.FragmentBmiBinding;

public class BmiCalculator extends Fragment {
    private FragmentBmiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BmiViewModel bmiViewModel = new ViewModelProvider(this).get(BmiViewModel.class);
        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button calculateButton = root.findViewById(R.id.bmiBtn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcBmi(v);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void calcBmi(View view) {
        EditText weightEditText = requireView().findViewById(R.id.weight);
        EditText heightEditText = requireView().findViewById(R.id.height);
        TextView resultTextView = requireView().findViewById(R.id.resultBmi);

        try {
            double weight = Double.parseDouble(weightEditText.getText().toString());
            double height = Double.parseDouble(heightEditText.getText().toString());

            if (height <= 0 || weight <= 0) {
                resultTextView.setText("Weight and height must be greater than zero.");
                return;
            }

            double bmi = weight / ((height / 100) * (height / 100));

            resultTextView.setVisibility(View.VISIBLE);

            String category;
            if (bmi < 18.5) {
                category = "Underweight";
            } else if (bmi >= 18.5 && bmi < 25) {
                category = "Normal weight";
            } else if (bmi >= 25 && bmi < 30) {
                category = "Overweight";
            } else {
                category = "Obese";
            }

            String formattedBmi = String.format("%.2f", bmi);
            String message = category + ": " + formattedBmi;
            resultTextView.setText(message);
        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid input. Please enter valid numbers for weight and height.");
        } catch (Exception e) {
            resultTextView.setText("An unexpected error occurred.");
            e.printStackTrace();
        }
    }

}