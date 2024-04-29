package com.example.motoservice.controller;

import com.example.motoservice.entities.MotoEntity;
import com.example.motoservice.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {


    @Autowired
    private MotoService motoService;

    @GetMapping()
    public ResponseEntity<List<MotoEntity>> listarMotos(){
        List<MotoEntity> motos = motoService.getAll();
        if(motos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoEntity> obtenerMotoPorId(@PathVariable("id") int id){
        MotoEntity moto = motoService.getMotoById(id);
        if(moto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(moto);
    }


    @PostMapping()
    public ResponseEntity<MotoEntity> nuevoMoto(@RequestBody MotoEntity motoEntity){
        MotoEntity nuevoMoto = motoService.save(motoEntity);
        return ResponseEntity.ok(nuevoMoto);
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MotoEntity>> byUsuarioId(@PathVariable("usuarioId") int usuarioId){
        List<MotoEntity> motosPorUsuarioId = motoService.byUsuarioId(usuarioId);
        if(motosPorUsuarioId.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(motosPorUsuarioId);
    }
}
