package com.example.aircraftwar2024.activity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aircraftwar2024.DAO.DbOpenHelper;
import com.example.aircraftwar2024.DAO.Record;
import com.example.aircraftwar2024.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingActivity extends AppCompatActivity {
    private static final String TAG = "RankingActivity";

    private SQLiteOpenHelper dbOpenHelper;
    private int size = 0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Record record = null;
        int gameType = 0;
        if (getIntent() != null) {
            record = getIntent().getSerializableExtra("record", Record.class);
            gameType = getIntent().getIntExtra("gameType", 0);
        }

        TextView mode = findViewById(R.id.mode);
        String modeName;
        switch (gameType) {
            case 1 -> modeName = "简单模式";
            case 2 -> modeName = "普通模式";
            case 3 -> modeName = "困难模式";
            default -> modeName = "无模式";
        }
        mode.setText(modeName);

        dbOpenHelper = new DbOpenHelper(this, "ranking", null, 1);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (record != null) {
            db.execSQL("INSERT INTO records(id, name, score, time) VALUES(?, ?, ?, ?)", new Object[]{size, record.getName(), record.getScore(), record.getTime()});
        }

        ListView list = findViewById(R.id.list);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                getData(db),
                R.layout.activity_item,
                new String[]{"排名", "用户名", "得分", "时间"},
                new int[]{R.id.rank, R.id.name, R.id.score, R.id.time}
        );

        list.setAdapter(simpleAdapter);
        dbOpenHelper.close();

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
                    Log.d(TAG, "return MainActivity");
                    ActivityManager.finishActivity(RankingActivity.class);
                    ActivityManager.finishActivity(GameActivity.class);
                    ActivityManager.finishActivity(OfflineActivity.class);
                    finish();
                }
        );
    }

    private List<Map<String, Object>> getData(SQLiteDatabase db) {
        List<Map<String, Object>> itemList = new ArrayList<>();

        Cursor cursor = db.query("records", null, null, null, null, null, "score DESC");
        int rank = 1;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<>();
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow("score"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
                map.put("排名", rank);
                map.put("用户名", name);
                map.put("得分", score);
                map.put("时间", time);
                itemList.add(map);
                rank += 1;
            }
            cursor.close();
        }
        return itemList;
    }
}
