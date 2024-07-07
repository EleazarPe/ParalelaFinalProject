package com.example.proyectofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    private int width = 1280;
    private int height = 889;

    public void iniciar(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAplication.class.getResource("normalStreet.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("Street!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onHelloButtonClick() throws IOException {
        iniciar(new Stage());

    }
}