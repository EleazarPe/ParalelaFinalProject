package com.example.proyectofinal;

import com.example.proyectofinal.logica.Car;
import com.example.proyectofinal.logica.CarH;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;


public class HighwayStreet {

    @FXML
    private MenuItem agregarCarrito;
    @FXML
    private MenuItem agregarAmbulancia;
    @FXML
    private Circle este;
    @FXML
    private Circle oeste;
    @FXML
    private Circle esteamb;
    @FXML
    private Circle oesteamb;
    @FXML
    private Pane root;
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
    private Rectangle carrilNorteTercero;
    @FXML
    private Rectangle carrilNorteSegundo;
    @FXML
    private Rectangle carrilNortePrimero;
    @FXML
    private Rectangle carrilSurPrimero;
    @FXML
    private Rectangle carrilSurSegundo;
    @FXML
    private Rectangle carrilSurTercero;

    private int idcars = 0;
    private String[] direcciones = new String[3];
    private int settingDir = 0;

    private static final String[] CARS = {"redcar", "graycar", "whitecar"};
    private List<CarH> cars = new ArrayList<>();

    @FXML
    public void initialize() {
        agregarCarrito.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                este.setVisible(true);
                oeste.setVisible(true);
                este.toFront();
                oeste.toFront();
            }
        });
        agregarAmbulancia.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                esteamb.setVisible(true);
                oesteamb.setVisible(true);
                esteamb.toFront();
                oesteamb.toFront();

            }
        });
        cercania();
        mover();
        cambioCarril();
    }
    @FXML
    public void handleCircleClick(MouseEvent event) {
        Circle circle = (Circle) event.getSource();
        String circleId = circle.getId();
        System.out.println("Circle pressed: " + circleId);
        addCar(circle.getId(), false);
        direcciones = new String[3];
        settingDir = 0;
        direccion();
        este.setVisible(false);
        oeste.setVisible(false);
    }
    @FXML
    public void handleCircleClickAmb(MouseEvent event) {
        Circle circle = (Circle) event.getSource();
        String circleId = circle.getId();
        System.out.println("Circle pressed: " + circleId);
        if(circle.getId().equals("esteamb")) {
//            addCar("este", true);
//            direccion();
        }else if(circle.getId().equals("oesteamb")){
//            addCar("oeste", true);
//            direccion();
        }
        esteamb.setVisible(false);
        oesteamb.setVisible(false);

    }
    @FXML
    public void handleDirection(MouseEvent event) {
        StackPane stackPane = (StackPane) event.getSource();
        System.out.println("Direction pressed: " + stackPane.getId());
        direcciones[settingDir] = stackPane.getId();
        direccionPane.setVisible(false);
        if (direcciones[settingDir].equals("alantedir") && settingDir<2) {
            direccion();
        }else{
            buscarCarroById(idcars-1).setDestino(direcciones);
            correr(idcars-1);
        }
        settingDir++;
    }

    private void cercania() {
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            temporal();
        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }
    private void temporal(){

        for (int i = 0; i < cars.size(); i++) {
            CarH car1 = cars.get(i);

            for (int j = 0; j < cars.size(); j++) {
                if (i == j) continue;
                CarH car2 = cars.get(j);

                if ((car1.getRectangle().getRotate() == 180 && car2.getRectangle().getRotate() == 180)) {
                    if (car1.getRectangle().getY() > car2.getRectangle().getY() && car1.getRectangle().getY() - car2.getRectangle().getY() < 110) {
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }
                    }

                } else if((car1.getRectangle().getRotate() == 0 && car2.getRectangle().getRotate() == 0)){
                    if (car1.getRectangle().getY() < car2.getRectangle().getY() && car2.getRectangle().getY() - car1.getRectangle().getY()  < 110) {
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }
                    }

                }else if((car1.getRectangle().getRotate() == 90 && car2.getRectangle().getRotate() == 90)){
                    if (car1.getRectangle().getX() > car2.getRectangle().getX() &&  car1.getRectangle().getX() - car2.getRectangle().getX()  < 110) {
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }

                    }

                }else if((car1.getRectangle().getRotate() == 270 && car2.getRectangle().getRotate() == 270)){
                    if (car1.getRectangle().getX() < car2.getRectangle().getX() && car2.getRectangle().getX() - car1.getRectangle().getX()   < 110) {
                        if (car2.getAnimationTimer() != null) {
                            car2.getAnimationTimer().stop();
                            car2.setRunning(false);
                        }
                    }
                }

            }
        }
    }
    private void mover(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(500), event -> {

            for(CarH car: cars){
                if(!car.isRunning()){
                    if(!car.isInCrossing()) {
                        if(car.getAnimationTimer() != null) {
                            if(!car.isPare()) {
                                if(moveChecker(car)) {
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
    private boolean moveChecker(CarH carro){
        for (int i = 0; i < cars.size(); i++) {
            CarH car1 = cars.get(i);
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
    private void addCar(String id, boolean ambulancia) {
        String carImage = CARS[new Random().nextInt(CARS.length)];
        Image image = new Image("file:src/main/resources/com/example/proyectofinal/cars/" + carImage + ".png");
        ImageView carritoImagen = new ImageView(image);
        if(ambulancia){
            carritoImagen = new ImageView(new Image("file:src/main/resources/com/example/proyectofinal/cars/ambulanciaShort.png"));
        }
        carritoImagen.setFitHeight(100);
        carritoImagen.setFitWidth(50);
        Rectangle carrito = new Rectangle(50, 100);
        carrito.setFill(javafx.scene.paint.Color.TRANSPARENT);
        switch (id) {
            case "este" -> {
                carrito.setX(root.getWidth()-100);
                carrito.setY(este.getCenterY() + 275);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(270);
                carritoImagen.setRotate(270);
            }
            case "oeste" -> {
                carrito.setX(50);
                carrito.setY(oeste.getCenterY()+595);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(90);
                carritoImagen.setRotate(90);
            }
        }
        CarH car = new CarH(carritoImagen, carrito, idcars++,ambulancia,id);
        System.out.println(idcars);
        cars.add(car);
        root.getChildren().addAll(carrito, carritoImagen);
//        if(ambulancia){
//            cicloNormal = 1;
//        }

    }
    private void direccion() {
        direccionPane.setVisible(true);
        direccionPane.toFront();
    }
    private void correr(int id){
        CarH car = buscarCarroById(id);
        if(car != null){
//            car.setDestino(idDireccion);
//            if(car.isTipoEmergencia()){
//                System.out.println("======AMBULANCIAAAA");
//                cicloNormal = 1;
//            }
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
                                newX = -100;
                            }
                            car.getRectangle().setX(newX);
                            car.getImageView().setLayoutX(newX);
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
    }
    private CarH buscarCarroById(int id){
        CarH car = null;
        for(CarH c : cars){
            if(c.getId() == id){
                car = c;
                break;
            }
        }
        return car;
    }

    public void cambioCarril(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            for (CarH car : cars) {
                if (car.getRectangle().getBoundsInParent().intersects(carrilNorteTercero.getBoundsInParent()) && !car.getCarril()) {
                    car.setCarril(true);
                    if(!car.getDestino()[2].equals("adelantedir")){
                       System.out.println("Cambio de carril1");
                        crusando(car,2);
                    }
                }else if(car.getRectangle().getBoundsInParent().intersects(carrilNorteSegundo.getBoundsInParent()) && !car.getCarril()) {
                    car.setCarril(true);
                    if(!car.getDestino()[1].equals("adelantedir")){
                        System.out.println("Cambio de carril2");
                        crusando(car, 1);
                    }

                }else if (car.getRectangle().getBoundsInParent().intersects(carrilNortePrimero.getBoundsInParent()) && !car.getCarril() ) {
                    car.setCarril(true);
                    if(!car.getDestino()[0].equals("adelantedir")){
                        System.out.println("Cambio de carril3");
                        crusando(car, 0);
                    }

                }
                if (car.getRectangle().getBoundsInParent().intersects(carrilSurPrimero.getBoundsInParent()) && !car.getCarril()) {
                    car.setCarril(true);
                    if(!car.getDestino()[0].equals("adelantedir")){
                        System.out.println("Cambio de carril1");
                        crusando(car,0);
                    }
                }else if(car.getRectangle().getBoundsInParent().intersects(carrilSurSegundo.getBoundsInParent()) && !car.getCarril()) {
                    car.setCarril(true);
                    if(!car.getDestino()[1].equals("adelantedir")){
                        System.out.println("Cambio de carril2");
                        crusando(car, 1);
                    }

                }else if (car.getRectangle().getBoundsInParent().intersects(carrilSurTercero.getBoundsInParent()) && !car.getCarril() ) {
                    car.setCarril(true);
                    if(!car.getDestino()[2].equals("adelantedir")){
                        System.out.println("Cambio de carril3");
                        crusando(car, 2);
                    }

                }
                if(car.getOrigen().equals("este") && (!car.getRectangle().getBoundsInParent().intersects(carrilNorteTercero.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilNorteSegundo.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilNortePrimero.getBoundsInParent()))){
                    car.setCarril(false);
                }

                if(car.getOrigen().equals("oeste") && (!car.getRectangle().getBoundsInParent().intersects(carrilSurPrimero.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilSurSegundo.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilSurTercero.getBoundsInParent()))){
                    car.setCarril(false);
                }

            }
        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }
    private void crusando(CarH car, int pos ) {

//        for(String s : car.getDestino()) {
//            if(!s.equals("alantedir")){
//
//            }
//        }
        AnimationTimer timer = new AnimationTimer() {
            private boolean movedInX = false;
            private double initialX = car.getRectangle().getX();
            private double initialY = car.getRectangle().getY();
            private double move = 80;

            @Override
            public void handle(long now) {
                switch (car.getOrigen()) {
                    case "este" -> {
                        switch (car.getDestino()[pos]) {
                            case "izquierdadir", "udir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() - 1;
                                    if (initialX - newX >= move) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(180);
                                    car.getImageView().setRotate(180);
                                    double newY = car.getRectangle().getY() + 1;
                                    if ( newY - initialY >= 80) {
                                        double newX = car.getRectangle().getX() - 1;
                                        car.getRectangle().setRotate(270);
                                        car.getImageView().setRotate(270);
                                        car.getRectangle().setX(newX);
                                        car.getImageView().setLayoutX(newX);
                                    }
//                                    if (newY > root.getHeight()) {
//
                                     else {
                                        car.getRectangle().setY(newY);
                                        car.getImageView().setLayoutY(newY);
                                    }
                                }
                            }
                            case "derechadir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() - 1;
                                    if (initialX - newX >= move) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(0);
                                    car.getImageView().setRotate(0);
                                    double newY = car.getRectangle().getY() - 1;
                                    if (initialY -newY >= 80) {
                                        double newX = car.getRectangle().getX() - 1;
                                        car.getRectangle().setRotate(270);
                                        car.getImageView().setRotate(270);
                                        car.getRectangle().setX(newX);
                                        car.getImageView().setLayoutX(newX);
                                    }
//                                    if (newY > root.getHeight()) {
//
                                    else {
                                        car.getRectangle().setY(newY);
                                        car.getImageView().setLayoutY(newY);
                                    }
                                }
                            }
                        }
                    }
                    case "oeste" -> {
                        switch (car.getDestino()[pos]) {
                            case "izquierdadir", "udir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() + 1;
                                    if ( newX - initialX  >= move) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(0);
                                    car.getImageView().setRotate(0);
                                    double newY = car.getRectangle().getY() - 1;
                                    if ( initialY - newY >= 80) {
                                        double newX = car.getRectangle().getX() + 1;
                                        car.getRectangle().setRotate(90);
                                        car.getImageView().setRotate(90);
                                        car.getRectangle().setX(newX);
                                        car.getImageView().setLayoutX(newX);
                                    }
//                                    if (newY > root.getHeight()) {
//
                                    else {
                                        car.getRectangle().setY(newY);
                                        car.getImageView().setLayoutY(newY);
                                    }
                                }
                            }
                            case "derechadir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() + 1;
                                    if (newX - initialX >= move) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(180);
                                    car.getImageView().setRotate(180);
                                    double newY = car.getRectangle().getY() + 1;
                                    if (newY - initialY >= 80) {
                                        double newX = car.getRectangle().getX() + 1;
                                        car.getRectangle().setRotate(90);
                                        car.getImageView().setRotate(90);
                                        car.getRectangle().setX(newX);
                                        car.getImageView().setLayoutX(newX);
                                    }
//                                    if (newY > root.getHeight()) {
//
                                    else {
                                        car.getRectangle().setY(newY);
                                        car.getImageView().setLayoutY(newY);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        };
        if(car.getAnimationTimer() != null && !car.getDestino()[pos].equals("alantedir")){
            car.getAnimationTimer().stop();
            car.setRunning(false);
            car.setAnimationTimer(timer);
            timer.start();
            car.setRunning(true);
        }

    }


}
