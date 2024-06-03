package com.example.aircraftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.aircraftwar2024.R;

public class OfflineActivity extends AppCompatActivity {

    private int gameType = 0;
    boolean bgmOn;
    Button easyButton;
    Button mediumButton;
    Button hardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);
        setContentView(R.layout.activity_offline);

        if(getIntent() != null){
            bgmOn = getIntent().getBooleanExtra("bgmOn",false);
        }

        easyButton = findViewById(R.id.easy_button);
        mediumButton = findViewById(R.id.medium_button);
        hardButton = findViewById(R.id.hard_button);

        Intent intent = new Intent(OfflineActivity.this, GameActivity.class);
        easyButton.setOnClickListener(view -> {
            gameType = 1;
            intent.putExtra("gameType", gameType);
            startActivity(intent);
        });

        mediumButton.setOnClickListener(view -> {
            gameType = 2;
            intent.putExtra("gameType", gameType);
            startActivity(intent);
        });

        hardButton.setOnClickListener(view -> {
            gameType = 3;
            intent.putExtra("gameType", gameType);
            startActivity(intent);
        });

    }
}