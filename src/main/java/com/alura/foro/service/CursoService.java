package com.alura.foro.service;

import com.alura.foro.modelo.Curso;
import com.alura.foro.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;


    public List<Curso> getCursos (){
        return repository.findAll();
    }

    public Curso setCurso(@Valid Curso curso){
        return repository.save(curso);
    }

    public void deleteCurso (Long id){
        repository.deleteById(id);
    }

    public void updateCurso (Curso curso){
        repository.save(curso);
    }

}
