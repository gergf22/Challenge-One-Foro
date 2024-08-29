package com.alura.foro.service;

import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public Usuario saveUsuarios(@Valid Usuario usuario){
        return repository.save(usuario);
    }

    public List<Usuario> getUsuarios(){
        return repository.findAll();
    }

    public void deleteUsuario (Long id){
        repository.deleteById(id);
    }

    public void updateUsuario (Usuario usuario){
        repository.save(usuario);
    }

}
