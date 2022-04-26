package com.retodev.model;

import javax.persistence.*;

@Entity
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idConductor")
    private int idConductor;

    @Column(name="CodigoConductor", length = 50)
    private String codigoConductor;

    @Column(name="Password", length = 100)
    private String password;

    @Column(name="PedidosCompletados", length = 50)
    private int pedidosCompletados;

    @Column(name="pedidosPendientes", length = 50)
    private int pedidosPendientes;

    @Column(name="pedidosFallidos", length = 50)
    private int pedidosFallidos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUltimaUbicacion", referencedColumnName = "idUbicacion")
    private Ubicacion ultimaUbicacion;

    public Conductor() {}

    public Conductor(String codigoConductor, String password) {
        this.codigoConductor = codigoConductor;
        this.password = password;
    }

    public int getIdConductor() {
        return idConductor;
    }

//    public void setIdConductor(int idConductor) {
//         this.idConductor = idConductor;
//    }

    public String getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(String codigoConductor) {
        this.codigoConductor = codigoConductor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPedidosCompletados() {
        return pedidosCompletados;
    }

    public void setPedidosCompletados(int pedidosCompletados) {
        this.pedidosCompletados = pedidosCompletados;
    }

    public int getPedidosPendientes() {
        return pedidosPendientes;
    }

    public void setPedidosPendientes(int pedidosPendientes) {
        this.pedidosPendientes = pedidosPendientes;
    }

    public int getPedidosFallidos() {
        return pedidosFallidos;
    }

    public void setPedidosFallidos(int pedidosFallidos) {
        this.pedidosFallidos = pedidosFallidos;
    }

    public Ubicacion getUltimaUbicacion() {
        return ultimaUbicacion;
    }

    public void setUltimaUbicacion(Ubicacion ultimaUbicacion) {
        this.ultimaUbicacion = ultimaUbicacion;
    }

}
