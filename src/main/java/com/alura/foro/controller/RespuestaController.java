package com.alura.foro.controller;

import com.alura.foro.dto.responce.RespuestaDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.Respuesta;
import com.alura.foro.service.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService service;

    @PostMapping
    public ResponseEntity<RespuestaDTO> setRespuesta(@RequestBody @Valid Respuesta respuesta) {

        return ResponseEntity.ok(service.setRespuesta(respuesta));
    }

    @GetMapping
    public ResponseEntity<List<RespuestaDTO>> getRespuestas() {
        return ResponseEntity.ok(service.getRespuestas());
    }


    @GetMapping(path = "/topico-{id}")
    public ResponseEntity<List<RespuestaDTO>> getRespuestasPorTopico(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRespuestasPorTopico(id));
    }


    //Todavía no actualiza la hora de edición, proximamnente lo hara.
    @PutMapping
    public ResponseEntity<String> updateRespuesta(@RequestBody @Valid Respuesta respuesta) {
        service.updateRespuesta(respuesta);
        return ResponseEntity.ok("Se ha editado la respuesta con exito");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteRespuesta (@PathVariable Long id){
        service.deleteRespuesta(id);
        return ResponseEntity.ok("Se ha eliminado la respuesta con éxito");
    }




}
