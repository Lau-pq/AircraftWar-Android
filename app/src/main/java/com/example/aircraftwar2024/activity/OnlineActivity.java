package com.example.aircraftwar2024.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.game.MediumGame;

public class OnlineActivity extends AppCompatActivity {
    private static final String TAG = "OnlineActivity";

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Handler handler;

    BaseGame game;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                }
            }
        };
    }

    class NetConnect implements Runnable {
        private Handler toCilentHandle;

        public NetConnect(Handler handler) {
            this.toCilentHandle = handler;
        }
        @Override
        public void run() {
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress
                        ("10.0.2.2", 9999), 5000);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                writer = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
                Log.i(TAG, "connect to server");

                // begin {enemyName enemyScore}
                new Thread(() -> {
                    String fromserver;
                    try {
                        while((fromserver = reader.readLine()) != null) {
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = fromserver;
                            toCilentHandle.sendMessage(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
