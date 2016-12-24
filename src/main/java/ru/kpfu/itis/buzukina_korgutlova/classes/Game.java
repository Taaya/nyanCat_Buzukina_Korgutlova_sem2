package ru.kpfu.itis.buzukina_korgutlova.classes;

import java.util.List;

public class Game implements Runnable {
    private List<Player> playerList;
    private Thread thread;
    private int rounds;

    public Game(List<Player> playerList) {
        this.playerList = playerList;
        for(Player player: playerList){
            player.setGame(this);
            //player.start();
        }
        this.thread = new Thread(this);
        thread.start();
        System.out.println("Game create");
    }

    @Override
    public void run() {
        System.out.println("Game started");

    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
