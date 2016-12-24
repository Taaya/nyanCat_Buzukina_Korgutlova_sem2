package ru.kpfu.itis.buzukina_korgutlova.classes;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import ru.kpfu.itis.buzukina_korgutlova.helpers.Connection;

public class Player implements Runnable{
    Scanner scanner = new Scanner(System.in);

    int PORT = 3456;
    String HOST = "localhost";

    //    private Connection connection;
    Player opponent;
    private Game game;
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
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }
//    public Player(Socket socket, String name) throws IOException {
//        this.socket = socket;
//        this.name = in.readLine();
//        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//        this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"), true);
//    }

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
                    coordinates = in.readLine();
//                    for (Connection connection: connections) {
//                        System.out.println("coordinates: " + coordinates);
//                        connection.getPrintWriter().println(this.name + ": " + coordinates);
//                    }
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

