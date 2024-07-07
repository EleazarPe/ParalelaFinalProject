package com.example.proyectofinal.logica;

import java.util.ArrayList;
import java.util.List;

public class Calle {

    private long id;
    private String nombre;
    private boolean derecha;
    private boolean recto;
    private boolean izquierda;

    private List<Vehiculo> vehiculos;

    public Calle(long id, String nombre, boolean derecha, boolean recto, boolean izquierda) {
        this.id = id;
        this.nombre = nombre;
        this.derecha = derecha;
        this.recto = recto;
        this.izquierda = izquierda;
        this.vehiculos = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public Calle setId(long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Calle setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public Calle setDerecha(boolean derecha) {
        this.derecha = derecha;
        return this;
    }

    public boolean isRecto() {
        return recto;
    }

    public Calle setRecto(boolean recto) {
        this.recto = recto;
        return this;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public Calle setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
        return this;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Calle setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
        return this;
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo){
        this.vehiculos.add(vehiculo);
        return vehiculo;
    }

    public Vehiculo removerPrimeroVehiculo(){
        Vehiculo vehiculo = this.vehiculos.getFirst();
        this.vehiculos.remove(vehiculo);
        return vehiculo;
    }

    public void dejarPasar(Vehiculo vehiculo) {

        for(Vehiculo v : vehiculos){

            if (v.getId() == vehiculo.getId()){
                vehiculos.remove(v);
                return;
            }

            if (v.getId() != vehiculo.getId()){
                vehiculos.remove(v);
            }
            
        }

    }
}
