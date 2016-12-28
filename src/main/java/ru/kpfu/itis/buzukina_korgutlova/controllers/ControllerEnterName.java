package ru.kpfu.itis.buzukina_korgutlova.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEnterName implements Initializable{
    private static final int WIDTH = 900;
    private static final int HEIGHT = 550;
    public TextField name;
    private Stage stage;
    private Scene sceneEnterName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void buttonClick(MouseEvent mouseEvent) throws IOException {
        Socket s = new Socket("localhost", 3456);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/game.fxml"));
        AnchorPane root = loader.load();
        ControllerGame controllerGame = loader.getController();
        controllerGame.setStage(stage);
        Scene sceneGame = new Scene(root, WIDTH, HEIGHT);
        controllerGame.setSceneGame(sceneGame);
        controllerGame.setIO(new BufferedReader(new InputStreamReader(s.getInputStream())), new PrintWriter(s.getOutputStream(), true));
        controllerGame.getPrintWriter().println(name.getText());
        controllerGame.initGame();
        stage.setScene(sceneGame);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneEnterName(Scene sceneEnterName) {
        this.sceneEnterName = sceneEnterName;
    }
}
