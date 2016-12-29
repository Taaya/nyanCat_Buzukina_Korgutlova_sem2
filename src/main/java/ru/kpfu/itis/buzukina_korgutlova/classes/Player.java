package ru.kpfu.itis.buzukina_korgutlova.classes;

import javafx.scene.control.Label;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable{
    Scanner scanner = new Scanner(System.in);

    int PORT = 3456;
    String HOST = "localhost";

    Player opponent;
    private Game game;
    private BufferedReader picture;
    private String name;
    private Label name_label;
    private Socket socket;
    private Thread thread;
    private PrintWriter out;
    private BufferedReader in;


    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);


    }

    public void start(){
        this.thread = new Thread(this.thread);
        thread.start();
    }

    @Override
    public void run() {

        while (true){
            try {
                if(in.ready()) {
                    String line = in.readLine();
                    System.out.println("line player" + line);
                    if (line.startsWith("y ")) {
                        getOpponent().getPrintWriter().println(line);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public PrintWriter getPrintWriter() {
        return out;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

