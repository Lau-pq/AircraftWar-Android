package com.example.aircraftwar2024.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar2024.R;
import com.example.aircraftwar2024.game.BaseGame;


public class EndActivity extends AppCompatActivity {

    private TextView resultText;
    private TextView myScoreText;
    private TextView enemyScoreText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        ActivityManager.addActivity(this);

        resultText = findViewById(R.id.result);
        myScoreText = findViewById(R.id.my_score);
        enemyScoreText = findViewById(R.id.enemy_score);

        int myScore = BaseGame.score;
        int enemyScore = BaseGame.enemyScore;
        String result;

        if (myScore > enemyScore) result = "险胜";
        else if (myScore == enemyScore) result = "平局";
        else result = "惜败";

        resultText.setText(result);
        myScoreText.setText(String.valueOf("你的分数: " + myScore));
        enemyScoreText.setText(String.valueOf("对手分数: " + enemyScore));

        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(view->{
                    ActivityManager.finishActivity(EndActivity.class);
                    ActivityManager.finishActivity(GameActivity.class);
                    finish();
                }
        );

    }


}
