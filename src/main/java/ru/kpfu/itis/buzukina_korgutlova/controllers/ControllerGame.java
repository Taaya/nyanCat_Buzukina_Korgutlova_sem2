package ru.kpfu.itis.buzukina_korgutlova.controllers;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.kpfu.itis.buzukina_korgutlova.classes.Bonus;
import ru.kpfu.itis.buzukina_korgutlova.classes.Name;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 550;
    public AnchorPane mainAnchorPane;
    @FXML
    private Label name1;
    @FXML
    private Label name2;
    private Stage stage;
    private Scene sceneGame;
    private ImageView firstView;
    private ImageView secondView;
    private BufferedReader bufferedReader;

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    private PrintWriter printWriter;
    private ArrayList<Image> images;
    public Label gameTime;



    public void setIO(BufferedReader bufferedReader, PrintWriter printWriter) throws IOException {
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initGame() throws IOException {
        System.out.println("Loading game area..");
        int picture = 0;
        try {
            while(!bufferedReader.ready());
                picture = Integer.parseInt(bufferedReader.readLine());
                //name1.setText(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image firstImage;
        Image secondImage;
        System.out.println("picture: " + picture);
        if (picture == 1) {
            firstImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat1.png"));
            secondImage = new Image(getClass().getClassLoader().getResourceAsStream("view/static/cat2.png"));
        } else {
            ImageView test = firstView;
            firstView = secondView;
            secondView = firstView;
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
        initImage();
        try {
            initArea();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initImage() {
        images = new ArrayList<>();
        images.add(new Image(getClass().getClassLoader().getResourceAsStream("view/static/flower.png")));
        images.add(new Image(getClass().getClassLoader().getResourceAsStream("view/static/fish.jpg")));
        images.add(new Image(getClass().getClassLoader().getResourceAsStream("view/static/star.png")));
        images.add(new Image(getClass().getClassLoader().getResourceAsStream("view/static/dog.png")));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneGame(Scene sceneGame) {
        this.sceneGame = sceneGame;
    }

    public void initMovie() throws IOException {
        final int STEP = 11;
        mainAnchorPane.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DOWN && firstView.getY() < stage.getHeight() - firstView.getFitHeight() - 2 * STEP) {
                //System.out.println(firstView.getY());
                firstView.setY(firstView.getY() + STEP);
                int newY = (int) (firstView.getY() + STEP);
                printWriter.print(("y  " + newY));
                System.out.println("y " + newY);
                System.out.println("new Y: " + (int) (firstView.getY() + STEP));
            }
            if (event.getCode() == KeyCode.UP && firstView.getY() != 0) {
                firstView.setY(firstView.getY() - STEP);
                int newY = (int) (firstView.getY() - STEP);
                printWriter.println("y " + newY);
                System.out.println("y " + newY);
            }
        });
    }

    public Scene getSceneGame() {
        return sceneGame;
    }

    private void initArea() throws IOException, InterruptedException {
        ArrayList<Bonus> bonusArrayList = new ArrayList<>();
        addElement(bonusArrayList);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Bonus bonus : bonusArrayList) {
                    bonus.setX(bonus.getX() - 3);
                    if (bonus.getX() <= 150 && bonus.getX() >= 100) {
                        if (bonus.getY() <= firstView.getY() + firstView.getFitHeight() &&
                                bonus.getY() + bonus.getImageView().getFitHeight() >= firstView.getY()) {
                            mainAnchorPane.getChildren().remove(bonus.getImageView());
                        }
                    }
                }


            }

        }.start();

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                Timeline timeline = timerAnimated(60);
                while (timeline.getStatus().equals(Animation.Status.RUNNING)){
                    while (true) {
                        String line = bufferedReader.readLine();
                        System.out.println("line: " + line);
                        if(line.startsWith("y ")){
                            System.out.println("substr:" + line.substring(3));
                            secondView.setY(Double.parseDouble(line.substring(3)));
                        }
                    }
                }

                return null;
            }
        };
    Thread th = new Thread(task);
    th.setDaemon(true);
    th.start();


    }

    private void addElement(ArrayList<Bonus> bonusArrayList) throws IOException, InterruptedException {
        Random random = new Random();
        Name[] names = Name.values();
        while (!bufferedReader.ready());
        while(bufferedReader.ready()) {
            String output = bufferedReader.readLine();
            if(output.startsWith("Game started")){
                break;
            } else {
                String[] line = output.split(" ");
                int numberBonus = Integer.parseInt(line[1]);
                bonusArrayList.add(new Bonus(names[numberBonus],
                        new ImageView(images.get(numberBonus)), Double.parseDouble(line[2]), Double.parseDouble(line[3])));
            }
        }
        for (Bonus bonus : bonusArrayList) {
            mainAnchorPane.getChildren().add(bonus.getImageView());
        }
        //Thread.sleep(5000);
    }

    private Timeline timerAnimated(int timeInSeconds) {
        int[] time = {timeInSeconds};
        Platform.runLater(() -> gameTime.setText((time[0] / 60) + ":" + ((time[0] % 60) < 10 ? "0" : "") + (time[0] % 60)));
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000),
                        event -> {
                            time[0] -= 1;
                            String timeValue = (time[0] / 60) + ":" + ((time[0] % 60) < 10 ? "0" : "") + (time[0] % 60);
                            Platform.runLater(() -> gameTime.setText(timeValue));
                        }
                )
        );
        timeline.setCycleCount(timeInSeconds);
        timeline.play();
        return timeline;
    }


}
