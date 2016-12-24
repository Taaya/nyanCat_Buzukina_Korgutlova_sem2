package ru.kpfu.itis.buzukina_korgutlova.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.kpfu.itis.buzukina_korgutlova.classes.Bonus;
import ru.kpfu.itis.buzukina_korgutlova.classes.Name;


import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {
    public AnchorPane mainAnchorPane;
    private Stage stage;
    private Scene sceneGame;
    private ImageView firstView;
    private ImageView secondView;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;



    public void setIO(BufferedReader bufferedReader, PrintWriter printWriter) throws IOException {
//
//        this.bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
//        this.printWriter = new PrintWriter(s.getOutputStream(), true);
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading game area..");
        Socket s = null;
        try {
            s = new Socket("localhost", 3456);
            setIO(new BufferedReader(new InputStreamReader(s.getInputStream())), new PrintWriter(s.getOutputStream(), true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int picture = 0;
        try {
            if(bufferedReader.ready())
            picture = bufferedReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image firstImage;
        Image secondImage;
        System.out.println("picture: " + picture);
        if (picture == 1){
            firstImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat1.png"));
            secondImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat2.png"));
        } else{
            firstImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat2.png"));
            secondImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat1.png"));
        }

        firstView = new ImageView(firstImage);
        secondView = new ImageView(secondImage);
        firstView.setFitWidth(100);
        firstView.setFitHeight(100);
        firstView.setPreserveRatio(true);
        firstView.setX(100);
        secondView.setX(100);
        secondView.setY(300);
        secondView.setFitHeight(100);
        secondView.setFitWidth(100);
        secondView.setPreserveRatio(true);
        mainAnchorPane.getChildren().add(firstView);
        mainAnchorPane.getChildren().add(secondView);
        try {
            initMovie();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initArea();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneGame(Scene sceneGame) {
        this.sceneGame = sceneGame;
    }

    public void initMovie() throws IOException {
        final int STEP = 11;
        Socket s = new Socket("localhost", 3456);
        setIO(new BufferedReader(new InputStreamReader(s.getInputStream())), new PrintWriter(s.getOutputStream(), true));
        mainAnchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DOWN && firstView.getY() < stage.getHeight() - firstView.getFitHeight() - 2 * STEP){
                System.out.println(firstView.getY());
                firstView.setY(firstView.getY() + STEP);
                System.out.println(printWriter);
                printWriter.write((int) (firstView.getY() + STEP));
                System.out.println("new Y: " + (int) (firstView.getY() + STEP));
            }
            if (event.getCode() == KeyCode.UP && firstView.getY() != 0) {
                firstView.setY(firstView.getY() - STEP);

                printWriter.write((int) (firstView.getY() - STEP));
            }
        });
    }

    public Scene getSceneGame() {
        return sceneGame;
    }

    private void initArea() {
        ArrayList<Bonus> bonusArrayList = new ArrayList<>();
        Random random = new Random();
        Name [] names = Name.values();
        for (int i = 0; i < 100; i++){
            bonusArrayList.add(new Bonus(names[random.nextInt(3)], random.nextInt(20)* 20 + 450, random.nextInt(7) * 50 + 30));
        }
        for(Bonus bonus: bonusArrayList){
           mainAnchorPane.getChildren().add(bonus.getImageView());
        }
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                for(Bonus bonus: bonusArrayList){
                    bonus.setX(bonus.getX() - 1);
                    if(bonus.getX() <= 150 && bonus.getX() >= 100){
                        if(bonus.getY() <= firstView.getY() + firstView.getFitHeight() &&
                                bonus.getY() + bonus.getImageView().getFitHeight() >= firstView.getY()){
                            mainAnchorPane.getChildren().remove(bonus.getImageView());
                        }
                    }
                }
            }
        }.start();
    }
}
