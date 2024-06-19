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
            System.out.println("Local host: " + address);

            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Listen port 9999");

            while (true) {
                System.out.println("Waiting client connect");
                Socket socket = serverSocket.accept();
                System.out.println("Accept client connect" + socket);
                matchRequests.add(socket);

                if (matchRequests.size() >= 2) {
                    Socket player1 = matchRequests.get(0);
                    Socket player2 = matchRequests.get(1);
                    matchRequests.remove(0);
                    matchRequests.remove(0);

                    new Thread(new ServerSocketThread(player1, player2)).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }

}
