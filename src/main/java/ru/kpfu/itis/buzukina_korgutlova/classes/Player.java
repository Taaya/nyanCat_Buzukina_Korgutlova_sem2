package ru.kpfu.itis.buzukina_korgutlova.classes;

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
        this.name = in.readLine();
    }

    public void start(){
        this.thread = new Thread(this.thread);
        thread.start();
    }

    @Override
    public void run() {

        while (true){
            try {
                String coordinates;
                if(in.ready()) {

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        System.out.print("Введите имя: ");
//        String name = scanner.nextLine();
//        out.println(name);
//
//        while (true) {
//
//            System.out.print("Введите сообщение: ");
//            String outMessage = scanner.nextLine();
//            out.println(outMessage);
//
//            try {
//                while (in.ready()) {
//                    String inMessage = in.readLine();
//                    System.out.println(inMessage);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
    }

    public PrintWriter getPrintWriter() {
        return out;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}

