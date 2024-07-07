package com.example.proyectofinal.logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InterseccionEscenario1 {

    private Calle norte;
    private Calle sur;
    private Calle este;
    private Calle oeste;
    private long idGenerator = 0;

    private final List<Vehiculo> vehiculoQueue;

    InterseccionEscenario1(){
        this.norte = new Calle(0, "Calle Norte",  true, true, true);
        this.sur = new Calle(0, "Calle Sur",  true, true, true);
        this.este = new Calle(0, "Calle Este",  true, true, true);
        this.oeste = new Calle(0, "Calle Oeste",  true, true, true);
        this.vehiculoQueue = new ArrayList<>();
    }

    public Calle getNorte() {
        return norte;
    }

    public InterseccionEscenario1 setNorte(Calle norte) {
        this.norte = norte;
        return this;
    }

    public Calle getSur() {
        return sur;
    }

    public InterseccionEscenario1 setSur(Calle sur) {
        this.sur = sur;
        return this;
    }

    public Calle getEste() {
        return este;
    }

    public InterseccionEscenario1 setEste(Calle este) {
        this.este = este;
        return this;
    }

    public Calle getOeste() {
        return oeste;
    }

    public InterseccionEscenario1 setOeste(Calle oeste) {
        this.oeste = oeste;
        return this;
    }

    public List<Vehiculo> getVehiculoQueue() {
        return vehiculoQueue;
    }

    private long generarId(){
        this.idGenerator += 1;
        return this.idGenerator;
    }

    public void dejarPasar(Vehiculo vehiculo) {

        if (norte.getVehiculos().contains(vehiculo)) {
            norte.dejarPasar(vehiculo);
        }

        if (sur.getVehiculos().contains(vehiculo)) {
            sur.dejarPasar(vehiculo);
        }

        if (este.getVehiculos().contains(vehiculo)) {
            este.dejarPasar(vehiculo);
        }

        if (oeste.getVehiculos().contains(vehiculo)) {
            oeste.dejarPasar(vehiculo);
        }
    }

    public Vehiculo agregarVehiculo(Vehiculo vehiculo){
        vehiculo.setId(generarId());
        vehiculoQueue.add(vehiculo);
        return vehiculo;
    }

    public Vehiculo agregarRandomVehiculo(){

        String direccion = "";
        boolean tipoEmergencia = false;

        Random random = new Random();
        int direccionRandomNumber = random.nextInt(1,5);
        if (direccionRandomNumber == 1){
            direccion = "recto";
        }
        else if (direccionRandomNumber == 2){
            direccion = "derecha";
        }
        else if (direccionRandomNumber == 3){
            direccion = "izquierda";
        }
        else{
            direccion = "u";
        }

        if (random.nextInt(1,21) == 1){
            tipoEmergencia = true;
        }

        return agregarVehiculo(new Vehiculo(-1, true, direccion));
    }

}
