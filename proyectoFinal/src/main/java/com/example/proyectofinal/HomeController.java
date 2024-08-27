package com.example.proyectofinal;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {



    public void iniciar(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAplication.class.getResource("normalStreet.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 889);
        stage.setTitle("Street!");
        stage.setScene(scene);
        stage.show();
    }
    public void iniciar2(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainAplication.class.getResource("highway.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 900);
        stage.setTitle("Higway!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void nomralbtn() throws IOException {
        iniciar(new Stage());

    }
    @FXML
    protected void highbtn() throws IOException {
        iniciar2(new Stage());

    }
}