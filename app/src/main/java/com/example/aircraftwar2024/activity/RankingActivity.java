package com.example.aircraftwar2024.activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Objects;

public class RankingActivity extends AppCompatActivity {

    private SQLiteOpenHelper dbOpenHelper;
    private String tableName;

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
            case 1 -> {
                modeName = "简单模式";
                tableName = "easyRecords";
            }
            case 2 -> {
                modeName = "普通模式";
                tableName = "mediumRecords";
            }
            case 3 -> {
                modeName = "困难模式";
                tableName = "hardRecords";
            }
            default -> {
                modeName = "无模式";
                tableName = "无表格";
            }
        }
        mode.setText(modeName);

        dbOpenHelper = new DbOpenHelper(this, "ranking", null, 1);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        if (record != null) {
            String sql = String.format("INSERT INTO %s (name, score, time) VALUES(?, ?, ?, ?)", tableName);
            db.execSQL(sql, new Object[]{record.getName(), record.getScore(), record.getTime()});
        }

        ListView list = findViewById(R.id.list);
        List<Map<String, Object>> adapterData = getData(db);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                adapterData,
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
                        String time = Objects.requireNonNull(adapterData.get(position).get("时间")).toString();
                        String sql = String.format("DELETE FROM %s WHERE personid = ?", tableName);
                        db.execSQL(sql, new String[]{time});
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

    private List<Map<String, Object>> getData(SQLiteDatabase db) {
        List<Map<String, Object>> itemList = new ArrayList<>();

        Cursor cursor = db.query(tableName, null, null, null, null, null, "score DESC");
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
