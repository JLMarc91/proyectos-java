package com.retodev.repo;

import com.retodev.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistorialRepo extends JpaRepository<Historial, Integer> {

    List<Historial> findAllByCodigoConductor(String codigoConductor);
}
