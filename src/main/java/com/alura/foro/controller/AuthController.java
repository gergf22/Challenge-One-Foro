package com.alura.foro.controller;

import com.alura.foro.dto.request.UsuarioAuthDTO;
import com.alura.foro.dto.responce.UsuarioDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService service;


    @PostMapping("/singup")
    public ResponseEntity<UsuarioDTO> singup (@Valid @RequestBody Usuario usuario) throws BadRequestException {
        return ResponseEntity.ok(service.singup(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioAuthDTO> login (@Valid @RequestBody Usuario usuario){
        return ResponseEntity.ok(service.logIn(usuario));
    }


}
