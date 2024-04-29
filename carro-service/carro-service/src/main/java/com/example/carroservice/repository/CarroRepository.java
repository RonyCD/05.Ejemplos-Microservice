package com.example.carroservice.repository;

import com.example.carroservice.entities.CarroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroRepository extends JpaRepository<CarroEntity, Integer> {

    List<CarroEntity> findByUsuarioId(int usuarioId);
}
