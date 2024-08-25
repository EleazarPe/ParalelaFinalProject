package com.example.proyectofinal;

import com.example.proyectofinal.logica.Car;
import com.example.proyectofinal.logica.CarH;
import com.example.proyectofinal.logica.OriginalCar;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.*;

public class HighwayStreet {

    private static final Color COLOR_ROJO = Color.web("0xa60017");
    private static final Color COLOR_AMARILLO = Color.web("0xdcb600");
    private static final Color COLOR_VERDE = Color.web("green");
    private static final Color COLOR_OFF = Color.web("0x373638");

    private static final int SEMAFORO_NORTE_DERECHA = 1;
    private static final int SEMAFORO_SUR_DERECHA = 2;
    private static final int SEMAFORO_NORTE_CENTRO = 3;
    private static final int SEMAFORO_SUR_CENTRO = 4;
    private static final int SEMAFORO_NORTE_IZQUIERDA = 5;
    private static final int SEMAFORO_SUR_IZQUIERDA = 6;

    private static final int SEMAFORO_TIMER_MILLI = 10000;
    private static final int SEMAFORO_AMARILLO_TIMER_MILLI = 3000;


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
    private Rectangle nortePare1;
    @FXML
    private Rectangle nortePare2;
    @FXML
    private Rectangle nortePare3;
    @FXML
    private Rectangle surPare1;
    @FXML
    private Rectangle surPare2;
    @FXML
    private Rectangle surPare3;
    @FXML
    private Rectangle cruceCentro;
    @FXML
    private Rectangle cruceDerecha;
    @FXML
    private Rectangle cruceIzquiera;

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
    @FXML
    private Circle semaforoLuzR1;
    @FXML
    private Circle semaforoLuzA1;
    @FXML
    private Circle semaforoLuzV1;
    @FXML
    private Circle semaforoLuzR2;
    @FXML
    private Circle semaforoLuzA2;
    @FXML
    private Circle semaforoLuzV2;
    @FXML
    private Circle semaforoLuzR3;
    @FXML
    private Circle semaforoLuzA3;
    @FXML
    private Circle semaforoLuzV3;
    @FXML
    private Circle semaforoLuzR4;
    @FXML
    private Circle semaforoLuzA4;
    @FXML
    private Circle semaforoLuzV4;
    @FXML
    private Circle semaforoLuzR5;
    @FXML
    private Circle semaforoLuzA5;
    @FXML
    private Circle semaforoLuzV5;
    @FXML
    private Circle semaforoLuzR6;
    @FXML
    private Circle semaforoLuzA6;
    @FXML
    private Circle semaforoLuzV6;
    List<Circle> semaforoLuzR = new ArrayList<>();
    List<Circle> semaforoLuzA = new ArrayList<>();
    List<Circle> semaforoLuzV = new ArrayList<>();




    private int idcars = 0;
    private String[] direcciones = new String[3];
    private int settingDir = 0;

    private static final String[] CARS = {"redcar", "graycar", "whitecar"};
    private List<CarH> cars = new ArrayList<>();
    private List<OriginalCar> carstemp = new ArrayList<>();
    @FXML
    public void initialize() {

        semaforoLuzR = Arrays.asList(semaforoLuzR1, semaforoLuzR2, semaforoLuzR3, semaforoLuzR4, semaforoLuzR5, semaforoLuzR6);
        semaforoLuzA = Arrays.asList(semaforoLuzA1, semaforoLuzA2, semaforoLuzA3, semaforoLuzA4, semaforoLuzA5, semaforoLuzA6);
        semaforoLuzV = Arrays.asList(semaforoLuzV1, semaforoLuzV2, semaforoLuzV3, semaforoLuzV4, semaforoLuzV5, semaforoLuzV6);

        setInitialSemaforoColor();

        startSemaforos();

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
        llegaAlcruceRojo();
        resetAllCars();
        pararSemaforo();
//        cruceStop();
    }

