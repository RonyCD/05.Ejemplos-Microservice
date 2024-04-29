package com.example.carroservice.controller;

import com.example.carroservice.entities.CarroEntity;
import com.example.carroservice.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping()
    public ResponseEntity<List<CarroEntity>> listarCarros(){
        List<CarroEntity> carros = carroService.getAll();
        if(carros.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroEntity> obtenerCarroPorId(@PathVariable("id") int id){
        CarroEntity carro = carroService.getCarroById(id);
        if(carro == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carro);
    }


    @PostMapping()
    public ResponseEntity<CarroEntity> nuevoCarro(@RequestBody CarroEntity carroEntity){
        CarroEntity nuevoCarro = carroService.save(carroEntity);
        return ResponseEntity.ok(nuevoCarro);
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CarroEntity>> byUsuarioId(@PathVariable("usuarioId") int usuarioId){
        List<CarroEntity> carrosPorUsuarioId = carroService.byUsuarioId(usuarioId);
        if(carrosPorUsuarioId.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(carrosPorUsuarioId);
    }
}
