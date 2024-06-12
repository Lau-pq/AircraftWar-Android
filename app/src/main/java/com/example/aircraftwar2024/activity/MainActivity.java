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

        String selectText = ((RadioButton)findViewById(bgmGroup.getCheckedRadioButtonId())).getText().toString();
        boolean bgmOn = selectText.equals("开启音效");

        // TODO 将 MusicManager.isActive 在这里赋值

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {

                }
            }
        };

        offlineButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
            intent.putExtra("bgm", bgmOn);
            startActivity(intent);
        });

        onlineButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OnlineActivity.class);
            intent.putExtra("bgm", bgmOn);
            startActivity(intent);
        });
    }
}