    private void resetAllCars() {
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            for (CarH c: cars) {
                if(c.getRectangle().getX() < -100 || c.getRectangle().getX() > root.getWidth()+100 || c.getRectangle().getY() < -100 || c.getRectangle().getY() > root.getHeight()+100){
                    for (OriginalCar cc: carstemp) {
                        if(cc.getId() == c.getId()){
                            System.out.println("Salio de pantalla");
                            if(c.getOrigen().equals("este")) {
                                c.getRectangle().setX(root.getWidth()+100);
                                c.getRectangle().setY(este.getCenterY() + 275);
                                c.getImageView().setLayoutX(c.getRectangle().getX());
                                c.getImageView().setLayoutY(c.getRectangle().getY());
                                c.getRectangle().setRotate(270);
                                c.getImageView().setRotate(270);
                            }
                            if(c.getOrigen().equals("oeste")) {
                                c.getRectangle().setX(-100);
                                c.getRectangle().setY(oeste.getCenterY()+595);
                                c.getImageView().setLayoutX(c.getRectangle().getX());
                                c.getImageView().setLayoutY(c.getRectangle().getY());
                                c.getRectangle().setRotate(90);
                                c.getImageView().setRotate(90);
                            }
                            c.getAnimationTimer().stop();
                            c.setAnimationTimer(cc.getAnimationTimer());
                            c.getAnimationTimer().start();
                        }
                    }

                }
            }

        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
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
    private void temporal() {
        for (int i = 0; i < cars.size(); i++) {
            CarH car1 = cars.get(i);

            for (int j = 0; j < cars.size(); j++) {
                if (i == j) continue;
                CarH car2 = cars.get(j);

                double distanciaEuclidiana = Math.sqrt(Math.pow(car1.getRectangle().getX() - car2.getRectangle().getX(), 2) +
                        Math.pow(car1.getRectangle().getY() - car2.getRectangle().getY(), 2));

                if (distanciaEuclidiana < 110) {
                    if ((car1.getRectangle().getRotate() == 180 && car2.getRectangle().getRotate() == 180)) {
                        if ((car1.getRectangle().getY() > car2.getRectangle().getY() || car1.getRectangle().getY() == car2.getRectangle().getY()) && car1.getRectangle().getY() - car2.getRectangle().getY() < 110) {
                            if (car2.getAnimationTimer() != null) {
                                car2.getAnimationTimer().stop();
                                car2.setRunning(false);
                            }
                        }

                    } else if ((car1.getRectangle().getRotate() == 0 && car2.getRectangle().getRotate() == 0)) {
                        if ((car1.getRectangle().getY() < car2.getRectangle().getY() || car1.getRectangle().getY() == car2.getRectangle().getY()) && car2.getRectangle().getY() - car1.getRectangle().getY() < 110) {
                            if (car2.getAnimationTimer() != null) {
                                car2.getAnimationTimer().stop();
                                car2.setRunning(false);
                            }
                        }

                    } else if ((car1.getRectangle().getRotate() == 90 && car2.getRectangle().getRotate() == 90)) {
                        if ((car1.getRectangle().getX() > car2.getRectangle().getX() || car1.getRectangle().getX() == car2.getRectangle().getX()) && car1.getRectangle().getX() - car2.getRectangle().getX() < 110) {
                            if (car2.getAnimationTimer() != null) {
                                car2.getAnimationTimer().stop();
                                car2.setRunning(false);
                            }

                        }
                    } else if ((car1.getRectangle().getRotate() == 270 && car2.getRectangle().getRotate() == 270)) {
                        if ((car1.getRectangle().getX() < car2.getRectangle().getX() || car1.getRectangle().getX() == car2.getRectangle().getX()) && car2.getRectangle().getX() - car1.getRectangle().getX() < 110) {
                            if (car2.getAnimationTimer() != null) {
                                car2.getAnimationTimer().stop();
                                car2.setRunning(false);
                            }
                        }
                    }
                }
            }
        }
    }

    private void mover(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {

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
        CarH car = new CarH(carritoImagen, carrito, idcars,ambulancia,id);
        OriginalCar oc = new OriginalCar(idcars);
        idcars++;
        cars.add(car);
        carstemp.add(oc);
        root.getChildren().addAll(carrito, carritoImagen);
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
//                            if (newX < 0) {
//                                newX = root.getWidth();
//                            }
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
//                            if (newX > root.getWidth()) {
//                                newX = -100;
//                            }
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
                for (OriginalCar c: carstemp) {
                    if(c.getId() == car.getId()){
                        c.setAnimationTimer(timer);
                    }
                }
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
                if (car.getRectangle().getBoundsInParent().intersects(carrilNorteTercero.getBoundsInParent()) && !car.getCarril() && car.getOrigen().equals("este")){
                    car.setCarril(true);
                    if(!car.getDestino()[2].equals("adelantedir")){
                       System.out.println("Cambio de carril1");
                        cambioCarrilAction(car,2);
                    }
                }else if(car.getRectangle().getBoundsInParent().intersects(carrilNorteSegundo.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("este")) {
                    car.setCarril(true);
                    if(!car.getDestino()[1].equals("adelantedir")){
                        System.out.println("Cambio de carril2");
                        cambioCarrilAction(car, 1);
                    }

                }else if (car.getRectangle().getBoundsInParent().intersects(carrilNortePrimero.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("este")) {
                    car.setCarril(true);
                    if(!car.getDestino()[0].equals("adelantedir")){
                        System.out.println("Cambio de carril3");
                        cambioCarrilAction(car, 0);
                    }

                }
                if (car.getRectangle().getBoundsInParent().intersects(carrilSurPrimero.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("oeste")) {
                    car.setCarril(true);
                    if(!car.getDestino()[0].equals("adelantedir")){
                        System.out.println("Cambio de carril1");
                        cambioCarrilAction(car,0);
                    }
                }else if(car.getRectangle().getBoundsInParent().intersects(carrilSurSegundo.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("oeste")) {
                    car.setCarril(true);
                    if(!car.getDestino()[1].equals("adelantedir")){
                        System.out.println("Cambio de carril2");
                        cambioCarrilAction(car, 1);
                    }

                }else if (car.getRectangle().getBoundsInParent().intersects(carrilSurTercero.getBoundsInParent()) && !car.getCarril() && !car.getCarril() && car.getOrigen().equals("oeste")) {
                    car.setCarril(true);
                    if(!car.getDestino()[2].equals("adelantedir")){
                        System.out.println("Cambio de carril3");
                        cambioCarrilAction(car, 2);
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
    private void cambioCarrilAction(CarH car, int pos ) {

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


    public void llegaAlcruceRojo() {
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            for (CarH car : cars) {
                if(car.getOrigen().equals("este")) {
                    if (car.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[0].equals("adelantedir")) {
                            System.out.println("En el cruce 1");
                            cruzando(car, 0);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[1].equals("adelantedir")) {
                            System.out.println("En el cruce 2");
                            cruzando(car, 1);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[2].equals("adelantedir")) {
                            System.out.println("En el cruce 3");
                            cruzando(car, 2);
                        }
                    } else if (!car.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent()) &&
                            !car.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent()) &&
                            !car.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent())) {
                        if (car.isInCrossing()) {
                            System.out.println("Sale del cruce");
                        }
                        car.setInCrossing(false);
                    }
                }

                if(car.getOrigen().equals("oeste")) {
                    if (car.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[0].equals("adelantedir")) {
                            System.out.println("En el cruce 1");
                            cruzando(car, 0);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[1].equals("adelantedir")) {
                            System.out.println("En el cruce 2");
                            cruzando(car, 1);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[2].equals("adelantedir")) {
                            System.out.println("En el cruce 3");
                            cruzando(car, 2);
                        }
                    } else if (!car.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent()) &&
                            !car.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent()) &&
                            !car.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent())) {
                        if (car.isInCrossing()) {
                            System.out.println("Sale del cruce");
                        }
                        car.setInCrossing(false);
                    }
                }
            }
        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }

    private void cruzando(CarH car, int pos ) {

        AnimationTimer timer = new AnimationTimer() {
            private boolean movedInX = false;
            private double initialX = car.getRectangle().getX();
            private double initialY = car.getRectangle().getY();
            private double moveizquierda = 135;
            private double movederecha = 80;

            @Override
            public void handle(long now) {
                switch (car.getOrigen()) {
                    case "este" -> {
                        switch (car.getDestino()[pos]) {
                            case "izquierdadir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() - 1;
                                    if (initialX - newX >= moveizquierda) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(180);
                                    car.getImageView().setRotate(180);
                                    double newY = car.getRectangle().getY() + 1;
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                            case "udir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() - 1;
                                    if (initialX - newX >= moveizquierda) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    double newY = car.getRectangle().getY() + 1;
                                    if(newY - initialY <=160) {
                                        car.getRectangle().setRotate(180);
                                        car.getImageView().setRotate(180);
                                        car.getRectangle().setY(newY);
                                        car.getImageView().setLayoutY(newY);
                                    }else{
                                        double newX = car.getRectangle().getX() + 1;
                                        car.getRectangle().setRotate(90);
                                        car.getImageView().setRotate(90);
                                        car.getRectangle().setX(newX);
                                        car.getImageView().setLayoutX(newX);
                                    }
                                }

                            }
                            case "derechadir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() - 1;
                                    if (initialX - newX >= movederecha) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(0);
                                    car.getImageView().setRotate(0);
                                    double newY = car.getRectangle().getY() - 1;
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                        }
                    }
                    case "oeste" -> {
                        switch (car.getDestino()[pos]) {
                            case "izquierdadir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() + 1;
                                    if ( newX - initialX >= moveizquierda) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(0);
                                    car.getImageView().setRotate(0);
                                    double newY = car.getRectangle().getY() - 1;
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
                                }
                            }
                            case "udir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() + 1;
                                    if (newX - initialX  >= moveizquierda) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    double newY = car.getRectangle().getY() - 1;
                                    if(initialY - newY<=160) {
                                        car.getRectangle().setRotate(0);
                                        car.getImageView().setRotate(0);
                                        car.getRectangle().setY(newY);
                                        car.getImageView().setLayoutY(newY);
                                    }else{
                                        double newX = car.getRectangle().getX() - 1;
                                        car.getRectangle().setRotate(270);
                                        car.getImageView().setRotate(270);
                                        car.getRectangle().setX(newX);
                                        car.getImageView().setLayoutX(newX);
                                    }
                                }

                            }
                            case "derechadir" -> {
                                if (!movedInX) {
                                    double newX = car.getRectangle().getX() + 1;
                                    if (newX - initialX >= movederecha) {
                                        movedInX = true;
                                    }
                                    car.getRectangle().setX(newX);
                                    car.getImageView().setLayoutX(newX);
                                } else {
                                    car.getRectangle().setRotate(180);
                                    car.getImageView().setRotate(180);
                                    double newY = car.getRectangle().getY() + 1;
                                    car.getRectangle().setY(newY);
                                    car.getImageView().setLayoutY(newY);
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

    private void startSemaforos(){

        setInitialSemaforoColor();

        Timeline startSemaforo = new Timeline(new KeyFrame(Duration.millis(SEMAFORO_TIMER_MILLI), event -> {
            changeSemaforoColor(SEMAFORO_NORTE_DERECHA);
            changeSemaforoColor(SEMAFORO_SUR_DERECHA);
            changeSemaforoColor(SEMAFORO_NORTE_CENTRO);
            changeSemaforoColor(SEMAFORO_SUR_CENTRO);
            changeSemaforoColor(SEMAFORO_NORTE_IZQUIERDA);
            changeSemaforoColor(SEMAFORO_SUR_IZQUIERDA);
        }));
        startSemaforo.setCycleCount(Timeline.INDEFINITE);
        startSemaforo.play();

    }

    private void changeSemaforoColor(int id){

        Circle luzR = semaforoLuzR.get(id - 1); // Subtract 1 because list indices start at 0
        Circle luzA = semaforoLuzA.get(id - 1);
        Circle luzV = semaforoLuzV.get(id - 1);

        Color currentColor = (Color) luzV.getFill();

        Timeline semaforo = new Timeline(new KeyFrame(Duration.millis(1), event -> {

            if (currentColor.equals(COLOR_VERDE)) {
                luzR.setFill(COLOR_OFF);
                luzA.setFill(COLOR_AMARILLO);
                luzV.setFill(COLOR_OFF);

                PauseTransition pause = new PauseTransition(Duration.millis(SEMAFORO_AMARILLO_TIMER_MILLI));
                pause.setOnFinished(e -> {
                    luzR.setFill(COLOR_ROJO);
                    luzA.setFill(COLOR_OFF);
                    luzV.setFill(COLOR_OFF);
                });
                pause.setCycleCount(0);
                pause.play();
            }else{
                PauseTransition pause = new PauseTransition(Duration.millis(SEMAFORO_AMARILLO_TIMER_MILLI));
                pause.setOnFinished(e -> {
                    luzR.setFill(COLOR_OFF);
                    luzA.setFill(COLOR_OFF);
                    luzV.setFill(COLOR_VERDE);
                });
                pause.setCycleCount(0);
                pause.play();
            }
        }));
        semaforo.setCycleCount(0);
        semaforo.play();
    }

    private void setInitialSemaforoColor(){

        // NORTE
        semaforoLuzR1.setFill(COLOR_ROJO);
        semaforoLuzA1.setFill(COLOR_OFF);
        semaforoLuzV1.setFill(COLOR_OFF);

        semaforoLuzR3.setFill(COLOR_ROJO);
        semaforoLuzA3.setFill(COLOR_OFF);
        semaforoLuzV3.setFill(COLOR_OFF);

        semaforoLuzR5.setFill(COLOR_ROJO);
        semaforoLuzA5.setFill(COLOR_OFF);
        semaforoLuzV5.setFill(COLOR_OFF);

        //SUR
        semaforoLuzR2.setFill(COLOR_OFF);
        semaforoLuzA2.setFill(COLOR_OFF);
        semaforoLuzV2.setFill(COLOR_VERDE);

        semaforoLuzR4.setFill(COLOR_OFF);
        semaforoLuzA4.setFill(COLOR_OFF);
        semaforoLuzV4.setFill(COLOR_VERDE);

        semaforoLuzR6.setFill(COLOR_OFF);
        semaforoLuzA6.setFill(COLOR_OFF);
        semaforoLuzV6.setFill(COLOR_VERDE);
    }

    private boolean checkCarPassSemaforo(int semaforo){
        Circle luzV = semaforoLuzV.get(semaforo - 1);
        Color currentColor = (Color) luzV.getFill();
        return currentColor.equals(COLOR_VERDE);
    }

    private void pararSemaforo() {
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            for (CarH c: cars) {
                if(c.getRectangle().getBoundsInParent().intersects(nortePare1.getBoundsInParent())){
                    if(!checkCarPassSemaforo(SEMAFORO_NORTE_DERECHA)){
                        c.setPare(true);
                        c.getAnimationTimer().stop();
                    }else{
                        c.setPare(false);
                        c.getAnimationTimer().start();
                    }
                }
            }

        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }




}
