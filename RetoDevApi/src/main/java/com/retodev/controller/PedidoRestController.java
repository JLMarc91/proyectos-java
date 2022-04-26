package com.retodev.controller;

import com.retodev.model.Conductor;
import com.retodev.model.Pedido;
import com.retodev.repo.IConductorRepo;
import com.retodev.repo.IPedidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

    @Autowired
    private IPedidoRepo pedidos;
    @Autowired
    private IConductorRepo conductores;

    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidos.findAll();
    }

    @GetMapping(value = "/pedidos-conductor/{codigoConductor}")
    public List<Pedido> listarPedidosConductor(@PathVariable("codigoConductor") String codigoConductor){
        Conductor conductor = conductores.findByCodigoConductor(codigoConductor);
        return pedidos.findAllByIdConductor(conductor);
    }
    @GetMapping(value = "/pedidos-conductor-estado/{codigoConductor}/{estado}")
    public List<Pedido> listarPedidosConductorEstado(@PathVariable("codigoConductor") String codigoConductor,@PathVariable("estado") int estado ){
        Conductor conductor = conductores.findByCodigoConductor(codigoConductor);
        return pedidos.findAllByIdConductorAndEstado(conductor,estado);
    }
    @GetMapping(value = "/pedidos-estado/{estado}")
    public List<Pedido> listarPedidosConductor(@PathVariable("estado") int estado){
        return pedidos.findAllByEstado(estado);
    }
    @PostMapping(value = "/{codigoConductor}")
    public void nuevoPedido(@RequestBody Pedido pedido, @PathVariable("codigoConductor") String codigoConductor){
        Conductor conductor = conductores.findByCodigoConductor(codigoConductor);
        pedido.setIdConductor(conductor);
        pedido.setEstado(1);
        pedidos.save(pedido);
        actualizarPedidosConductor(conductor);
    }
    @PutMapping(value = "pedido-finalizar/{codigoPedido}")
    public void finalizarPedido(@PathVariable("codigoPedido") String codigoPedido ){
        Pedido pedido = pedidos.findByCodigoPedido(codigoPedido);
        pedido.setEstado(2);
        pedidos.save(pedido);
        actualizarPedidosConductor(pedido.getIdConductor());
    }
    @PutMapping(value = "pedido-fallido/{codigoPedido}")
    public void fallidoPedido(@PathVariable("codigoPedido") String codigoPedido ){
        Pedido pedido = pedidos.findByCodigoPedido(codigoPedido);
        pedido.setEstado(3);
        pedidos.save(pedido);
        actualizarPedidosConductor(pedido.getIdConductor());
    }
    @PutMapping(value = "pedido-cancelar/{codigoPedido}")
    public void cancelarPedido(@PathVariable("codigoPedido") String codigoPedido ){
        Pedido pedido = pedidos.findByCodigoPedido(codigoPedido);
        pedido.setEstado(4);
        pedidos.save(pedido);
        actualizarPedidosConductor(pedido.getIdConductor());
    }

    public void actualizarPedidosConductor(Conductor conductor){
        int nPendientes = pedidos.countByIdConductorAndEstado(conductor,1);
        int nFinalizados  = pedidos.countByIdConductorAndEstado(conductor,2);
        int nFallidos  = pedidos.countByIdConductorAndEstado(conductor,3);
        conductor.setPedidosPendientes(nPendientes);
        conductor.setPedidosCompletados(nFinalizados);
        conductor.setPedidosFallidos(nFallidos);
        conductores.save(conductor);
    }
}
