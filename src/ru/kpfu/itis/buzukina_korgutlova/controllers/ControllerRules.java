package ru.kpfu.itis.buzukina_korgutlova.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerRules implements Initializable{
    private Stage stage;
    private Scene rulesScene;
    private Scene mainScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setRulesScene(Scene rulesScene) {
        this.rulesScene = rulesScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void clickMainButton(MouseEvent mouseEvent) {
        stage.setScene(mainScene);
    }
}
