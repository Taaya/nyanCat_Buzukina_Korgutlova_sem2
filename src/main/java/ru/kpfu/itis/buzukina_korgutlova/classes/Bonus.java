package ru.kpfu.itis.buzukina_korgutlova.classes;

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

    public Bonus(Name name, ImageView imageView, double x, double y){
        this.name = name;
        switch (name){
            case FLOWER:
                this.value = 2;
                break;
            case FISH:
                this.value = 5;
            case STAR:
                this.value = 10;
        }
        this.imageView = imageView;
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
