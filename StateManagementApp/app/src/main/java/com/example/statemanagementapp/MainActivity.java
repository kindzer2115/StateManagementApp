package com.example.statemanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_COUNT = "count";
    private static final String KEY_DARK_MODE = "dark_mode";
    private TextView textViewCount;
    private TextView textViewCheckStatus;
    private int count = 0;
    private boolean isDarkMode = false;
    private Switch switchDarkMode;
    private CheckBox buttonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCount = findViewById(R.id.textViewCount);
        textViewCheckStatus = findViewById(R.id.textViewCheckStatus);
        Button buttonIncrement = findViewById(R.id.buttonIncrement);
        switchDarkMode = findViewById(R.id.switchDarkMode);
        buttonCheck = findViewById(R.id.buttonCheck);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT);
            isDarkMode = savedInstanceState.getBoolean(KEY_DARK_MODE);
            updateDarkMode();
        }

        updateCountText();

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateCountText();
            }
        });

        switchDarkMode.setChecked(isDarkMode);
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDarkMode = isChecked;
            updateDarkMode();
        });

        buttonCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                textViewCheckStatus.setText("Zaznaczony");
            } else {
                textViewCheckStatus.setText("");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putBoolean(KEY_DARK_MODE, isDarkMode);
    }

    protected void updateCountText() {
        textViewCount.setText("Licznik: " + count);
    }

    private void updateDarkMode() {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
