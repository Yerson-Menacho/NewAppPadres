package com.example.newapppadres.Entidades;

public class Subtarea {

    private int id;
    private String nombreSubtarea;
    private boolean completada;

    public Subtarea(int id, String nombreSubtarea, boolean completada) {
        this.id = id;
        this.nombreSubtarea = nombreSubtarea;
        this.completada = completada;
    }

    public int getId() {
        return id;
    }

    public String getNombreSubtarea() {
        return nombreSubtarea;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreSubtarea(String nombreSubtarea) {
        this.nombreSubtarea = nombreSubtarea;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
