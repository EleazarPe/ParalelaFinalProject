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

    private static final int SEMAFORO_NORTE_DERECHA = 5;
    private static final int SEMAFORO_SUR_DERECHA = 6;
    private static final int SEMAFORO_NORTE_CENTRO = 3;
    private static final int SEMAFORO_SUR_CENTRO = 4;
    private static final int SEMAFORO_NORTE_IZQUIERDA = 1;
    private static final int SEMAFORO_SUR_IZQUIERDA = 2;

    private static final int SEMAFORO_TIMER_MILLI = 8000;
    private static final int SEMAFORO_AMARILLO_TIMER_MILLI = 6000;

    private static final String DERECHADIR = "derechadir";
    private static final String IZQUIERDADIR = "izquierdadir";
    private static final String ALANTEDIR = "alantedir";
    private static final String UDIR = "udir";

    private static final String ESTE = "este";
    private static final String OESTE = "oeste";

    private static final int CAR_DISTANCE = 120;



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

    @FXML
    private Rectangle norteAmbIzquierda;
    @FXML
    private Rectangle norteAmbCentro;
    @FXML
    private Rectangle nortAmbDerecha;
    @FXML
    private Rectangle surAmbIzquierda;
    @FXML
    private Rectangle surAmbCentro;
    @FXML
    private Rectangle surAmbDerecha;

    private List<Circle> semaforoLuzR = new ArrayList<>();
    private List<Circle> semaforoLuzA = new ArrayList<>();
    private List<Circle> semaforoLuzV = new ArrayList<>();
    private List<List<Integer>> carrilSemaforo = Arrays.asList(new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<Integer>());

    private List<Integer> ambulancias = new ArrayList<>();
    private List<Boolean> semaforoWorking = Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);

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
        //mover();
        cambioCarril();
        llegaAlcruceRojo();
        resetAllCars();
        pararSemaforo();
