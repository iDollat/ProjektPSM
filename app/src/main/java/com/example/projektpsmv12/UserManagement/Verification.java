package com.example.projektpsmv12.UserManagement;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projektpsmv12.R;

public class Verification extends Fragment {
    // Definiujesz zmienną logiczną
    private boolean isVerified = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int layoutId = isVerified ? R.layout.fragment_verified : R.layout.fragment_verification;
        return inflater.inflate(layoutId, container, false);
    }


}
