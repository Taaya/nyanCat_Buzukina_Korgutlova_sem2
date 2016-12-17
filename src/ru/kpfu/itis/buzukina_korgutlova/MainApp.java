package ru.kpfu.itis.buzukina_korgutlova;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerMenu;
import ru.kpfu.itis.buzukina_korgutlova.controllers.ControllerRules;

public class MainApp extends Application {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
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
        initStage();
    }

    private void initStage() {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Nyan Cat");
        primaryStage.setScene(sceneMenu);
        primaryStage.show();
        primaryStage.getScene().getRoot().requestFocus();
    }

    private void initMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/menu.fxml"));
        AnchorPane root = loader.load();
        controllerMenu = loader.getController();
        controllerMenu.setStage(primaryStage);
        sceneMenu = new Scene(root);
        controllerMenu.setMainScene(sceneMenu);
    }

    private void initRules() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/rules.fxml"));
        AnchorPane root = loader.load();
        controllerRules = loader.getController();
        controllerRules.setStage(primaryStage);
        sceneRules = new Scene(root);
        controllerRules.setRulesScene(sceneRules);
    }


    public static void main(String[] args) {
        launch(args);
    }
}