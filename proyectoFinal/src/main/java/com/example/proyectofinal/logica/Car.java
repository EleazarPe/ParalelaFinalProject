package com.example.proyectofinal.logica;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Car {
    private int id;
    private ImageView imageView;
    private Rectangle rectangle;
    private boolean tipoEmergencia;
    private String origen;
    private String destino;
    private AnimationTimer animationTimer;
    private boolean inCrossing;
    private boolean pare;
    private boolean running;

    public Car(ImageView imageView, Rectangle rectangle, int id, boolean tipoEmergencia, String origen) {
        this.id = id;
        this.imageView = imageView;
        this.rectangle = rectangle;
        this.tipoEmergencia = tipoEmergencia;
        this.origen = origen;
        this.inCrossing = false;
        this.pare = false;
        this.running = false;
    }

    public int getId() {
        return id;
    }


    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isTipoEmergencia() {
        return tipoEmergencia;
    }

    public void setTipoEmergencia(boolean tipoEmergencia) {
        this.tipoEmergencia = tipoEmergencia;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    public boolean isInCrossing() {
        return inCrossing;
    }

    public void setInCrossing(boolean inCrossing) {
        this.inCrossing = inCrossing;
    }

    public boolean isPare() {
        return pare;
    }

    public void setPare(boolean pare) {
        this.pare = pare;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
