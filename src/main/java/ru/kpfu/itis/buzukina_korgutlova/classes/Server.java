package ru.kpfu.itis.buzukina_korgutlova.classes;

import ru.kpfu.itis.buzukina_korgutlova.helpers.Connection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<Connection> connectionPool;
    private List<Game> forGame;
    private final int PORT = 3456;

    public Server() {
        init();
    }

    private void init() {
        forGame = new ArrayList<>();
    }


    public void go() throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        while (true) {
            List<Player> players = new ArrayList<>();
            Socket socket = null;

            socket = ss.accept();
            Player p1 = new Player(socket);
            players.add(p1);
            System.out.println("Client 2 is connected");

            socket = ss.accept();
            Player p2 = new Player(socket);
            players.add(p2);
            System.out.println("Client 2 is connected");

            p1.setOpponent(p2);
            p2.setOpponent(p1);


            OutputStream os = socket.getOutputStream();


            Game game = new Game(players);
            forGame.add(game);
        }
    }

    public static void main(String[] args) throws IOException {
        (new Server()).go();
    }
}