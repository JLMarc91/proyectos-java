package com.retodev.model;

import javax.persistence.*;

@Entity
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idHistorial")
    private int idHistorial;

    @Column(name="CodigoConductor", length = 50)
    private String codigoConductor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUbicacion", referencedColumnName = "idUbicacion")
    private Ubicacion ubicacion;

    public Historial() {
    }

    public Historial(String codigoConductor, Ubicacion ubicacion) {
        this.codigoConductor = codigoConductor;
        this.ubicacion = ubicacion;
    }

    public int getIdHistorial() {
        return idHistorial;
    }

//    public void setIdHistorial(int idHistorial) {
//        this.idHistorial = idHistorial;
//    }

    public String getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(String codigoConductor) {
        this.codigoConductor = codigoConductor;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
