package com.alura.foro.controller;

import com.alura.foro.dto.responce.TopicoDTO;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoDTO> saveTopico (@RequestBody @Valid Topico topico){
        return ResponseEntity.ok(topicoService.setTopico(topico));
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> getTopicos (){
        return ResponseEntity.ok(topicoService.getTopicos());
    }

    @PutMapping
    public ResponseEntity<String> updateTopico(@RequestBody @Valid Topico topico){
        topicoService.updateTopico(topico);
        return ResponseEntity.ok("Se ha actualizado el tópico con éxito.");
    }

    @DeleteMapping(path = "/id")
    public ResponseEntity<String> deleteTopico(@PathVariable Long id){
        topicoService.deleteTopico(id);
        return ResponseEntity.ok("Se ha eliminado el tópico con éxito.");
    }

    @GetMapping(path = "/id")
    public ResponseEntity<TopicoDTO> getTopicoById (@PathVariable Long id){
        return ResponseEntity.ok(topicoService.getTopicoById(id));
    }


}
