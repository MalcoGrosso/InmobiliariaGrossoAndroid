package com.mng.inmobiliariagrosso.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    private String Usuario;
    private String Clave;


    public Usuario(){}
    public Usuario(String Usuario, String Clave) {

        this.Usuario = Usuario;
        this.Clave = Clave;

    }


    public String getEmail() {
        return Usuario;
    }

    public void setEmail(String email) {
        this.Usuario = email;
    }

    public String getContraseña() {
        return Clave;
    }

    public void setContraseña(String clave) {
        this.Clave = clave;
    }


}
