package com.example.motoservice.repository;

import com.example.motoservice.entities.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<MotoEntity, Integer> {

    List<MotoEntity> findByUsuarioId(int usuarioId);
}
