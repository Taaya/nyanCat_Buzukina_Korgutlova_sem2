package ru.kpfu.itis.buzukina_korgutlova.classes;

import java.util.List;

public class Game implements Runnable {
    private List<Player> playerList;
    private Thread thread;

    public Game(List<Player> playerList) {
        this.playerList = playerList;
        for(Player player: playerList){
            player.setGame(this);
            player.getPrintWriter().println("Game started");
            boolean game = true;
            while (game){

                //player.getOpponent().setCoordinates(out);

            }
            //player.start();
        }
        this.thread = new Thread(this);
        thread.start();
        System.out.println("Game is created");
    }

    @Override
    public void run() {
        System.out.println("Game is started");


    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
