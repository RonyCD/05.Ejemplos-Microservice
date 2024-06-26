package com.example.usuarioservice.feignclient;

import com.example.usuarioservice.model.MotoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto-service", url="http://localhost:8003")
public interface MotoFeignClient {

    @PostMapping("/moto")
    public MotoModel save(@RequestBody MotoModel motoModel);

    @GetMapping("/moto/usuario/{usuarioId}")
    public List<MotoModel> getMotos(@PathVariable("usuarioId") int usuarioId);

}
