package com.example.proyectofinal;

import com.example.proyectofinal.logica.Car;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class NormalStreet {
    @FXML
    private Pane root;
    @FXML
    private Circle este;
    @FXML
    private Circle oeste;
    @FXML
    private Circle norte;
    @FXML
    private Circle sur;
    @FXML
    private Circle esteamb;
    @FXML
    private Circle oesteamb;
    @FXML
    private Circle norteamb;
    @FXML
    private Circle suramb;
    @FXML
    private MenuItem agregarCarrito;
    @FXML
    private MenuItem agregarAmbulancia;
    @FXML
    private Rectangle estePare;
    @FXML
    private Rectangle oestePare;
    @FXML
    private Rectangle nortePare;
    @FXML
    private Rectangle surPare;
    @FXML
    private Pane direccionPane;
    @FXML
    private Rectangle cruce;

    private int idcars = 0;



//    private double carritoX = 0;
//    private double carritoY = 0;
//
//    private double velocityX = 0;
//    private double velocityY = 0;
//
//    private final double acceleration = 0.1;
//    private final double maxSpeed = 5;
//    private final double deceleration = 0.05;
//
//    private double currentAngle = 0;
//
//    private Set<String> pressedKeys = new HashSet<>();
    private static final String[] CARS = {"redcar", "graycar", "whitecar"};
    private List<Car> cars = new ArrayList<>();
    private BlockingDeque<Car> colaInterseccion = new LinkedBlockingDeque<>(100);

    @FXML
    public void initialize() {

        agregarCarrito.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                este.setVisible(true);
                oeste.setVisible(true);
                norte.setVisible(true);
                sur.setVisible(true);
                este.toFront();
                oeste.toFront();
                norte.toFront();
                sur.toFront();
            }
        });
        agregarAmbulancia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                esteamb.setVisible(true);
                oesteamb.setVisible(true);
                norteamb.setVisible(true);
                suramb.setVisible(true);
                esteamb.toFront();
                oesteamb.toFront();
                norteamb.toFront();
                suramb.toFront();
            }
        });
//        AnimationTimer animationTimer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//            }
//        };
//        animationTimer.start();
        //cercania();
        timeline();
        llegada();
        tiempoDeCola();
        cercania2();
        mover();
    }
