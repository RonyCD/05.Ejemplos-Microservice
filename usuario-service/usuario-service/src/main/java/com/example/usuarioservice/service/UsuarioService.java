package com.example.usuarioservice.service;

import com.example.usuarioservice.entities.UsuarioEntity;
import com.example.usuarioservice.feignclient.CarroFeignClient;
import com.example.usuarioservice.feignclient.MotoFeignClient;
import com.example.usuarioservice.model.CarroModel;
import com.example.usuarioservice.model.MotoModel;
import com.example.usuarioservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;


    public List<UsuarioEntity> getAll(){
        return usuarioRepository.findAll();
    }

    public UsuarioEntity getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public UsuarioEntity save(UsuarioEntity usuarioEntity){
        UsuarioEntity nuevoUsuario = usuarioRepository.save(usuarioEntity);
        return nuevoUsuario;
    }

    //REST TEMPLATE
    //Metodo para listar Carros por usuario
    public List<CarroModel> getCarros(int usuarioId){
        List<CarroModel> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    //Metodo para listar Motos por usuario
    public List<MotoModel> getMotos(int usuarioId){
        List<MotoModel> motos = restTemplate.getForObject("http://localhost:8003/moto/usuario/" + usuarioId, List.class);
        return motos;
    }


    // FEIGN
    public CarroModel saveCarro(int usuarioId, CarroModel carroModel){
        carroModel.setUsuarioId(usuarioId);
        CarroModel nuevoCarro = carroFeignClient.save(carroModel);
        return nuevoCarro;
    }

    public MotoModel saveMoto(int usuarioId, MotoModel motoModel){
        motoModel.setUsuarioId(usuarioId);
        MotoModel nuevoMoto = motoFeignClient.save(motoModel);
        return nuevoMoto;
    }


    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        UsuarioEntity usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario == null){
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("Mensaje", usuario);

        List<CarroModel> carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()) {
            resultado.put("Carros", "El usuario no tiene carros");
        }
        else{
            resultado.put("Carros", carros);
        }

        List<MotoModel> motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()) {
            resultado.put("Motos", "El usuario no tiene motos");
        }
        else{
            resultado.put("Motos", motos);
        }

        return resultado;
    }




}
