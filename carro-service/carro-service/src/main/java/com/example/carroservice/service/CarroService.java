package com.example.carroservice.service;

import com.example.carroservice.entities.CarroEntity;
import com.example.carroservice.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroEntity> getAll(){
        return carroRepository.findAll();
    }

    public CarroEntity getCarroById(int id){
        return carroRepository.findById(id).orElse(null);
    }

    public CarroEntity save(CarroEntity carroEntity){
        CarroEntity nuevoCarro = carroRepository.save(carroEntity);
        return nuevoCarro;
    }


    //Obtener todos los carros de un usuario, mediante su id
    public List<CarroEntity> byUsuarioId(int usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }

}
