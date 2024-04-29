package com.example.usuarioservice.feignclient;

import com.example.usuarioservice.model.CarroModel;
import com.example.usuarioservice.model.MotoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carro-service", url="http://localhost:8002")
public interface CarroFeignClient {

    @PostMapping("/carro")
    public CarroModel save(@RequestBody CarroModel carroModel);

    @GetMapping("/carro/usuario/{usuarioId}")
    public List<CarroModel> getCarros(@PathVariable("usuarioId") int usuarioId);
}
