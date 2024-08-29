package com.alura.foro.service;

import com.alura.foro.modelo.Respuesta;
import com.alura.foro.repository.RespuestaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository repository;


    public Respuesta setRespuesta (@Valid Respuesta respuesta){
        return repository.save(respuesta);
    }

    public List<Respuesta> getRespuestas (){
        return repository.findAll();
    }

    public void deleteRespuesta (Long id){
        repository.deleteById(id);
    }

    public void updateRespuesta (Respuesta respuesta){
        repository.save(respuesta);
    }

    public Optional<List<Respuesta>> getRespuestasPorTopico (Long id){
        return repository.findByTopico_id(id);
    }
}
