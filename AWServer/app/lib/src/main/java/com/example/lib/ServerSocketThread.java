package com.example.lib;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import javax.lang.model.element.NestingKind;

public class ServerSocketThread extends Thread{
    private BufferedReader in1;
    private BufferedReader in2;
    private PrintWriter pw1;
    private PrintWriter pw2;
    private Socket player1;
    private Socket player2;

    private boolean game1Over;
    private boolean game2Over;
    public ServerSocketThread(Socket player1, Socket player2){
        this.player1 = player1;
        this.player2 = player2;
        game1Over = false;
        game2Over = false;
    }

    @Override
    public void run(){
        try {
            in1 = new BufferedReader(new InputStreamReader(player1.getInputStream(), StandardCharsets.UTF_8));
            in2 = new BufferedReader(new InputStreamReader(player2.getInputStream(), StandardCharsets.UTF_8));

            pw1 = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(player1.getOutputStream(), StandardCharsets.UTF_8)), true);
            pw2 = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(player2.getOutputStream(), StandardCharsets.UTF_8)), true);

            sendMessage(pw1, "begin", "player1");
            sendMessage(pw2, "begin", "player2");

            String content1;
            String content2;

            do {

                //Thread.sleep(1000);
                content1 = in1.readLine();
                content2 = in2.readLine();
                System.out.println(content1);
                System.out.println(content2);

                if (Objects.equals(content1, "over")) game1Over = true;
                if (Objects.equals(content2, "over")) game2Over = true;
                System.out.println(game1Over);
                System.out.println(game2Over);

                if (game1Over && game2Over) {
                    System.out.println("Disconnect from client, close socket");
                    sendMessage(pw1, "over", "player1");
                    sendMessage(pw2, "over", "player2");
                    break;
                }

                if (content1 != null && !Objects.equals(content1, "over")) sendMessage(pw2, content1, "player2");
                if (content2 != null && !Objects.equals(content2, "over")) sendMessage(pw1, content2, "player1");

            } while (content1 != null || content2 != null);
            System.out.println("aaaaa");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(PrintWriter pout, String message, String toPlayer) {
        System.out.println("Message to " + toPlayer + ": " + message);
        pout.println(message);
    }
}