//    private void cercania(){
//        AnimationTimer animationTimer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                for (int i = 0; i < cars.size(); i++) {
//                    Car car1 = cars.get(i);
//                    double newX = car1.getRectangle().getX() + 2.0;
//                    double newY = car1.getRectangle().getY() + 1.0;
//                    car1.getRectangle().setX(newX);
//                    car1.getRectangle().setY(newY);
//                    car1.getImageView().setLayoutX(newX);
//                    car1.getImageView().setLayoutY(newY);
//
//                    for (int j = 0; j < cars.size(); j++) {
//                        if (i == j) continue;
//
//                        Car car2 = cars.get(j);
//
//                        double centerX1 = car1.getRectangle().getX() + car1.getRectangle().getWidth() / 2;
//                        double centerY1 = car1.getRectangle().getY() + car1.getRectangle().getHeight() / 2;
//
//                        double centerX2 = car2.getRectangle().getX() + car2.getRectangle().getWidth() / 2;
//                        double centerY2 = car2.getRectangle().getY() + car2.getRectangle().getHeight() / 2;
//
//                        double distanceX = centerX2 - centerX1;
//                        double distanceY = centerY2 - centerY1;
//                        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
//
//                        if (distance < 10) {
//                            newX = newX * 0.9;
//                            newY = newY * 0.9;
//                        }
//                        car1.getRectangle().setX(car1.getRectangle().getX() + newX);
//                        car1.getRectangle().setY(car1.getRectangle().getY() + newY);
//                        car1.getImageView().setLayoutX(car1.getRectangle().getX());
//                        car1.getImageView().setLayoutY(car1.getRectangle().getY());
//                    }
//                }
//            }
//        };
//
//        animationTimer.start();
//    }
    private void temporal(){
        for (int i = 0; i < cars.size(); i++) {
            Car car1 = cars.get(i);
            for (int j = 0; j < cars.size(); j++) {
                if (i == j) continue;
                Car car2 = cars.get(j);
                //if (car1.getRectangle().getBoundsInParent().intersects(car2.getRectangle().getBoundsInParent())) {

                if ((car1.getRectangle().getRotate() == 180 && car2.getRectangle().getRotate() == 180)) {
                    //System.out.println("Bajando");
                    if (car1.getRectangle().getY() > car2.getRectangle().getY() && car1.getRectangle().getY() - car2.getRectangle().getY() < 110) {
//                                if(!car1.isPare()){
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }
                        //}

                    } //else {
//                                if (car1.getAnimationTimer() != null) {
//                                      car1.getAnimationTimer().stop();
//                                      car1.setRunning(false);
//                                }
//                            }
                } else if((car1.getRectangle().getRotate() == 0 && car2.getRectangle().getRotate() == 0)){
                    if (car1.getRectangle().getY() < car2.getRectangle().getY() && car2.getRectangle().getY() - car1.getRectangle().getY()  < 110) {
//                                if(!car1.isPare()){
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }
                        //}

                    }
                }else if((car1.getRectangle().getRotate() == 90 && car2.getRectangle().getRotate() == 90)){
                    if (car1.getRectangle().getX() > car2.getRectangle().getX() &&  car1.getRectangle().getX() - car2.getRectangle().getX()  < 110) {
//                                if(!car1.isPare()){
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }
                        //}

                    }

                }else if((car1.getRectangle().getRotate() == 270 && car2.getRectangle().getRotate() == 270)){
                    if (car1.getRectangle().getX() < car2.getRectangle().getX() && car2.getRectangle().getX() - car1.getRectangle().getX()   < 110) {
//                                if(!car1.isPare()){
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }
                        //}

                    }
                }
                //}
            }
        }
    }
    private boolean temporal2(Car carro){
        for (int i = 0; i < cars.size(); i++) {
            Car car1 = cars.get(i);
            if (car1.equals(carro)) {
                continue;
            }
            if ((car1.getRectangle().getRotate() == 180 && carro.getRectangle().getRotate() == 180)) {
                if (car1.getRectangle().getY() > carro.getRectangle().getY() && car1.getRectangle().getY() - carro.getRectangle().getY() < 110) {
                    return false;
                }
            } else if((car1.getRectangle().getRotate() == 0 && carro.getRectangle().getRotate() == 0)) {
                if (car1.getRectangle().getY() < carro.getRectangle().getY() && carro.getRectangle().getY() - car1.getRectangle().getY() < 110) {
                    return false;
                }
            }else if((car1.getRectangle().getRotate() == 90 && carro.getRectangle().getRotate() == 90)) {
                if (car1.getRectangle().getX() > carro.getRectangle().getX() && car1.getRectangle().getX() - carro.getRectangle().getX() < 110) {
                    return false;
                }
            }else if((car1.getRectangle().getRotate() == 270 && carro.getRectangle().getRotate() == 270)) {
                if (car1.getRectangle().getX() < carro.getRectangle().getX() && carro.getRectangle().getX() - car1.getRectangle().getX() < 110) {
                    return false;
                }
            }
        }
        return true;
    }

    private void cercania2() {
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            temporal();
        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }
    private void mover(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(500), event -> {

            for(Car car: cars){
                if(!car.isRunning()){
                    if(!car.isInCrossing()) {
                        if(car.getAnimationTimer() != null) {
                            if(!car.isPare()) {
                                if(temporal2(car)) {
                                    car.getAnimationTimer().start();
                                    car.setRunning(true);
                                }
                            }
                        }
                    }
                }

            }
        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }
//    private void mover(){
//        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
//
//            for(Car car: cars){
//                    if(!car.isRunning()){
//                        if(!car.isInCrossing()) {
//                            if(car.getAnimationTimer() != null) {
//                                if(car.isPare()) {
//                                    int i = 0;
//                                    for (Car c : cars) {
//                                        if (c.isInCrossing()) {
//                                            i++;
//                                        }
//                                    }
//                                    if (i == 0) {
//                                        car.getAnimationTimer().start();
//                                        car.setRunning(true);
//                                    }
//                                }else{
//                                    car.getAnimationTimer().start();
//                                    car.setRunning(true);
//                                }
//                            }
//                        }
//                    }
//
//            }
//        }));
//        collisionChecker.setCycleCount(Timeline.INDEFINITE);
//        collisionChecker.play();
//    }
//    private int conseguirPosicion(Car car){
//        int posicion =0;
//        for(Car carBlockingDeque: colaInterseccion){
//            if (car.equals(carBlockingDeque)) {
//                return posicion;
//            }
//            posicion++;
//        }
//        return 0;
//    }

    @FXML
    public void handleCircleClick(MouseEvent event) {
        Circle circle = (Circle) event.getSource();
        String circleId = circle.getId();
        System.out.println("Circle pressed: " + circleId);
        addCar(circle.getId());
        direccion();
        este.setVisible(false);
        oeste.setVisible(false);
        norte.setVisible(false);
        sur.setVisible(false);
    }

    @FXML
    public void handleCircleClickAmb(MouseEvent event) {
        Circle circle = (Circle) event.getSource();
        String circleId = circle.getId();
        System.out.println("Circle pressed: " + circleId);
        esteamb.setVisible(false);
        oesteamb.setVisible(false);
        norteamb.setVisible(false);
        suramb.setVisible(false);
    }
    @FXML
    public void handleDirection(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        System.out.println("Direction pressed: " + stackPane.getId());
        direccionPane.setVisible(false);
        correr(idcars-1,stackPane.getId());

    }
    private void direccion() {
        direccionPane.setVisible(true);
        direccionPane.toFront();
    }
    private void correr(int id, String idDireccion){
        Car car = buscarCarroById(id);
        if(car != null){
            car.setDestino(idDireccion);
        }
        AnimationTimer timer = null;
        switch (Objects.requireNonNull(car).getOrigen()){
            case "este" ->{
                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        double newX = car.getRectangle().getX() - 1;
                        if (newX < 0) {
                            newX = root.getWidth();
                        }
                        car.getRectangle().setX(newX);
                        car.getImageView().setLayoutX(newX);
                    }
                };
                timer.start();
            }
            case "oeste" -> {
                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        double newX = car.getRectangle().getX() + 1;
                        if (newX > root.getWidth()) {
                            newX = -0;
                        }
                        car.getRectangle().setX(newX);
                        car.getImageView().setLayoutX(newX);
                    }
                };
                timer.start();
            }
            case "norte" -> {
                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        double newX = car.getRectangle().getY() + 1;
                        if (newX > root.getHeight()) {
                            newX = -50;
                        }
                        car.getRectangle().setY(newX);
                        car.getImageView().setLayoutY(newX);
                    }
                };
                timer.start();
            }
            case "sur" -> {
                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        double newX = car.getRectangle().getY() - 1;
                        if (newX < 0) {
                            newX = root.getHeight();
                        }
                        car.getRectangle().setY(newX);
                        car.getImageView().setLayoutY(newX);
                    }
                };
                timer.start();
            }
        }
        if(timer != null){
            car.setAnimationTimer(timer);
            car.setRunning(true);
        }
    }


    private void timeline(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            for (Car car : cars) {
                if (car.getRectangle().getBoundsInParent().intersects(cruce.getBoundsInParent())) {
                    if (!car.isInCrossing()) {
                        System.out.println("Llego al cruce id: " + car.getId());
                        car.setInCrossing(true);
                        crusando(car);
                    }
                } else {
                    if (car.isInCrossing()) {
                        car.setInCrossing(false);
                        System.out.println("Salió del cruce id: " + car.getId());
                        //sacarDeCola();
                    }
                }
            }

        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }
    private void sacarDeCola(){
        if (!colaInterseccion.isEmpty()) {
            Car car = colaInterseccion.poll();
            car.getAnimationTimer().start();
            car.setRunning(true);
//            PauseTransition pause = new PauseTransition(Duration.seconds(1));
//            pause.setOnFinished(event -> car.getAnimationTimer().start());
//            pause.play();
        }
    }
    private void tiempoDeCola() {
            Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(500), event -> {
                int crossing= 0;
                for (Car c :cars) {
                    if(c.isInCrossing()){
                        crossing++;
                    }
                }
                if(crossing == 0){
                    sacarDeCola();
                }
            }));
            collisionChecker.setCycleCount(Timeline.INDEFINITE);
            collisionChecker.play();
    }
