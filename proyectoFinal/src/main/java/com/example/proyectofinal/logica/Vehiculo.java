package com.example.proyectofinal.logica;

import java.util.Optional;

public class Vehiculo {

    private long id;
    private boolean tipoEmergencia;
    private String direccion;

    public Vehiculo(long id, boolean tipoEmergencia, String direccion) {
        this.id = id;
        this.tipoEmergencia = tipoEmergencia;
        this.direccion = direccion;
    }

    public long getId() {
        return id;
    }

    public Vehiculo setId(long id) {
        this.id = id;
        return this;
    }

    public boolean isTipoEmergencia() {
        return tipoEmergencia;
    }

    public Vehiculo setTipoEmergencia(boolean tipoEmergencia) {
        this.tipoEmergencia = tipoEmergencia;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public Vehiculo setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }
}
