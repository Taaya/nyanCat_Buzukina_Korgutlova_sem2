package ru.kpfu.itis.buzukina_korgutlova.classes;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {


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

            Player p1 = addClient(ss, 1, players);
            Player p2 = addClient(ss, 2, players);

            p1.setOpponent(p2);
            p2.setOpponent(p1);
            System.out.println(players);
            Game game = new Game(players);
            forGame.add(game);
        }
    }

    public Player addClient(ServerSocket ss, int number, List<Player> players) throws IOException {
        Socket socket = ss.accept();
        Player p1 = new Player(socket);
        p1.getPrintWriter().println(number);
        players.add(p1);
        System.out.println("Client " + number + " is connected");
        return p1;
    }

    public static void main(String[] args) throws IOException {
        (new Server()).go();
    }
}