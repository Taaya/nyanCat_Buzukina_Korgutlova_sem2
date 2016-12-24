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

            Scanner sc = new Scanner(System.in);

            socket = ss.accept();
            Player p1 = new Player(socket);
            p1.getPrintWriter().write(1);
            players.add(p1);
            System.out.println("Client 1 is connected");

            socket = ss.accept();
            Player p2 = new Player(socket);
            p1.getPrintWriter().write(2);
            players.add(p2);
            System.out.println("Client 2 is connected");

            System.out.println("Name 1: ");
            p1.getPrintWriter().write(sc.nextLine());
            System.out.println("Name 2: ");
            p2.getPrintWriter().write(sc.nextLine());

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