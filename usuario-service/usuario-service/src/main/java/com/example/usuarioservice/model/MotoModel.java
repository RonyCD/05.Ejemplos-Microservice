package com.example.usuarioservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotoModel {
    private String marca;
    private String modelo;
    private int usuarioId;
}
