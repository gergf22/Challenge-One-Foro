package com.alura.foro.controller;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.UsuarioService;
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
    public ResponseEntity<Usuario> guardarUsuario (@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.saveUsuarios(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> traerUsuarios (){
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }


    @PutMapping
    public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario){
        usuarioService.updateUsuario(usuario);
        return ResponseEntity.ok("Usuario actualizado con éxito");
    }

    @DeleteMapping(path = "/id")
    public ResponseEntity<String> deleteUsuario (@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }

}
