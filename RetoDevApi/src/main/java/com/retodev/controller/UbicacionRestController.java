package com.retodev.controller;

import com.retodev.excepciones.ConductorNotFoundException;
import com.retodev.excepciones.UbicacionBadRequestException;
import com.retodev.model.Conductor;
import com.retodev.model.Historial;
import com.retodev.model.Ubicacion;
import com.retodev.repo.IConductorRepo;
import com.retodev.repo.IHistorialRepo;
import com.retodev.repo.IUbicacionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionRestController {

    @Autowired
    private IUbicacionRepo ubicaciones;
    @Autowired
    private IConductorRepo conductores;
    @Autowired
    private IHistorialRepo historial;

    private static Logger LOG = LoggerFactory.getLogger(UbicacionRestController.class);

    @PostMapping(value = "/{codigoConductor}")
    public void insertar(@RequestBody Ubicacion ubicacion, @PathVariable("codigoConductor") String codigoConductor){

        Conductor conductor = conductores.findByCodigoConductor(codigoConductor);
        if (conductor==null) {
            new ConductorNotFoundException(" Conductor" + codigoConductor + "not found");
        }else {
            if(comprobarCoordenadas(ubicacion.getCoordenadaX(), ubicacion.getCoordenadaY())){
                Timestamp now = new Timestamp(System.currentTimeMillis());
                ubicacion.setFecha(now);
                ubicaciones.save(ubicacion);
                conductor.setUltimaUbicacion(ubicacion);
                conductores.save(conductor);
                añadirHistorial(conductor.getCodigoConductor(),ubicacion);
            } else{
                LOG.error("El formato de las coordenadas no es valido: " + ubicacion.getCoordenadaX() + " / " + ubicacion.getCoordenadaY());
                throw new UbicacionBadRequestException("El formato de las coordenadas no es valido" );
            }
        }
    }

    public boolean comprobarCoordenadas(String coorX, String coorY){
        coorX.replaceFirst(".","");
        coorY.replaceFirst(".","");

        if(coorX.matches("[+-]?\\d*(\\.\\d+)?") && coorY.matches("[+-]?\\d*(\\.\\d+)?")){
            return true;
        }else return false;
    }

    public void añadirHistorial(String codigoConductor, Ubicacion ubicacion){
        Historial h = new Historial(codigoConductor,ubicacion);
        historial.save(h);
    }
}
