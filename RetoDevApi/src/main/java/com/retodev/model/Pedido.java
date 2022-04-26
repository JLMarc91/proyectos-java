package com.retodev.model;

import javax.persistence.*;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPedido")
    private int idPedido;

    @Column(name="CodigoPedido", length = 50)
    private String codigoPedido;

    @Column(name="Direccion", length = 200)
    private String direccion;

    @Column(name="Estado", length = 50)
    private int estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConductor", referencedColumnName = "idConductor")
    private Conductor idConductor;

    public Pedido() {
    }

    public Pedido(String codigoPedido, String direccion, int estado, Conductor idConductor) {
        this.codigoPedido = codigoPedido;
        this.direccion = direccion;
        this.estado = estado;
        this.idConductor = idConductor;
    }

    public int getIdPedido() {
        return idPedido;
    }

//    public void setIdPedido(int idPedido) {
//        this.idPedido = idPedido;
//    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Conductor getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Conductor idConductor) {
        this.idConductor = idConductor;
    }
}
