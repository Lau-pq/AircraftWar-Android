package com.example.lib;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final String TAG = "Server";

    private List<Socket> matchRequests;

    public Server() {
        matchRequests = new ArrayList<>();
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("local host:" + address);

            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("listen port 9999");

            while (true) {
                System.out.println("waiting client connect");
                Socket socket = serverSocket.accept();
                System.out.println("accept client connect" + socket);
                matchRequests.add(socket);

                if (matchRequests.size() >= 2) {
                    Socket player1 = matchRequests.get(0);
                    Socket player2 = matchRequests.get(1);

                    new Thread(new Service(player1, player2)).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Service implements Runnable {
        private Socket player1;
        private Socket player2;

        public Service(Socket player1, Socket player2) {
            this.player1 = player1;
            this.player2 = player2;
        }

        private void sendMessage(Socket player, String message) {
            
        }

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
