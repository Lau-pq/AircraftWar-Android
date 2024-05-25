package com.example.aircraftwar2024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    RadioGroup bgmGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        bgmGroup = findViewById(R.id.bgm_group);

        startButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
            // TODO 应用 intent.putExtra 将音乐是否开启作为参数传递

            startActivity(intent);
        });
    }
}