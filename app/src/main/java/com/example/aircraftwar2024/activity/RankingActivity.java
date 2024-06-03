package com.example.aircraftwar2024.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aircraftwar2024.R;

import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView list = findViewById(R.id.list);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                getData(),
                R.layout.activity_item,
                new String[]{"排名", "用户名", "得分", "时间"},
                new int[]{R.id.rank, R.id.name, R.id.score, R.id.time}
        );

        list.setAdapter(simpleAdapter);

        // TODO: 删除弹窗
        list.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(RankingActivity.this)
                    .setTitle("提示")
                    .setMessage("确定删除该条记录么")
                    .setPositiveButton("确定", (dialog, which) -> {
                        // TODO 删除一行数据 使用 position
                        simpleAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("取消", (dialog, which) -> {})
                    .create();
            alertDialog.show();
        });


        Button returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(view->{
                    // TODO: 返回首页
                    ActivityManager.finishActivity(GameActivity.class);
                    ActivityManager.finishActivity(OfflineActivity.class);
                }
        );
    }

    private List<Map<String, Object>> getData() {
        return null;
    }
}
