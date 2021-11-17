package com.example.lab08.model;

import java.io.Serializable;

public class ItemList implements Serializable {
    private String nombre;
    private int celular;
    private String email;
    private int imgResource;

    public ItemList(String nombre, int celular, String email, int imgResource){
        this.nombre=nombre;
        this.celular=celular;
        this.email=email;
        this.imgResource=imgResource;
    }
    public String getNombre(){
        return nombre;
    }
    public int getCelular(){
        return celular;
    }
    public String getEmail(){
        return email;
    }
    public int getImgResource(){
        return imgResource;
    }
}
