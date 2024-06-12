package com.example.aircraftwar2024.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar2024.DAO.Record;
import com.example.aircraftwar2024.aircraft.HeroAircraft;
import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.game.EasyGame;
import com.example.aircraftwar2024.game.HardGame;
import com.example.aircraftwar2024.game.MediumGame;
import com.example.aircraftwar2024.message.GameMessage;

import java.util.Objects;


public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    private int gameType=0;
    public static int screenWidth,screenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.addActivity(this);


        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.d(TAG, "handleMessage");
                if (msg.what == GameMessage.over) {
                    Intent intent = new Intent(GameActivity.this, RankingActivity.class);
                    Record record = (Record)msg.obj;

                    intent.putExtra("record", record);
                    intent.putExtra("gameType", gameType);

                    startActivity(intent);
                }
            }
        };

        getScreenHW();

        if(getIntent() != null){
            gameType = getIntent().getIntExtra("gameType",1);
        }

        BaseGame baseGameView;
        switch (gameType) {
            case 1 -> baseGameView = new EasyGame(this, handler);
            case 2 -> baseGameView = new MediumGame(this, handler);
            case 3 -> baseGameView = new HardGame(this, handler);
            default -> baseGameView = null;
        }
        setContentView(baseGameView);
    }

    public void getScreenHW(){
        //定义DisplayMetrics 对象
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getDisplay().getRealMetrics(dm);

        //窗口的宽度
        screenWidth= dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

        Log.i(TAG, "screenWidth : " + screenWidth + " screenHeight : " + screenHeight);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}