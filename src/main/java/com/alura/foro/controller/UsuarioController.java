package com.alura.foro.controller;


import com.alura.foro.dto.responce.UsuarioDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDTO> guardarUsuario (@RequestBody @Valid Usuario usuario) throws BadRequestException {
        return ResponseEntity.ok(usuarioService.setUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> traerUsuarios (){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById (@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.findUserById(id));
    }


    @PutMapping
    public ResponseEntity<String> updateUsuario(@RequestBody @Valid Usuario usuario){
        usuarioService.updateUsuario(usuario);
        return ResponseEntity.ok("Usuario actualizado con éxito");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUsuario (@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }

}
