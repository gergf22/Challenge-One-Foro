package com.alura.foro.service;

import com.alura.foro.dto.request.UsuarioAuthDTO;
import com.alura.foro.dto.responce.UsuarioDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.security.TokenService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService  {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private BCryptPasswordEncoder encoder;

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


    public UsuarioDTO singup (Usuario usuario) throws BadRequestException {
        if (usuario.getId()== null){
            String codedPass = encoder.encode(usuario.getPassword());
            usuario.setPassword(codedPass);
            Usuario saveUser = repository.save(usuario);
            UsuarioDTO usuarioDTO = modelMapper.map(saveUser, UsuarioDTO.class);
            return usuarioDTO;
        } else {
        throw new BadRequestException("Usuario existente");
        }
    }



    public UsuarioAuthDTO logIn (Usuario usuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuario.getNombre(),usuario.getPassword());
        Authentication authUser = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken((Usuario) authUser.getPrincipal());
        UsuarioAuthDTO usuarioAuthDTO = new UsuarioAuthDTO((Usuario) authUser.getPrincipal(), token);
        return usuarioAuthDTO;
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
