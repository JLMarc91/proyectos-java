package com.retodev.controller;

import com.retodev.excepciones.ConductorNotFoundException;
import com.retodev.model.Conductor;
import com.retodev.repo.IConductorRepo;
import com.retodev.services.Encriptador;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/conductores")
public class ConductorRestController {

    @Autowired
    private IConductorRepo conductores;

    @Autowired
    private Encriptador e;

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(ConductorRestController.class);

    @GetMapping
    public List<Conductor> listarConductores(){

        return conductores.findAll();
    }

    @GetMapping(value = "/{codigoConductor}")
    public Conductor encontrarConductor(@PathVariable("codigoConductor") String codigoConductor){
        Conductor conductor =conductores.findByCodigoConductor(codigoConductor);
        if (conductor==null) {
            LOG.error("Conductor " + codigoConductor+ " not found");
            throw new ConductorNotFoundException(" Conductor" + codigoConductor + "not found");
        }return conductor;
    }

    @PostMapping
    public void insertarConductor(@RequestBody Conductor conductor){
        conductor.setPedidosCompletados(0);
        conductor.setPedidosPendientes(0);
        conductor.setPedidosFallidos(0);
        try {
            conductor.setPassword(e.encriptar(conductor.getPassword()));
        }  catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(ConductorRestController.class.getName()).log(Level.WARNING, "Erro al encriptar contraseña: ", ex);
        }
        conductores.save(conductor);
    }

}
