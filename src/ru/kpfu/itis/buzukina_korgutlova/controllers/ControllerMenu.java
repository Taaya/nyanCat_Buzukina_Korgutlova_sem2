package ru.kpfu.itis.buzukina_korgutlova.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {
    private static Stage stage;
    private Scene mainScene;
    private Scene rulesScene;
    private Scene gameScene;

    public Scene getMainScene() {
        return mainScene;
    }

    public  void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Scene getRulesScene() {
        return rulesScene;
    }

    public  void setRulesScene(Scene rulesScene) {
        this.rulesScene = rulesScene;
    }

    public void setStage(Stage stage) {
        ControllerMenu.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Loading...");

    }


    public void clickOnPlayButton(MouseEvent mouseEvent) {
        stage.setScene(gameScene);
    }

    public void clickOnRulesButton(MouseEvent mouseEvent) throws IOException {
        stage.setScene(rulesScene);
    }

    public void clickOnExitButton(MouseEvent mouseEvent) {
        stage.close();
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
}
