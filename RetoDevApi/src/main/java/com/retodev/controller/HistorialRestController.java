package com.retodev.controller;

import com.retodev.model.Historial;
import com.retodev.repo.IHistorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialRestController {

    @Autowired
    IHistorialRepo historial;

    @GetMapping
    public List<Historial> listarHistorial(){
        return historial.findAll();
    }

    @GetMapping(value = "/{codigoConductor}")
    public List<Historial> buscarHistorial(@PathVariable("codigoConductor") String codigoConductor){
        return historial.findAllByCodigoConductor(codigoConductor);
    }
}
