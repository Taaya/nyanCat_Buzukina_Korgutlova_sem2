package ru.kpfu.itis.buzukina_korgutlova.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenu implements Initializable {
    private static Stage stage;
    private Scene mainScene;
    private Scene rulesScene;

    public  void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
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


    public void clickOnPlayButton(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/enterName.fxml"));
        AnchorPane root = loader.load();
        ControllerEnterName controllerEnterName = loader.getController();
        controllerEnterName .setStage(stage);
        Scene scene = new Scene(root);
        controllerEnterName .setSceneEnterName(scene);
        stage.setScene(scene);
    }

    public void clickOnRulesButton(MouseEvent mouseEvent) throws IOException {
        stage.setScene(rulesScene);
    }

    public void clickOnExitButton(MouseEvent mouseEvent) {
        stage.close();
    }

}
