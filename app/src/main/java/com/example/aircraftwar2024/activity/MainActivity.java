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
import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.message.GameMessage;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    Button offlineButton;
    Button onlineButton;
    RadioGroup bgmGroup;

    private Handler handler;
    private int gameType;

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
                    case GameMessage.begin -> {
                        gameType = 2;
                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("gameType", gameType);
                        startActivity(intent);
                    }
                    case GameMessage.over -> {
                        BaseGame.online = true;
                        Intent intent = new Intent(MainActivity.this, EndActivity.class);
                        startActivity(intent);
                    }
                    case GameMessage.enemy -> {
                        BaseGame.enemyScore = Integer.parseInt((String) msg.obj);
                    }
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
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                    .setMessage("匹配中，请等待……")
                    .setCancelable(false)
                    .create();
            alertDialog.show();
            new Thread(new NetConnect(handler)).start();
        });
    }
}