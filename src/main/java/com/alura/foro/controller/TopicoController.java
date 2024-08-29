package com.alura.foro.controller;

import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.TopicoService;
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
    public ResponseEntity<Topico> saveTopico (@RequestBody Topico topico){
        return ResponseEntity.ok(topicoService.saveTopico(topico));
    }

    @GetMapping
    public ResponseEntity<List<Topico>> getTopicos (){
        return ResponseEntity.ok(topicoService.getTopicos());
    }


}
