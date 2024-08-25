package com.example.proyectofinal.logica;

import javafx.animation.AnimationTimer;

public class OriginalCar {
    private int id;
    private AnimationTimer animationTimer;

    public OriginalCar(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AnimationTimer getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(AnimationTimer animationTimer) {
        this.animationTimer = animationTimer;
    }
}
