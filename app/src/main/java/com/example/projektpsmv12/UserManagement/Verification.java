package com.example.projektpsmv12.UserManagement;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projektpsmv12.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Verification extends Fragment {
    // Definiujesz zmienną logiczną
    private boolean isVerified = false;
    private Connection connection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // W zależności od wartości isVerified wybierasz układ
        int layoutId = isVerified ? R.layout.fragment_verified : R.layout.fragment_verification;
        return inflater.inflate(layoutId, container, false);
    }
}
