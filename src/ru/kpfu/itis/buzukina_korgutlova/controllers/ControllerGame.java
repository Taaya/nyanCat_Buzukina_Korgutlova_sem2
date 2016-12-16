package ru.kpfu.itis.buzukina_korgutlova.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable{
    private Stage stage;
    private Scene gameScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
}
