package com.example.motoservice.service;

import com.example.motoservice.entities.MotoEntity;
import com.example.motoservice.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<MotoEntity> getAll(){
        return motoRepository.findAll();
    }

    public MotoEntity getMotoById(int id){
        return motoRepository.findById(id).orElse(null);
    }

    public MotoEntity save(MotoEntity motoEntity){
        MotoEntity nuevaMoto = motoRepository.save(motoEntity);
        return nuevaMoto;
    }


    //Obtener todos los motos de un usuario, mediante su id
    public List<MotoEntity> byUsuarioId(int usuarioId){
        return motoRepository.findByUsuarioId(usuarioId);
    }
}