//    private void observador(){
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        AtomicLong lastChangeTime = new AtomicLong(System.currentTimeMillis());
//        //scheduler.scheduleAtFixedRate(Objects.requireNonNull(checkQueue(lastChangeTime)), 0, 1, TimeUnit.SECONDS);
//    }
//    private void monitorQueue( long idleTimeMillis) {
//        long startTime = System.currentTimeMillis();
//
//        while (true) {
//
//            if (colaInterseccion.isEmpty()) {
//                long currentTime = System.currentTimeMillis();
//                if (currentTime - startTime >= idleTimeMillis) {
//                    System.out.println("Sacando de cola");
//                    sacarDeCola();
//                }
//            } else {
//                startTime = System.currentTimeMillis();
//            }
//        }
//    }
//    private Runnable checkQueue(AtomicLong lastChangeTime) {
//        long currentTime = System.currentTimeMillis();
//        if (currentTime - lastChangeTime.get() >= 2000) {
//            sacarDeCola();
//        }
//        return null;
//    }
    private void llegada(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            for (Car car : cars) {
                if(car.getRectangle().getBoundsInParent().intersects(estePare.getBoundsInParent())){
                    if (!car.isPare()) {
                        System.out.println("Llego al stop Este id: " + car.getId());
                        car.setPare(true);
                        car.getAnimationTimer().stop();
                        car.setRunning(false);
                        colaInterseccion.add(car);
                    }
                    //car.getAnimationTimer().stop();
                }else if (car.getRectangle().getBoundsInParent().intersects(oestePare.getBoundsInParent())){
                    if (!car.isPare()) {
                        System.out.println("Llego al stop Oeste id: " + car.getId());
                        car.setPare(true);
                        car.getAnimationTimer().stop();
                        car.setRunning(false);
                        colaInterseccion.add(car);
                    }
                }else if (car.getRectangle().getBoundsInParent().intersects(nortePare.getBoundsInParent())){
                    if (!car.isPare()) {
                        System.out.println("Llego al stop Norte id: " + car.getId());
                        car.setPare(true);
                        car.getAnimationTimer().stop();
                        car.setRunning(false);
                        colaInterseccion.add(car);
                    }
                }else if (car.getRectangle().getBoundsInParent().intersects(surPare.getBoundsInParent())) {
                    if (!car.isPare()) {
                        System.out.println("Llego al stop Sur id: " + car.getId());
                        car.setPare(true);
                        car.getAnimationTimer().stop();
                        car.setRunning(false);
                        colaInterseccion.add(car);
                    }
                }else{
                    if (car.isPare()) {
                        car.setPare(false);
                    }
                }
            }

        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }


