/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectonetbean.Clases;

/**
 *
 * @author mario
 */
public class Espectaculo {

    private String id;
    private String nombre;
    private int aforo;
    private String descripcion;
    private String lugar;
    private int coste;

    public Espectaculo(String nombre, int aforo, String descripcion, String lugar, int coste){
        this.nombre = nombre;
        this.aforo = aforo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.coste = coste;
    }
    public Espectaculo(){

    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
