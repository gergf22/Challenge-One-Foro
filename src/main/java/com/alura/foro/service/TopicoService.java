package com.alura.foro.service;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Respuesta;
import com.alura.foro.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;


    public List<Topico> getTopicos (){
        return repository.findAll();
    }

    public Topico saveTopico (@Valid Topico topico){
        return repository.save(topico);
    }

    public void deleteTopico (Long id){
        repository.deleteById(id);
    }

    public void updateTopico (Topico topico){
        repository.save(topico);
    }

    public Optional<Topico> getTopicoById (Long id){
        return repository.findById(id);
    }

    public Set<Respuesta> getRespuestasTopico (long id) {
        return repository.findById(id).get().getRespuestas();
    }
}