private void crusando(Car car) {
    AnimationTimer timer = new AnimationTimer() {
        private boolean movedInX = false;
        private boolean madeUTurn = false;
        private double initialX = car.getRectangle().getX();
        private double initialY = car.getRectangle().getY();
        private double moveDistanceDerecha = 115;  // distancia en píxeles a mover en X
        private double moveDistanceIzquierda = 190;
        private double curveRadius = 80;

        @Override
        public void handle(long now) {
            switch (car.getOrigen()) {
                case "este" -> {
                    switch (car.getDestino()) {
                        case "izquierdadir" -> {
                            if (!movedInX) {
                                double newX = car.getRectangle().getX() - 1;
                                if (initialX - newX >= moveDistanceIzquierda) {
                                    movedInX = true;
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else {
                                car.getRectangle().setRotate(180);
                                car.getImageView().setRotate(180);
                                double newY = car.getRectangle().getY() + 1;
                                if (newY > root.getHeight()) {
                                    resetCarPositionEste(car);
                                } else {
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                        }
                        case "alantedir" -> {
                            double newX = car.getRectangle().getX() - 1;
                            if (newX < 0) {
                                resetCarPositionEste(car);
                            } else {
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            }
                        }
                        case "udir" -> {
                            if (!movedInX) {
                                double newX = car.getRectangle().getX() - 1;
                                if (initialX - newX >= moveDistanceDerecha) {
                                    movedInX = true;
                                    car.getRectangle().setRotate(180);
                                    car.getImageView().setRotate(180);
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else if (!madeUTurn) {
                                double newY = car.getRectangle().getY() + 1;
                                if (newY - initialY >= curveRadius) {
                                    madeUTurn = true;
                                    car.getRectangle().setRotate(90);  // Gira el carro hacia adelante
                                    car.getImageView().setRotate(90);
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else {
                                double newX = car.getRectangle().getX() + 1;
                                if (newX > root.getWidth()) {
                                    resetCarPositionEste(car);
                                } else {
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                }
                            }
                        }
                        case "derechadir" -> {
                            if (!movedInX) {
                                double newX = car.getRectangle().getX() - 1;
                                if (initialX - newX >= moveDistanceDerecha) {
                                    movedInX = true;  // Movimiento en X completado
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else {
                                car.getRectangle().setRotate(0);
                                car.getImageView().setRotate(0);
                                double newY = car.getRectangle().getY() - 1;
                                if (newY < 0) {
                                    resetCarPositionEste(car);
                                } else {
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                        }
                    }
                }
                case "oeste" -> {
                    switch (car.getDestino()) {
                        case "izquierdadir" -> {
                            if (!movedInX) {
                                double newX = car.getRectangle().getX() + 1;
                                if (newX - initialX >= moveDistanceIzquierda) {
                                    movedInX = true;
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else {
                                car.getRectangle().setRotate(0);
                                car.getImageView().setRotate(0);
                                double newY = car.getRectangle().getY() - 1;
                                if (newY < 0) {
                                    resetCarPositionOeste(car);
                                } else {
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                        }
                        case "alantedir" -> {
                            double newX = car.getRectangle().getX() + 1;
                            if (newX > root.getWidth() + 50) {
                                resetCarPositionOeste(car);
                            } else {
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            }
                        }
                        case "udir" -> {
                            if (!movedInX) {
                                double newX = car.getRectangle().getX() + 1;
                                if (newX - initialX >= moveDistanceDerecha) {
                                    movedInX = true;
                                    car.getRectangle().setRotate(0);
                                    car.getImageView().setRotate(0);
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else if (!madeUTurn) {
                                double newY = car.getRectangle().getY() - 1;
                                if (initialY - newY >= curveRadius) {
                                    madeUTurn = true;
                                    car.getRectangle().setRotate(270);
                                    car.getImageView().setRotate(270);
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else {
                                double newX = car.getRectangle().getX() - 1;
                                if (newX < -50) {
                                    resetCarPositionOeste(car);
                                } else {
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                }
                            }
                        }
                        case "derechadir" -> {
                            if (!movedInX) {
                                double newX = car.getRectangle().getX() + 1;
                                if (newX - initialX >= moveDistanceDerecha) {
                                    movedInX = true;  // Movimiento en X completado
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else {
                                car.getRectangle().setRotate(180);
                                car.getImageView().setRotate(180);
                                double newY = car.getRectangle().getY() + 1;
                                if (newY > root.getHeight()) {
                                    resetCarPositionOeste(car);
                                } else {
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                        }
                    }
                }
                case "norte" -> {
                    switch (car.getDestino()) {
                        case "izquierdadir" -> {
                            if (!movedInX) {
                                double newY = car.getRectangle().getY() + 1;
                                if (newY - initialY >= moveDistanceIzquierda) {
                                    movedInX = true;
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else {
                                car.getRectangle().setRotate(90);
                                car.getImageView().setRotate(90);
                                double newX = car.getRectangle().getX() + 1;
                                if (newX > root.getWidth()) {
                                    resetCarPositionNorte(car);
                                } else {
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                }
                            }
                        }
                        case "alantedir" -> {
                            double newY = car.getRectangle().getY() + 1;
                            if (newY > root.getHeight()) {
                                resetCarPositionNorte(car);
                            } else {
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            }
                        }
                        case "udir" -> {
                            if (!movedInX) {
                                double newY = car.getRectangle().getY() + 1;
                                if (newY - initialY >= moveDistanceDerecha) {
                                    movedInX = true;
                                    car.getRectangle().setRotate(90);
                                    car.getImageView().setRotate(90);
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else if (!madeUTurn) {
                                double newX = car.getRectangle().getX() + 1;
                                if (newX - initialX >= curveRadius) {
                                    madeUTurn = true;
                                    car.getRectangle().setRotate(0);
                                    car.getImageView().setRotate(0);
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else {
                                double newY = car.getRectangle().getY() - 1;
                                if (newY < -50) {
                                    resetCarPositionNorte(car);
                                } else {
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                        }
                        case "derechadir" -> {
                            if (!movedInX) {
                                double newY = car.getRectangle().getY() + 1;
                                if (newY - initialY >= moveDistanceDerecha) {
                                    movedInX = true;
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else {
                                car.getRectangle().setRotate(270);
                                car.getImageView().setRotate(270);
                                double newX = car.getRectangle().getX() - 1;
                                if (newX < 0) {
                                    resetCarPositionNorte(car);
                                } else {
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                }
                            }
                        }
                    }
                }
                case "sur" -> {
                    switch (car.getDestino()) {
                        case "izquierdadir" -> {
                            if (!movedInX) {
                                double newY = car.getRectangle().getY() - 1;
                                if (initialY - newY >= moveDistanceIzquierda) {
                                    movedInX = true;
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else {
                                car.getRectangle().setRotate(270);
                                car.getImageView().setRotate(270);
                                double newX = car.getRectangle().getX() - 1;
                                if (newX < 0) {
                                    resetCarPositionSur(car);
                                } else {
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                }
                            }
                        }
                        case "alantedir" -> {
                            double newY = car.getRectangle().getY() - 1;
                            if (newY < 0) {
                                resetCarPositionSur(car);
                            } else {
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            }
                        }
                        case "udir" -> {
                            if (!movedInX) {
                                double newY = car.getRectangle().getY() - 1;
                                if (initialY - newY >= moveDistanceDerecha) {
                                    movedInX = true;
                                    car.getRectangle().setRotate(270);
                                    car.getImageView().setRotate(270);
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else if (!madeUTurn) {
                                double newX = car.getRectangle().getX() - 1;
                                if (initialX - newX >= curveRadius) {
                                    madeUTurn = true;
                                    car.getRectangle().setRotate(180);
                                    car.getImageView().setRotate(180);
                                }
                                car.getRectangle().setX(newX);
                                car.getImageView().setLayoutX(newX);
                            } else {
                                double newY = car.getRectangle().getY() + 1;
                                if (newY > root.getHeight()) {
                                    resetCarPositionSur(car);
                                } else {
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                        }
                        case "derechadir" -> {
                            if (!movedInX) {
                                double newY = car.getRectangle().getY() - 1;
                                if (initialY - newY >= moveDistanceDerecha) {
                                    movedInX = true;
                                }
                                car.getRectangle().setY(newY);
                                car.getImageView().setLayoutY(newY);
                            } else {
                                car.getRectangle().setRotate(90);
                                car.getImageView().setRotate(90);
                                double newX = car.getRectangle().getX() + 1;
                                if (newX > root.getWidth()) {
                                    resetCarPositionSur(car);
                                } else {
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                }
                            }
                        }
                    }
                }
            }
        }
    };
    car.getAnimationTimer().stop();
    car.setRunning(false);
    car.setAnimationTimer(timer);
    timer.start();
    car.setRunning(true);
}


    private void resetCarPositionEste(Car car) {
    car.getRectangle().setX(root.getWidth() );
    car.getRectangle().setY(este.getCenterY() + 370);
    car.getImageView().setLayoutX(car.getRectangle().getX());
    car.getImageView().setLayoutY(car.getRectangle().getY());
    car.getRectangle().setRotate(270);
    car.getImageView().setRotate(270);
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            double newX = car.getRectangle().getX() - 1;
            if (newX < 0) {
                newX = root.getWidth();
            }
            car.getRectangle().setX(newX);
            car.getImageView().setLayoutX(newX);
        }
    };
    car.getAnimationTimer().stop();
    car.setRunning(false);
    car.setAnimationTimer(timer);
    timer.start();
    car.setRunning(true);
}
private void resetCarPositionOeste(Car car) {
    car.getRectangle().setX(-50);
    car.getRectangle().setY(oeste.getCenterY()+450);
    car.getImageView().setLayoutX( car.getRectangle().getX());
    car.getImageView().setLayoutY( car.getRectangle().getY());
    car.getRectangle().setRotate(90);
    car.getImageView().setRotate(90);
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            double newX = car.getRectangle().getX() + 1;
            if (newX > root.getWidth()) {
                newX = -0;
            }
            car.getRectangle().setX(newX);
            car.getImageView().setLayoutX(newX);
        }
    };
    car.getAnimationTimer().stop();
    car.setRunning(false);
    car.setAnimationTimer(timer);
    timer.start();
    car.setRunning(true);
}
    private void resetCarPositionNorte(Car car) {

        car.getRectangle().setX(norte.getCenterX() +590);
        car.getRectangle().setY(-50);
        car.getImageView().setLayoutX(car.getRectangle().getX());
        car.getImageView().setLayoutY(car.getRectangle().getY());
        car.getRectangle().setRotate(180);
        car.getImageView().setRotate(180);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double newX = car.getRectangle().getY() + 1;
                if (newX > root.getHeight()) {
                    newX = -50;
                }
                car.getRectangle().setY(newX);
                car.getImageView().setLayoutY(newX);
            }
        };
        car.getAnimationTimer().stop();
        car.setRunning(false);
        car.setAnimationTimer(timer);
        timer.start();
        car.setRunning(true);

    }
    private void resetCarPositionSur(Car car) {
        car.getRectangle().setX(sur.getCenterX() +670);
        car.getRectangle().setY(root.getHeight());
        car.getImageView().setLayoutX(car.getRectangle().getX());
        car.getImageView().setLayoutY(car.getRectangle().getY());
        car.getRectangle().setRotate(0);
        car.getImageView().setRotate(0);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double newX = car.getRectangle().getY() - 1;
                if (newX < 0) {
                    newX = root.getHeight();
                }
                car.getRectangle().setY(newX);
                car.getImageView().setLayoutY(newX);
            }
        };
        car.getAnimationTimer().stop();
        car.setRunning(false);
        car.setAnimationTimer(timer);
        timer.start();
        car.setRunning(true);

    }




    private void addCar(String id) {
        String carImage = CARS[new Random().nextInt(CARS.length)];
        Image image = new Image("file:src/main/resources/com/example/proyectofinal/cars/" + carImage + ".png");
        ImageView carritoImagen = new ImageView(image);
        carritoImagen.setFitHeight(100);
        carritoImagen.setFitWidth(50);
        Rectangle carrito = new Rectangle(50, 100);
        carrito.setFill(javafx.scene.paint.Color.TRANSPARENT);
        switch (id) {
            case "este" -> {
                carrito.setX(root.getWidth()-100);
                carrito.setY(este.getCenterY() + 370);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(270);
                carritoImagen.setRotate(270);
            }
            case "oeste" -> {
                carrito.setX(50);
                carrito.setY(oeste.getCenterY()+450);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(90);
                carritoImagen.setRotate(90);
            }
            case "norte" -> {
                carrito.setX(norte.getCenterX() +590);
                carrito.setY(25);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(180);
                carritoImagen.setRotate(180);
            }
            case "sur" -> {
                carrito.setX(sur.getCenterX() +670);
                carrito.setY(root.getHeight() - 110);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(0);
                carritoImagen.setRotate(0);
            }
        }
        Car car = new Car(carritoImagen, carrito, idcars++,false,id);
        System.out.println(idcars);
        cars.add(car);
        root.getChildren().addAll(carrito, carritoImagen);

    }
    //        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                double newX = cars.get(0).getRectangle().getY() + 2;
//                if (newX > root.getHeight()) {
//                    newX = -50;
//                }
//                cars.get(0).getRectangle().setY(newX);
//                cars.get(0).getImageView().setLayoutY(newX);
//            }
//        };
//        timer.start();
    private Car buscarCarroById(int id){
        Car car = null;
        for(Car c : cars){
            if(c.getId() == id){
                car = c;
                break;
            }
        }
        return car;
    }
}