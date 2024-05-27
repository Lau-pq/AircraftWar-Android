package com.example.aircraftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.aircraftwar2024.R;

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
            String selectText = ((RadioButton)findViewById(bgmGroup.getCheckedRadioButtonId())).getText().toString();
            boolean bgmOn = selectText.equals("开启音效");

            Intent intent = new Intent(MainActivity.this, OfflineActivity.class);
            intent.putExtra("bgm", bgmOn);

            startActivity(intent);
        });
    }
}