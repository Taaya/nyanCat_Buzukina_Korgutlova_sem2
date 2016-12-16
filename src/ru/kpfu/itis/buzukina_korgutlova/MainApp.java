package ru.kpfu.itis.buzukina_korgutlova;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerGame;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerMenu;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerRules;

import java.io.IOException;

public class MainApp extends Application {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private ControllerMenu controllerMenu;
    private ControllerRules controllerRules;
    private ControllerGame controllerGame;
    private Scene sceneMenu;
    private Scene sceneRules;
    private Scene sceneGame;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        initMenu();
        initRules();
        initGame();
        controllerRules.setMainScene(sceneMenu);
        controllerMenu.setRulesScene(sceneRules);
        controllerMenu.setGameScene(sceneGame);
        initStage();
    }

    private void initGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/game.fxml"));
        AnchorPane root = loader.load();
        controllerGame = loader.getController();
        controllerGame.setStage(primaryStage);
        sceneGame = new Scene(root, WIDTH, HEIGHT);
        controllerGame.setGameScene(sceneGame);
    }

    private void initStage() {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Nyan Cat");
        primaryStage.setScene(sceneMenu);
        primaryStage.show();
    }

    private void initMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/menu.fxml"));
        AnchorPane root = loader.load();
        controllerMenu = loader.getController();
        controllerMenu.setStage(primaryStage);
        sceneMenu = new Scene(root, WIDTH, HEIGHT);
        controllerMenu.setMainScene(sceneMenu);
    }

    private void initRules() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/rules.fxml"));
        AnchorPane root = loader.load();
        controllerRules = loader.getController();
        controllerRules.setStage(primaryStage);
        sceneRules = new Scene(root, WIDTH, HEIGHT);
        controllerRules.setRulesScene(sceneRules);
    }


    public static void main(String[] args) {
        launch(args);
    }
}