package com.alura.foro.controller;

import com.alura.foro.modelo.Curso;
import com.alura.foro.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @PostMapping
    public ResponseEntity<Curso> saveCurso (@RequestBody Curso curso){
        return ResponseEntity.ok(cursoService.setCurso(curso));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> getCursos (){
        return ResponseEntity.ok(cursoService.getCursos());
    }

    @PutMapping
    public ResponseEntity<String> updateCurso (@RequestBody Curso curso){
        cursoService.updateCurso(curso);
        return ResponseEntity.ok("Curso actualizado con éxito");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCurso (@PathVariable Long id){
        cursoService.deleteCurso(id);
        return ResponseEntity.ok("Curso eliminado con éxito");
    }


}
