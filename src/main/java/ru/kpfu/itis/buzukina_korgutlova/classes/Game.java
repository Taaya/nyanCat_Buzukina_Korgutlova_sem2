package ru.kpfu.itis.buzukina_korgutlova.classes;

import java.util.List;
import java.util.Random;

public class Game {
    private List<Player> playerList;
    private Thread thread;

    public Game(List<Player> playerList) {
        this.playerList = playerList;
        for(Player player: playerList){
            player.setGame(this);
            player.start();
        }
        createBonuses();
        System.out.println("Game is created");
    }

    private void createBonuses() {
        int numberBonus;
        Random random = new Random();
        int x;
        int y;
        int width = 900;
        for(int i = 0; i < 1500;i++){
            numberBonus = random.nextInt(4);
            x = random.nextInt(25 * width) + width;
            y = random.nextInt(50) * 10 + 30;
            for(Player player: playerList){
                player.getPrintWriter().println("Bonus " + numberBonus + " " + x + " " + y);
            }
        }
        for(Player player: playerList){
            player.getPrintWriter().println("Game started");
        }

    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
