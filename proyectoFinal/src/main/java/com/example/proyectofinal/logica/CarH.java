package com.example.proyectofinal.logica;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarH {
    private int id;
    private ImageView imageView;
    private Rectangle rectangle;
    private boolean tipoEmergencia;
    private String origen;
    private String[] destino;
    private AnimationTimer animationTimer;
    private boolean inCrossing;
    private boolean pare;
    private boolean running;
    private boolean carril = false;
    private List<Boolean> semaforos;

    public CarH(ImageView imageView, Rectangle rectangle, int id, boolean tipoEmergencia, String origen) {
        this.id = id;
        this.imageView = imageView;
        this.rectangle = rectangle;
        this.tipoEmergencia = tipoEmergencia;
        this.origen = origen;
        this.inCrossing = false;
        this.pare = false;
        this.running = false;
        this.semaforos = Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
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

    public String[] getDestino() {
        return destino;
    }

    public void setDestino(String[] destino) {
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

    public boolean getCarril() {
        return carril;
    }

    public void setCarril(boolean carril) {
        this.carril = carril;
    }

    public List<Boolean> getSemaforos() {
        return semaforos;
    }

    public Boolean getSemaforos(int id) {
        return semaforos.get(id - 1);
    }

    public void setSemaforos(int id) {
        semaforos.set(id - 1, Boolean.TRUE);
    }

    public CarH setSemaforos(List<Boolean> semaforos) {
        this.semaforos = semaforos;
        return this;
    }
}
