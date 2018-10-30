package com.example.mcarrillom.arf;

public class Usuario {
    private String nombre;
    private String correo;
    private String password;

    public void Usuario(){
    }
    public void Usuario(String nombre, String correo, String pass){
        this.nombre =  nombre;
        this.correo =  correo;
        this.password =  pass;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
