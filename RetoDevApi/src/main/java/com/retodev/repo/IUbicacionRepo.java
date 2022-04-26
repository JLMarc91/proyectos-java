package com.retodev.repo;

import com.retodev.model.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUbicacionRepo extends JpaRepository<Ubicacion, Integer> {
}
