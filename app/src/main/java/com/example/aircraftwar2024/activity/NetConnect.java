package com.example.aircraftwar2024.activity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.aircraftwar2024.aircraft.HeroAircraft;
import com.example.aircraftwar2024.game.BaseGame;
import com.example.aircraftwar2024.message.GameMessage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class NetConnect implements Runnable{
    private static final String TAG = "NetConnect";
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Handler toCilentHandle;
    private String fromserver;

    private String hostname = "10.250.41.201";

    public NetConnect(Handler handler) {
        this.toCilentHandle = handler;
    }
    @Override
    public void run() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress
                    (hostname, 9999), 5000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
            Log.i(TAG, "connect to server");

            // start enemy over
            new Thread(() -> {
                try {
                    while((fromserver = reader.readLine()) != null) {
                        Message msg = new Message();
                        switch (fromserver) {
                            case "begin" -> msg.what = GameMessage.begin;
                            case "over" -> msg.what = GameMessage.over;
                            default -> msg.what = GameMessage.enemy;
                        }
                        msg.obj = fromserver;
                        toCilentHandle.sendMessage(msg);
                        Log.i(TAG, fromserver);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            try {
                while (!BaseGame.gameOverFlag) {
                    Thread.sleep(50);
                    int score = BaseGame.score;
                    writer.println(score);
                }
                while (!Objects.equals(fromserver, "over")) {
                    writer.println("over");
                }
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();
                BaseGame.drawLoop = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

