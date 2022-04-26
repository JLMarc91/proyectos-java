package com.retodev.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUbicacion")
    private int idUbicacion;

    @Column(name="CoordenadaX", length = 100)
    private String coordenadaX;

    @Column(name="CoordenadaY", length = 50)
    private String coordenadaY;

    @Column(name="Fecha", length = 100)
    private Timestamp fecha;

    public Ubicacion() {
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

 //   public void setIdUbicacion(int idUbicacion) {
 //       this.idUbicacion = idUbicacion;
 //   }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setUbicacion(String coordenadaX, String coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
}
