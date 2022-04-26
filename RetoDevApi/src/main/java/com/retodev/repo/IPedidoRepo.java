package com.retodev.repo;

import com.retodev.model.Conductor;
import com.retodev.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepo extends JpaRepository<Pedido, Integer> {
    Pedido findByCodigoPedido(String codigoPedido);
    List<Pedido> findAllByEstado(int estado);
    List<Pedido> findAllByIdConductor(Conductor conductor);
    List<Pedido> findAllByIdConductorAndEstado(Conductor conductor, int estado);
    Integer countByIdConductorAndEstado(Conductor conductor, int estado);
}
