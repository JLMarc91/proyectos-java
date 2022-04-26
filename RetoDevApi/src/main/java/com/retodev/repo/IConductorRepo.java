package com.retodev.repo;

import com.retodev.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConductorRepo extends JpaRepository<Conductor, Integer> {

    Conductor findByCodigoConductor(String codigoConductor);
}
