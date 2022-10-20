package com.mng.inmobiliariagrosso.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private double precio;
    private Propietario duenio;
    private boolean disponible = true;
    private String imagen;
    private int idPropietario;
    private int superficie;
    private String latitud;
    private String longitud;


    public Inmueble(int id, String direccion, String uso, String tipo, int ambientes, double precio, Propietario propietario, boolean disponible, String imagen) {
        this.id = id;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.precio = precio;
        this.duenio = propietario;
        this.disponible = disponible;
        this.imagen = imagen;
    }

    public Inmueble(int id, String direccion, String uso, String tipo, int ambientes, double precio, Propietario propietario, boolean disponible, String imagen , int idPropietario, int superficie, String latitud, String longitud) {
        this.id = id;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.precio = precio;
        this.duenio = propietario;
        this.disponible = disponible;
        this.imagen = imagen;
        this.idPropietario = idPropietario;
        this.superficie = superficie;
        this.latitud = latitud;
        this.longitud = longitud;
    }


    public Inmueble() {

    }
    public int getIdInmueble() {
        return id;
    }

    public void setIdInmueble(int idInmueble) {
        this.id = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getPropietario() {
        return duenio;
    }

    public void setPropietario(Propietario propietario) {
        this.duenio = propietario;
    }

    public boolean isEstado() {return disponible;}

    public void setEstado(boolean disponible) {this.disponible = disponible;}

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
