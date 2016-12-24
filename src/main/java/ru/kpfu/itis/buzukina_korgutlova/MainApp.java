package ru.kpfu.itis.buzukina_korgutlova;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerGame;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerMenu;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerRules;

public class MainApp extends Application {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 550;
    private ControllerMenu controllerMenu;
    private ControllerRules controllerRules;
    private Scene sceneMenu;
    private Scene sceneRules;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        initMenu();
        initRules();
        controllerRules.setMainScene(sceneMenu);
        controllerMenu.setRulesScene(sceneRules);
        initStage(primaryStage, sceneMenu);

    }


    private void initStage(Stage primaryStage, Scene scene) {
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("Nyan Cat");
        this.primaryStage.setScene(sceneMenu);
        this.primaryStage.show();
        this.primaryStage.getScene().getRoot().requestFocus();
    }

    private void initMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/menu.fxml"));
        AnchorPane root = loader.load();
        controllerMenu = loader.getController();
        controllerMenu.setStage(primaryStage);
        sceneMenu = new Scene(root, WIDTH, HEIGHT);
        controllerMenu.setMainScene(sceneMenu);
    }

    private void initRules() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/rules.fxml"));
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