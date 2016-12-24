package ru.kpfu.itis.buzukina_korgutlova.classes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bonus{
    private Name name;
    private int value;
    private ImageView imageView;
    private double x;
    private double y;

    public ImageView getImageView() {
        return imageView;
    }

    public Bonus(Name name, double x, double y){
        this.name = name;
        switch (name){
            case FLOWER:
                this.value = 2;
                this.imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("view/static/flower.png")));
                break;
            case FISH:
                this.imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("view/static/fish.jpg")));
                this.value = 5;
                break;
            case STAR:
                this.imageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("view/static/star.png")));
                this.value = 10;
                break;
        }
        this.imageView.setFitHeight(30);
        this.imageView.setFitWidth(30);
        setX(x);
        setY(y);
        this.imageView.setPreserveRatio(true);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.imageView.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.imageView.setX(x);
    }
}
