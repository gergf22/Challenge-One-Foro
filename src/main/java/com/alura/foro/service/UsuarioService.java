package com.alura.foro.service;

import com.alura.foro.dto.responce.UsuarioDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO setUsuario (Usuario usuario)throws BadRequestException {
        if (usuario.getId()== null){
            Usuario saveUser = repository.save(usuario);
            UsuarioDTO usuarioDTO = modelMapper.map(saveUser, UsuarioDTO.class);
            return usuarioDTO;
        } else {
            throw new BadRequestException("Usuario existente");
        }

    }


    public List<UsuarioDTO> getUsuarios(){

        List<Usuario> usuarios = repository.findAll();

        return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());


    }

    public void deleteUsuario (Long id) throws EntityNotFoundException {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Usuario no encontrado");
        }



    }

    public UsuarioDTO findUserById (Long id) {
            Usuario usuario = repository.findById(id).orElseThrow();
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
            return usuarioDTO;
        }

    public void updateUsuario (Usuario usuario)throws EntityNotFoundException {
        if (repository.findById(usuario.getId()).isPresent()) {
            repository.save(usuario);
        } else {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }
}
