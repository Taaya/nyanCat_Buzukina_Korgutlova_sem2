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
import ru.kpfu.itis.buzukina_korgutlova.content.Bonus;
import ru.kpfu.itis.buzukina_korgutlova.content.Name;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading game area..");
        Image firstImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat.png"));
        Image secondImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat.png"));
        firstView = new ImageView(firstImage);
        secondView = new ImageView(secondImage);
        firstView.setFitWidth(50);
        firstView.setFitHeight(50);
        firstView.setPreserveRatio(true);
        firstView.setX(100);
        secondView.setX(100);
        secondView.setY(300);
        secondView.setFitHeight(50);
        secondView.setFitWidth(50);
        secondView.setPreserveRatio(true);
        mainAnchorPane.getChildren().add(firstView);
        mainAnchorPane.getChildren().add(secondView);
        initMovie();
        initArea();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneGame(Scene sceneGame) {
        this.sceneGame = sceneGame;
    }

    public void initMovie() {
        final int STEP = 20;
        mainAnchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DOWN && firstView.getY() < stage.getHeight() - firstView.getFitHeight() - 2 * STEP){
                System.out.println(firstView.getY());
                firstView.setY(firstView.getY() + STEP);
            }
            if (event.getCode() == KeyCode.UP && firstView.getY() != 0) {
                firstView.setY(firstView.getY() - STEP);
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
                    bonus.setX(bonus.getX() - 3);
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
