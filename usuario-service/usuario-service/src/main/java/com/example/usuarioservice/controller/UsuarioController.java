package com.example.usuarioservice.controller;

import com.example.usuarioservice.entities.UsuarioEntity;
import com.example.usuarioservice.model.CarroModel;
import com.example.usuarioservice.model.MotoModel;
import com.example.usuarioservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios(){
        List<UsuarioEntity> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> obtenerUsuarioPorId(@PathVariable("id") int id){
        UsuarioEntity usuario = usuarioService.getUsuarioById(id);
        if(usuario == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }


    @PostMapping()
    public ResponseEntity<UsuarioEntity> nuevoUsuario(@RequestBody UsuarioEntity usuarioEntity){
        UsuarioEntity nuevoUsuario = usuarioService.save(usuarioEntity);
        return ResponseEntity.ok(nuevoUsuario);
    }

    //REST TEMPLATE
    //Obtener lista de Carros por ID del usuario
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<CarroModel>> listarCarros(@PathVariable("usuarioId") int usuarioId){
        UsuarioEntity usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null)
            return ResponseEntity.notFound().build();
        List<CarroModel> carros = usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carros);
    }

    //Obtener lista de Motos por ID del usuario
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<MotoModel>> listarMotos(@PathVariable("usuarioId") int usuarioId){
        UsuarioEntity usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null)
            return ResponseEntity.notFound().build();
        List<MotoModel> motos = usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }


    // FEIGN
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<CarroModel> guardarCarro(@PathVariable("usuarioId") int usuarioId, @RequestBody CarroModel carroModel){
        CarroModel nuevoCarro = usuarioService.saveCarro(usuarioId, carroModel);
        return ResponseEntity.ok(nuevoCarro);
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<MotoModel> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody MotoModel motoModel){
        MotoModel nuevoMoto = usuarioService.saveMoto(usuarioId, motoModel);
        return ResponseEntity.ok(nuevoMoto);
    }

    @GetMapping("/todosa/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosVehiculos11(@PathVariable("usuarioId") int usuarioId){
        Map<String, Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }


    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosVehiculos(@PathVariable("usuarioId") int usuarioId) {
        try {
            Map<String, Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            // Manejo de excepciones
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al obtener los vehículos del usuario.");
            errorResponse.put("message", e.getMessage()); // Opcional: puedes incluir el mensaje de la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


}
