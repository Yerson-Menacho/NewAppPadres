package com.example.newapppadres.Entidades;

import java.util.List;

public class entTareas {

    private int id;
    private String nombreTarea;
    private String detallesTarea;
    private int imagen;
    private int progreso;
    private List<Subtarea> subtareas; // Agregar lista de subtareas

    public entTareas(int id, String nombreTarea, String detallesTarea, int imagen, int progreso, List<Subtarea> subtareas) {
        this.id = id;
        this.nombreTarea = nombreTarea;
        this.detallesTarea = detallesTarea;
        this.imagen = imagen;
        this.progreso = progreso;
        this.subtareas = subtareas;
    }

    public int getId() {
        return id;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public String getDetallesTarea() {
        return detallesTarea;
    }

    public int getImagen() {
        return imagen;
    }

    public int getProgreso() {
        return progreso;
    }

    public List<Subtarea> getSubtareas() {
        return subtareas;
    }

    // Si necesitas establecer el ID manualmente, puedes agregar un método setter
    public void setId(int id) {
        this.id = id;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }
}

