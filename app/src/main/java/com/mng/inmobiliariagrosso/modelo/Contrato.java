package com.mng.inmobiliariagrosso.modelo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String desde;
    private String hasta;
    private int montoM;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato() {}
    public Contrato(int id, String desde, String hasta, int montoM, Inquilino inquilino, Inmueble inmueble) {
        this.id = id;
        this.desde = desde;
        this.hasta = hasta;
        this.montoM = montoM;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getIdContrato() {
        return id;
    }

    public void setIdContrato(int id) {
        this.id = id;
    }

    public String getFechaInicio() {
        String dia="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(desde);

            dia = formato.format(d);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }

    public void setFechaInicio(String fechaInicio) {
        this.desde = fechaInicio;
    }

    public String getFechaFin() {
        String dia="";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = dateFormat.parse(hasta);

            dia = formato.format(d);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        return dia;
    }

    public void setFechaFin(String fechaFin) {
        this.hasta = fechaFin;
    }

    public double getMontoAlquiler() {
        return montoM;
    }

    public void setMontoAlquiler(int montoM) {
        this.montoM = montoM;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
