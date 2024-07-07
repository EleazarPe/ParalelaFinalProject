package com.example.proyectofinal;

import com.example.proyectofinal.logica.Car;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class NormalStreet {
    @FXML
    private Pane root;
    @FXML
    private Rectangle carrito;

    @FXML
    private ImageView carritoImagen;
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
    @FXML
    public void initialize() {
//        String [] args = {"redcar","graycar","whitecar"};
//        Image image = new Image("file:src/main/resources/com/example/proyectofinal/cars/"+ args[new Random().nextInt(3)]+".png");
//        carritoImagen.setImage(image);

        // Colocar la imagen del carrito encima del rectángulo
//        carritoImagen.setLayoutX(carrito.getX());
//        carritoImagen.setLayoutY(carrito.getY());

//        root.setOnKeyPressed(this::handleKeyPressed);
//        root.setOnKeyReleased(this::handleKeyReleased);

        // Para asegurar que el Pane root tenga el enfoque de teclado
//        root.setFocusTraversable(true);

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
    }

//    private void handleKeyPressed(KeyEvent event) {
//        pressedKeys.add(event.getCode().toString());
//    }
//
//    private void handleKeyReleased(KeyEvent event) {
//        pressedKeys.remove(event.getCode().toString());
//    }

//    private void update() {
//        boolean up = pressedKeys.contains("UP");
//        boolean down = pressedKeys.contains("DOWN");
//        boolean left = pressedKeys.contains("LEFT");
//        boolean right = pressedKeys.contains("RIGHT");
//
//        if (up && !down) {
//            velocityY -= acceleration;
//        } else if (down && !up) {
//            velocityY += acceleration;
//        } else {
//            if (velocityY > 0) {
//                velocityY = Math.max(0, velocityY - deceleration);
//            } else if (velocityY < 0) {
//                velocityY = Math.min(0, velocityY + deceleration);
//            }
//        }
//
//        if (left && !right) {
//            velocityX -= acceleration;
//        } else if (right && !left) {
//            velocityX += acceleration;
//        } else {
//            if (velocityX > 0) {
//                velocityX = Math.max(0, velocityX - deceleration);
//            } else if (velocityX < 0) {
//                velocityX = Math.min(0, velocityX + deceleration);
//            }
//        }
//
//        // Limitar la velocidad máxima
//        velocityX = Math.max(-maxSpeed, Math.min(maxSpeed, velocityX));
//        velocityY = Math.max(-maxSpeed, Math.min(maxSpeed, velocityY));
//
//        // Calcular el ángulo según la dirección del movimiento
//        if (up && right) {
//            currentAngle = 45;
//        } else if (up && left) {
//            currentAngle = 315;
//        } else if (down && right) {
//            currentAngle = 135;
//        } else if (down && left) {
//            currentAngle = 225;
//        } else if (up) {
//            currentAngle = 0;
//        } else if (down) {
//            currentAngle = 180;
//        } else if (left) {
//            currentAngle = 270;
//        } else if (right) {
//            currentAngle = 90;
//        }
//
//        carritoX += velocityX;
//        carritoY += velocityY;
//
//        carrito.setX(carritoX);
//        carrito.setY(carritoY);
//        carritoImagen.setLayoutX(carritoX);
//        carritoImagen.setLayoutY(carritoY);
//        carritoImagen.setRotate(currentAngle);
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
        switch (car.getOrigen()){
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
        }
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