//        cruceStop();
    }

    private void resetAllCars() {
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            List<CarH> carsToRemove = new ArrayList<>();
            for (CarH c: cars) {
                if(c.getRectangle().getX() < -100 || c.getRectangle().getX() > root.getWidth()+100 || c.getRectangle().getY() < -100 || c.getRectangle().getY() > root.getHeight()+100){
                    if(c.isTipoEmergencia()){
                        removeAmbulanceQueue(c.getId());
                        root.getChildren().remove(c.getRectangle());
                        root.getChildren().remove(c.getImageView());
                        carsToRemove.add(c);
                        turnOnAllSemaforos();
                        if (!ambulancias.isEmpty()){
                            turnOffSelectiveSemaforos();
                        }
                    }else {
                        for (OriginalCar cc : carstemp) {
                            if (cc.getId() == c.getId()) {
                                System.out.println("Salio de pantalla");
                                c.setSemaforos(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
                                if (c.getOrigen().equals("este")) {
                                    c.getRectangle().setX(root.getWidth() + 100);
                                    c.getRectangle().setY(este.getCenterY() + 275);
                                    c.getImageView().setLayoutX(c.getRectangle().getX());
                                    c.getImageView().setLayoutY(c.getRectangle().getY());
                                    c.getRectangle().setRotate(270);
                                    c.getImageView().setRotate(270);
                                }
                                if (c.getOrigen().equals("oeste")) {
                                    c.getRectangle().setX(-100);
                                    c.getRectangle().setY(oeste.getCenterY() + 595);
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
            }
            cars.removeAll(carsToRemove);
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
        direcciones = new String[3];
        settingDir = 0;
        if(circle.getId().equals("esteamb")) {
          addCar("este", true);
          direccion();
        }else if(circle.getId().equals("oesteamb")){
            addCar("oeste", true);
            direccion();
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
        if (direcciones[settingDir].equals(ALANTEDIR) && settingDir<2) {
            direccion();
        }else{
            buscarCarroById(idcars-1).setDestino(direcciones);
            correr(idcars-1);
            if(buscarCarroById(idcars-1).isTipoEmergencia()){
                turnOnAllSemaforos();
                turnOffSelectiveSemaforos();
            }
        }
        settingDir++;
    }

    private void cercania() {
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            temporal();
            if (!ambulancias.isEmpty()){
                rehabilitarSemaforoCheck();
            }
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

                if (distanciaEuclidiana < CAR_DISTANCE) {
                    // Movimiento de norte a sur (rotación 180)
                    if (car1.getRectangle().getRotate() == 180 && car2.getRectangle().getRotate() == 180) {
                        if (car1.getRectangle().getX() == car2.getRectangle().getX() && // Verifica que estén en el mismo carril
                                car1.getRectangle().getY() > car2.getRectangle().getY() &&
                                car1.getRectangle().getY() - car2.getRectangle().getY() < CAR_DISTANCE) {
                            if (car2.getAnimationTimer() != null) {
                                carroStop(car2);
                            }
                        }
                    }
                    // Movimiento de sur a norte (rotación 0)
                    else if (car1.getRectangle().getRotate() == 0 && car2.getRectangle().getRotate() == 0) {
                        if (car1.getRectangle().getX() == car2.getRectangle().getX() && // Verifica que estén en el mismo carril
                                car1.getRectangle().getY() < car2.getRectangle().getY() &&
                                car2.getRectangle().getY() - car1.getRectangle().getY() < CAR_DISTANCE) {
                            if (car2.getAnimationTimer() != null) {
                                carroStop(car2);
                            }
                        }
                    }
                    // Movimiento de este a oeste (rotación 90)
                    else if (car1.getRectangle().getRotate() == 90 && car2.getRectangle().getRotate() == 90) {
                        if (Math.abs(car1.getRectangle().getY() - car2.getRectangle().getY()) <= 5 && // Verifica que estén en el mismo carril
                                car1.getRectangle().getX() > car2.getRectangle().getX() &&
                                car1.getRectangle().getX() - car2.getRectangle().getX() < CAR_DISTANCE) {
                            if (car2.getAnimationTimer() != null) {
                                carroStop(car2);
                            }
                        }
                    }
                    // Movimiento de oeste a este (rotación 270)
                    else if (car1.getRectangle().getRotate() == 270 && car2.getRectangle().getRotate() == 270) {
                        if (Math.abs(car1.getRectangle().getY() - car2.getRectangle().getY()) <= 5 && // Verifica que estén en el mismo carril
                                car1.getRectangle().getX() < car2.getRectangle().getX() &&
                                car2.getRectangle().getX() - car1.getRectangle().getX() < CAR_DISTANCE) {
                            if (car2.getAnimationTimer() != null) {
                                carroStop(car2);
                            }
                        }
                    }
                }
            }
        }
    }

/*    private void mover(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            for(CarH car: cars){
                if(!car.isRunning()){
                    if(car.getAnimationTimer() != null) {
                        if(moveChecker(car)) {
                            carroMove(car);
                        }
                    }
                }
            }
        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }*/

    private boolean moveChecker(CarH carro){
        for (CarH c : cars) {
            if (c.equals(carro)) {
                continue;
            }
            if ((c.getRectangle().getRotate() == 180 && carro.getRectangle().getRotate() == 180)) {
                if (c.getRectangle().getY() > carro.getRectangle().getY()
                        && c.getRectangle().getY() - carro.getRectangle().getY() < CAR_DISTANCE
                        && Math.abs(c.getRectangle().getX() - carro.getRectangle().getX()) <= 5
                ) {
                    return false;
                }
            } else if((c.getRectangle().getRotate() == 0 && carro.getRectangle().getRotate() == 0)) {
                if (c.getRectangle().getY() < carro.getRectangle().getY()
                        && carro.getRectangle().getY() - c.getRectangle().getY() < CAR_DISTANCE
                        && Math.abs(c.getRectangle().getX() - carro.getRectangle().getX()) <= 5
                ) {
                    return false;
                }
            } else if((c.getRectangle().getRotate() == 90 && carro.getRectangle().getRotate() == 90)) {
                if (c.getRectangle().getX() > carro.getRectangle().getX()
                        && c.getRectangle().getX() - carro.getRectangle().getX() < CAR_DISTANCE
                        && Math.abs(c.getRectangle().getY() - carro.getRectangle().getY()) <= 5
                ) {
                    return false;
                }
            }else if((c.getRectangle().getRotate() == 270 && carro.getRectangle().getRotate() == 270)) {
                if (c.getRectangle().getX() < carro.getRectangle().getX()
                        && carro.getRectangle().getX() - c.getRectangle().getX() < CAR_DISTANCE
                        && Math.abs(c.getRectangle().getY() - carro.getRectangle().getY()) <= 5
                ) {
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
        CarH car = new CarH(carritoImagen, carrito, idcars,ambulancia,id);
        switch (id) {
            case "este" -> {
                carrito.setX(root.getWidth()-100);
                carrito.setY(este.getCenterY() + 275);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(270);
                carritoImagen.setRotate(270);
                car.setAvenidaNorte(true);
            }
            case "oeste" -> {
                carrito.setX(50);
                carrito.setY(oeste.getCenterY()+595);
                carritoImagen.setLayoutX(carrito.getX());
                carritoImagen.setLayoutY(carrito.getY());
                carrito.setRotate(90);
                carritoImagen.setRotate(90);
                car.setAvenidaNorte(false);
            }
        }
        OriginalCar oc = new OriginalCar(idcars);
        cars.add(car);
        if(ambulancia){
            if (ambulancias.isEmpty()){
                ambulancias.add(idcars);
                //turnOffSelectiveSemaforos();
            }else{
                ambulancias.add(idcars);
            }
        }
        idcars++;
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

    private boolean checkCambiarCarril(CarH car, Rectangle carril){

/*        List<String> direccionesCarro = Arrays.asList(car.getDestino());

        List<CarH> carrosEnCambioCarril = cars.stream().filter(c -> c.getRectangle().getBoundsInParent().intersects(carril.getBoundsInParent()) && !c.equals(car)).toList();

        for (CarH c : carrosEnCambioCarril){
            // Si el carro esta mas abajo en el eje Y que carro, entonces la resta dara negativo y lo contrario si esta arriba.
            boolean otroCarroEstaArribaDeCarro = c.getRectangle().getY() - car.getRectangle().getY() < 0 ? true : false;
            System.out.println("El carro esta arriba:" + otroCarroEstaArribaDeCarro + " " + c.getRectangle().getY() + " " + car.getRectangle().getY());

            if (car.getOrigen().equalsIgnoreCase(ESTE)){
                if(direccionesCarro.contains(DERECHADIR) && otroCarroEstaArribaDeCarro){
                    return false;
                }else if (!otroCarroEstaArribaDeCarro){
                    return false;
                }
            }else{
                if(direccionesCarro.contains(DERECHADIR) && !otroCarroEstaArribaDeCarro){
                    return false;
                }else if (otroCarroEstaArribaDeCarro){
                    return false;
                }
            }
        }*/
        return true;
    }

    public void cambioCarril(){
        Timeline collisionChecker = new Timeline(new KeyFrame(Duration.millis(1), event -> {
            for (CarH car : cars) {
                if (car.getRectangle().getBoundsInParent().intersects(carrilNorteTercero.getBoundsInParent()) && !car.getCarril() && car.getOrigen().equals(ESTE)){
                    if (checkCambiarCarril(car, carrilNorteTercero)){
                        //carroMove(car);
                        car.setCarril(true);
                        if(!car.getDestino()[2].equals(ALANTEDIR)){
                            System.out.println("Cambio de carril1");
                            cambioCarrilAction(car,2);
                        }
                    }else{
                        carroStop(car);
                    }

                }else if(car.getRectangle().getBoundsInParent().intersects(carrilNorteSegundo.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("este")) {
                    if (checkCambiarCarril(car, carrilNorteSegundo)){
                        //carroMove(car);
                        car.setCarril(true);
                        if(!car.getDestino()[1].equals(ALANTEDIR)){
                            System.out.println("Cambio de carril1");
                            cambioCarrilAction(car,1);
                        }
                    }else{
                        carroStop(car);
                    }

                }else if (car.getRectangle().getBoundsInParent().intersects(carrilNortePrimero.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("este")) {

                    if (checkCambiarCarril(car, carrilNortePrimero)){
                        //carroMove(car);
                        car.setCarril(true);
                        if(!car.getDestino()[0].equals(ALANTEDIR)){
                            System.out.println("Cambio de carril1");
                            cambioCarrilAction(car,0);
                        }
                    }else{
                        carroStop(car);
                    }

                }
                if (car.getRectangle().getBoundsInParent().intersects(carrilSurPrimero.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("oeste")) {

                    if (checkCambiarCarril(car, carrilSurPrimero)){
                        //carroMove(car);
                        car.setCarril(true);
                        if(!car.getDestino()[0].equals(ALANTEDIR)){
                            System.out.println("Cambio de carril1");
                            cambioCarrilAction(car,0);
                        }
                    }else{
                        carroStop(car);
                    }

                    /*car.setCarril(true);
                    if(!car.getDestino()[0].equals(ALANTEDIR)){
                        System.out.println("Cambio de carril1");
                        cambioCarrilAction(car,0);
                    }*/
                }else if(car.getRectangle().getBoundsInParent().intersects(carrilSurSegundo.getBoundsInParent()) && !car.getCarril()&& !car.getCarril() && car.getOrigen().equals("oeste")) {

                    if (checkCambiarCarril(car, carrilSurSegundo)){
                        //carroMove(car);
                        car.setCarril(true);
                        if(!car.getDestino()[1].equals(ALANTEDIR)){
                            System.out.println("Cambio de carril1");
                            cambioCarrilAction(car,1);
                        }
                    }else{
                        carroStop(car);
                    }

                    /*car.setCarril(true);
                    if(!car.getDestino()[1].equals(ALANTEDIR)){
                        System.out.println("Cambio de carril2");
                        cambioCarrilAction(car, 1);
                    }*/

                }else if (car.getRectangle().getBoundsInParent().intersects(carrilSurTercero.getBoundsInParent()) && !car.getCarril() && !car.getCarril() && car.getOrigen().equals("oeste")) {

                    if (checkCambiarCarril(car, carrilSurSegundo)){
                        //carroMove(car);
                        car.setCarril(true);
                        if(!car.getDestino()[2].equals(ALANTEDIR)){
                            System.out.println("Cambio de carril1");
                            cambioCarrilAction(car,2);
                        }
                    }else{
                        carroStop(car);
                    }

                    /*car.setCarril(true);
                    if(!car.getDestino()[2].equals(ALANTEDIR)){
                        System.out.println("Cambio de carril3");
                        cambioCarrilAction(car, 2);
                    }*/

                }
                if(car.getOrigen().equals("este") && (!car.getRectangle().getBoundsInParent().intersects(carrilNorteTercero.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilNorteSegundo.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilNortePrimero.getBoundsInParent()))){
                    car.setCarril(false);
                }

                if(car.getOrigen().equals("oeste") && (!car.getRectangle().getBoundsInParent().intersects(carrilSurPrimero.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilSurSegundo.getBoundsInParent()) && !car.getRectangle().getBoundsInParent().intersects(carrilSurTercero.getBoundsInParent()))){
                    car.setCarril(false);
                }

                enableSemaforoForIzquierdaAndUDir(car);

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
                            case IZQUIERDADIR, "udir" -> {
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
                            case IZQUIERDADIR, "udir" -> {
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
        if(car.getAnimationTimer() != null && !car.getDestino()[pos].equals(ALANTEDIR)){
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
                        if (!car.getDestino()[0].equals(ALANTEDIR)) {
                            System.out.println("En el cruce 1");
                            cruzando(car, 0);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[1].equals(ALANTEDIR)) {
                            System.out.println("En el cruce 2");
                            cruzando(car, 1);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[2].equals(ALANTEDIR)) {
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
                        if (!car.getDestino()[0].equals(ALANTEDIR)) {
                            System.out.println("En el cruce 1");
                            cruzando(car, 0);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[1].equals(ALANTEDIR)) {
                            System.out.println("En el cruce 2");
                            cruzando(car, 1);
                        }
                    } else if (car.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent()) && !car.isInCrossing()) {
                        car.setInCrossing(true);
                        if (!car.getDestino()[2].equals(ALANTEDIR)) {
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
                            case IZQUIERDADIR -> {
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
                            case IZQUIERDADIR -> {
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
        if(car.getAnimationTimer() != null && !car.getDestino()[pos].equals(ALANTEDIR)){
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
                    if (ambulancias.isEmpty()){
                        attemptMoveNormal(c, SEMAFORO_NORTE_DERECHA);
                    }else{
                        attemptMoveAmbulance(c, SEMAFORO_NORTE_DERECHA);
                    }
                }

                else if(c.getRectangle().getBoundsInParent().intersects(nortePare2.getBoundsInParent())){
                    if (ambulancias.isEmpty()){
                        attemptMoveNormal(c, SEMAFORO_NORTE_CENTRO);
                    }else{
                        attemptMoveAmbulance(c, SEMAFORO_NORTE_CENTRO);
                    }
                }

                else if(c.getRectangle().getBoundsInParent().intersects(nortePare3.getBoundsInParent())){
                    if (ambulancias.isEmpty()){
                        attemptMoveNormal(c, SEMAFORO_NORTE_IZQUIERDA);
                    }else{
                        attemptMoveAmbulance(c, SEMAFORO_NORTE_IZQUIERDA);
                    }
                }

                else if(c.getRectangle().getBoundsInParent().intersects(surPare1.getBoundsInParent())){
                   if (ambulancias.isEmpty()){
                        attemptMoveNormal(c, SEMAFORO_SUR_IZQUIERDA);
                   }else{
                        attemptMoveAmbulance(c, SEMAFORO_SUR_IZQUIERDA);
                   }
                }

                else if(c.getRectangle().getBoundsInParent().intersects(surPare2.getBoundsInParent())){
                    if (ambulancias.isEmpty()){
                        attemptMoveNormal(c, SEMAFORO_SUR_CENTRO);
                    }else{
                        attemptMoveAmbulance(c, SEMAFORO_SUR_CENTRO);
                    }
                }

                else if(c.getRectangle().getBoundsInParent().intersects(surPare3.getBoundsInParent())){
                    if (ambulancias.isEmpty()){
                        attemptMoveNormal(c, SEMAFORO_SUR_DERECHA);
                    }else{
                        attemptMoveAmbulance(c, SEMAFORO_SUR_DERECHA);
                    }
                }else{
                    if(!c.isRunning()){
                        if(c.getAnimationTimer() != null) {
                            if(moveChecker(c)) {
                                carroMove(c);
                            }
                        }
                    }
                }
            }

        }));
        collisionChecker.setCycleCount(Timeline.INDEFINITE);
        collisionChecker.play();
    }

    /*private boolean attemptCarrilSemaforo(int idSemaforo, int idCar){
        if (checkCarPassCarrilSemaforo(idSemaforo)){

            if (checkCarFromCarrilSemaforo(idSemaforo, idCar)){
                return true;
            }

            addCarFromCarrilSemaforo(idSemaforo, idCar);

            removeCarFromCarrilSemaforo(idSemaforo, idCar);

        }
        return false;
    }

    private synchronized void removeCarFromCarrilSemaforo(int idSemaforo, int idCar){

        switch (idSemaforo){
            case SEMAFORO_SUR_DERECHA:
                idSemaforo = SEMAFORO_SUR_CENTRO;
                break;
            case SEMAFORO_SUR_CENTRO:
                idSemaforo = SEMAFORO_SUR_IZQUIERDA;
                break;
            case SEMAFORO_SUR_IZQUIERDA:
                idSemaforo = SEMAFORO_NORTE_DERECHA;
                break;
            case SEMAFORO_NORTE_IZQUIERDA:
                idSemaforo = SEMAFORO_NORTE_CENTRO;
                break;
            case SEMAFORO_NORTE_CENTRO:
                idSemaforo = SEMAFORO_NORTE_DERECHA;
                break;
            case SEMAFORO_NORTE_DERECHA:
                idSemaforo = SEMAFORO_NORTE_IZQUIERDA;
                break;

        }

        List<Integer> semaforo = carrilSemaforo.get(idSemaforo - 1);
        Integer carro = semaforo.stream().filter(integer -> integer.equals(idCar)).findFirst().orElse(null);
        if (carro != null){
            semaforo.remove(carro);
        }
    }

    private synchronized boolean addCarFromCarrilSemaforo(int idSemaforo, int idCar){
        List<Integer> semaforo = carrilSemaforo.get(idSemaforo - 1);
        return semaforo.add(idCar);
    }

    private boolean checkCarFromCarrilSemaforo(int idSemaforo, int idCar){
        List<Integer> semaforo = carrilSemaforo.get(idSemaforo - 1);
        return semaforo.contains(idCar);
    }

    private boolean checkCarPassCarrilSemaforo(int id){
        return  carrilSemaforo.get(id - 1).size() < 3;
    }*/

    private void carroMove(CarH c){
        if (!c.isRunning()){
            c.setRunning(true);
            c.getAnimationTimer().start();
        }
    }
    private void carroStop(CarH c){
        if (c.isRunning()){
            c.setRunning(false);
            c.getAnimationTimer().stop();
        }
    }

    private void attemptMoveNormal(CarH c, int semaforo){

        if (!moveChecker(c)){
            carroStop(c);
        }
        else if (!checkCarroIsCrossing(c, semaforo)) {
            carroStop(c);
        }
        else if (c.getSemaforos(semaforo)){
            carroMove(c);
        }else if (checkCarPassSemaforo(semaforo)){
            c.setSemaforos(semaforo);
            carroMove(c);
        }
        else{
            carroStop(c);
        }
    }

    private void attemptMoveAmbulance(CarH c, int semaforo){

        CarH ambulancia = cars.stream().filter(car -> car.getId() == ambulancias.getFirst()).findFirst().get();

        if (!moveChecker(c)){
            carroStop(c);
        }
        else if (isSemaforoWorking(semaforo) && !c.isTipoEmergencia()){
            attemptMoveNormal(c, semaforo);
        }
        // 1. Si un carro esta cruzando hay que dejarlo terminar
        else if (c.getSemaforos(semaforo)){
            carroMove(c);
        }
        // 3. Revisar si hay algun carro del carril contrario in crossing que no sea la ambulancia, dejarlo pasar antes de la ambulancia
        else if (!checkCarroIsCrossing(c, semaforo)) {
            carroStop(c);
        }
        // 4. Si no hay mas chequeos, dejar pasar al carro
        else{
            c.setSemaforos(semaforo);
            carroMove(c);
        }
    }
    private boolean carroIsCrossing(CarH ambulancia){
        for (CarH c:  cars) {
            if(c.isInCrossing() && !c.getOrigen().equals(ambulancia.getOrigen())){
                return true;
            }
        }
        return false;
    }

    private boolean checkCarroIsCrossing(CarH carro, int semaforo) {

        for (CarH c : cars) {

            if (c == carro){
                continue;
            }

            if (c.getDestino() == null || carro.getDestino() == null) {
                continue;
            }

            if (
                    carro.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent())
                    || carro.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent())
                    || carro.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent())
            ){
                continue;
            }

            List<String> direcciones = Arrays.stream(c.getDestino()).filter(s -> s != null).toList();
            List<String> direccionesCarro = Arrays.stream(carro.getDestino()).filter(s -> s != null).toList();

            if (semaforo - 1 == SEMAFORO_NORTE_DERECHA || semaforo - 1 == SEMAFORO_SUR_DERECHA) {
                if (c.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent())) {

                    if (!c.getOrigen().equals(carro.getOrigen()) && !direccionesCarro.contains(UDIR)){
                        return false;
                    }else if(direccionesCarro.contains(UDIR)){

                        String tempOrigen = carro.getOrigen();
                        if (carro.getRectangle().getRotate() == 90 || carro.getOrigen().equalsIgnoreCase(ESTE)){
                            tempOrigen = OESTE;
                        }else if (carro.getRectangle().getRotate() == 270 || carro.getOrigen().equalsIgnoreCase(OESTE)){
                            tempOrigen = ESTE;
                        }
                        if (!c.getOrigen().equals(tempOrigen)){
                            return false;
                        }
                    }
                }
            } else if (semaforo - 1 == SEMAFORO_NORTE_CENTRO || semaforo - 1 == SEMAFORO_SUR_CENTRO) {
                if (c.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent())) {
                    // Revisando si carro va a doblar en U
                    if (!c.getOrigen().equals(carro.getOrigen()) && !direccionesCarro.contains(UDIR)){
                        return false;
                    }else if(direccionesCarro.contains(UDIR)){

                        String tempOrigen = carro.getOrigen();
                        if (carro.getRectangle().getRotate() == 90 || carro.getOrigen().equalsIgnoreCase(ESTE)){
                            tempOrigen = OESTE;
                        }else if (carro.getRectangle().getRotate() == 270 || carro.getOrigen().equalsIgnoreCase(OESTE)){
                            tempOrigen = ESTE;
                        }
                        if (!c.getOrigen().equals(tempOrigen)){
                            return false;
                        }
                    }
                }
            } else if (semaforo - 1 == SEMAFORO_NORTE_IZQUIERDA || semaforo - 1 == SEMAFORO_SUR_IZQUIERDA) {
                if (c.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent())) {
                    // Revisando si carro va a doblar en U
                    if (!c.getOrigen().equals(carro.getOrigen()) && !direccionesCarro.contains(UDIR)){
                        return false;
                    }else if(direccionesCarro.contains(UDIR)){

                        String tempOrigen = carro.getOrigen();
                        if (carro.getRectangle().getRotate() == 90 || carro.getOrigen().equalsIgnoreCase(ESTE)){
                            tempOrigen = OESTE;
                        }else if (carro.getRectangle().getRotate() == 270 || carro.getOrigen().equalsIgnoreCase(OESTE)){
                            tempOrigen = ESTE;
                        }
                        if (!c.getOrigen().equals(tempOrigen)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isSemaforoWorking(int semaforo){
        return semaforoWorking.get(semaforo - 1);
    }

    private void turnOffSemaforo(int semaforo){
        semaforoWorking.set(semaforo - 1, Boolean.FALSE);
    }

    private void turnOnSemaforo(int semaforo){
        semaforoWorking.set(semaforo - 1, Boolean.TRUE);
    }

    private void turnOnAllSemaforos(){
        semaforoWorking = Arrays.asList(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
    }
    private void turnOffAllSemaforos(){
        semaforoWorking = Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
    }
    private void removeAmbulanceQueue(int id){
        Integer ambulanciaId = id;
        ambulancias.remove(ambulanciaId);
    }

    private void rehabilitarSemaforoCheck(){

        if (ambulancias.isEmpty()){
            return;
        }
        CarH ambulancia = cars.stream().filter(car -> car.getId() == ambulancias.getFirst()).findFirst().orElse(null);
        if (ambulancia == null){
            System.out.println("No hay ambulancia: " + ambulancias.getFirst());
            return;
        }

        // NorteIzquierda
        if(ambulancia.getRectangle().getBoundsInParent().intersects(norteAmbIzquierda.getBoundsInParent())){
            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOnSemaforo(SEMAFORO_NORTE_CENTRO);
                turnOnSemaforo(SEMAFORO_NORTE_DERECHA);
                turnOnSemaforo(SEMAFORO_NORTE_IZQUIERDA);
            }else{
                turnOnAllSemaforos();
            }
        }
        // Norte Centro
        if(ambulancia.getRectangle().getBoundsInParent().intersects(norteAmbCentro.getBoundsInParent())){
            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOnSemaforo(SEMAFORO_NORTE_DERECHA);
                turnOnSemaforo(SEMAFORO_NORTE_CENTRO);
            }else{
                turnOnSemaforo(SEMAFORO_SUR_IZQUIERDA);
                turnOnSemaforo(SEMAFORO_SUR_CENTRO);
                turnOnSemaforo(SEMAFORO_SUR_DERECHA);
                turnOnSemaforo(SEMAFORO_NORTE_CENTRO);
                turnOnSemaforo(SEMAFORO_NORTE_DERECHA);
            }
        }
        // Norte Derecha
        if(ambulancia.getRectangle().getBoundsInParent().intersects(nortAmbDerecha.getBoundsInParent())){
            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOnSemaforo(SEMAFORO_NORTE_DERECHA);
            }else{
                turnOnSemaforo(SEMAFORO_SUR_IZQUIERDA);
                turnOnSemaforo(SEMAFORO_SUR_CENTRO);
                turnOnSemaforo(SEMAFORO_SUR_DERECHA);
                turnOnSemaforo(SEMAFORO_NORTE_DERECHA);
            }
        }
        // Sur Izquierda
        if(ambulancia.getRectangle().getBoundsInParent().intersects(surAmbIzquierda.getBoundsInParent())){
            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOnSemaforo(SEMAFORO_NORTE_IZQUIERDA);
                turnOnSemaforo(SEMAFORO_NORTE_CENTRO);
                turnOnSemaforo(SEMAFORO_NORTE_DERECHA);
                turnOnSemaforo(SEMAFORO_SUR_IZQUIERDA);
            }else{
                turnOnSemaforo(SEMAFORO_SUR_IZQUIERDA);
            }
        }
        // Sur Centro
        if(ambulancia.getRectangle().getBoundsInParent().intersects(surAmbCentro.getBoundsInParent())){

            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOnSemaforo(SEMAFORO_NORTE_IZQUIERDA);
                turnOnSemaforo(SEMAFORO_NORTE_CENTRO);
                turnOnSemaforo(SEMAFORO_NORTE_DERECHA);
                turnOnSemaforo(SEMAFORO_SUR_CENTRO);
                turnOnSemaforo(SEMAFORO_SUR_IZQUIERDA);
            }else{
                turnOnSemaforo(SEMAFORO_SUR_IZQUIERDA);
                turnOnSemaforo(SEMAFORO_SUR_CENTRO);
            }
        }
        //Sur Derecho
        if(ambulancia.getRectangle().getBoundsInParent().intersects(surAmbDerecha.getBoundsInParent())){
            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOnAllSemaforos();
            }else{
                turnOnSemaforo(SEMAFORO_SUR_IZQUIERDA);
                turnOnSemaforo(SEMAFORO_SUR_CENTRO);
                turnOnSemaforo(SEMAFORO_SUR_DERECHA);
            }
        }

    }

    private void turnOffSelectiveSemaforos(){
        CarH ambulancia = cars.stream().filter(car -> car.getId() == ambulancias.getFirst()).findFirst().get();

        if (ambulancia.getDestino() == null){
            return;
        }

        List<String> direcciones = Arrays.stream(ambulancia.getDestino()).filter(s -> s != null).toList();
        if (
                direcciones.contains(DERECHADIR)
                        || direcciones.contains(ALANTEDIR)
                        || direcciones.getFirst().equalsIgnoreCase(UDIR)
        ){
            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOffSemaforo(SEMAFORO_NORTE_DERECHA);
                turnOffSemaforo(SEMAFORO_NORTE_CENTRO);
                turnOffSemaforo(SEMAFORO_NORTE_IZQUIERDA);
            }else{
                turnOffSemaforo(SEMAFORO_SUR_DERECHA);
                turnOffSemaforo(SEMAFORO_SUR_CENTRO);
                turnOffSemaforo(SEMAFORO_SUR_IZQUIERDA);
            }
        }
        if (direcciones.size() >= 2 && direcciones.get(1).equalsIgnoreCase(UDIR)){
            if (ambulancia.getOrigen().equalsIgnoreCase(ESTE)){
                turnOffSemaforo(SEMAFORO_SUR_DERECHA);
            }else{
                turnOffSemaforo(SEMAFORO_NORTE_IZQUIERDA);
            }
        }
        if(direcciones.size() == 3 && direcciones.get(2).equalsIgnoreCase(UDIR)){
            turnOffAllSemaforos();
        }

    }

/*    private int checkIzquierdaAmbulancia(CarH c, CarH ambulancia){

        for (int i = 0 ; i < ambulancia.getDestino().length && i < c.getDestino().length  ; i++){
            String direccionAmbulancia = ambulancia.getDestino()[i];
            String direccionCarro = c.getDestino()[i];

            if (direccionCarro.equalsIgnoreCase(IZQUIERDADIR) && (direccionAmbulancia.equalsIgnoreCase(IZQUIERDADIR) || direccionAmbulancia.equalsIgnoreCase(ALANTEDIR))){
                return i;
            }
        }
        return -1;
    }*/

    private void enableSemaforoForIzquierdaAndUDir(CarH car){

        if (car.getRectangle().getBoundsInParent().intersects(cruceDerecha.getBoundsInParent())){
            if (car.getRectangle().getBoundsInParent().intersects(nortePare1.getBoundsInParent())){
                car.setSemaforos(SEMAFORO_NORTE_DERECHA);
            }else if (car.getRectangle().getBoundsInParent().intersects(surPare3.getBoundsInParent())){
                car.setSemaforos(SEMAFORO_SUR_DERECHA);
            }
        }

        else if (car.getRectangle().getBoundsInParent().intersects(cruceCentro.getBoundsInParent())) {
            if (car.getRectangle().getBoundsInParent().intersects(nortePare2.getBoundsInParent())) {
                car.setSemaforos(SEMAFORO_NORTE_CENTRO);
            } else if (car.getRectangle().getBoundsInParent().intersects(surPare2.getBoundsInParent())) {
                car.setSemaforos(SEMAFORO_SUR_CENTRO);
            }
        }

        else if (car.getRectangle().getBoundsInParent().intersects(cruceIzquiera.getBoundsInParent())) {
            if (car.getRectangle().getBoundsInParent().intersects(nortePare3.getBoundsInParent())) {
                car.setSemaforos(SEMAFORO_NORTE_IZQUIERDA);
            } else if (car.getRectangle().getBoundsInParent().intersects(surPare1.getBoundsInParent())) {
                car.setSemaforos(SEMAFORO_SUR_IZQUIERDA);
            }
        }
    }

}
