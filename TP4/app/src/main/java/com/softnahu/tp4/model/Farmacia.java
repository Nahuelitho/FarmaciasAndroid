package com.softnahu.tp4.model;

import java.io.Serializable;

public class Farmacia implements Serializable {
    private String nombre;
    private String direccion;
    private int foto;
    private String info;
    private double latitud;  // Nueva propiedad
    private double longitud; // Nueva propiedad

    public Farmacia() {
    }

    public Farmacia(String nombre, String direccion, int foto, String info, double latitud, double longitud) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.foto = foto;
        this.info = info;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
