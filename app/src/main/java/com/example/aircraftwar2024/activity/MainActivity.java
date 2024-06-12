package com.example.aircraftwar2024.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.aircraftwar2024.R;
import com.example.aircraftwar2024.application.MusicManager;


public class MainActivity extends AppCompatActivity {

    Button offlineButton;
    Button onlineButton;
    RadioGroup bgmGroup;

    private Handler handler;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(R.layout.activity_main);

        offlineButton = findViewById(R.id.offline_button);
        onlineButton = findViewById(R.id.online_button);
        bgmGroup = findViewById(R.id.bgm_group);

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {

                }
            }
        };

        offlineButton.setOnClickListener(view -> {
            String selectText = ((RadioButton)findViewById(bgmGroup.getCheckedRadioButtonId())).getText().toString();
            MusicManager.isActive = selectText.equals("开启音效");
            Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
            startActivity(intent);
        });

        onlineButton.setOnClickListener(view -> {
            String selectText = ((RadioButton)findViewById(bgmGroup.getCheckedRadioButtonId())).getText().toString();
            MusicManager.isActive = selectText.equals("开启音效");
            Intent intent = new Intent(MainActivity.this, OnlineActivity.class);
            startActivity(intent);
        });
    }
}