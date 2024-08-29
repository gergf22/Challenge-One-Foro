package com.alura.foro.test.service;
import com.alura.foro.modelo.Curso;
import com.alura.foro.service.CursoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursoServiceTest {

    @Autowired
    private CursoService service;


    @Test
    @Order(1)
    public void setCurso (){

        Curso curso = new Curso("Infraestructura 2", "Infra");

        Curso curso1 = service.setCurso(curso);

        assertEquals(curso1.getId(),1l);
    }

    @Test
    @Order(2)
    public void getCurso(){
        List<Curso> cursos = service.getCursos();

        assertTrue(cursos.size()==1);
    }

    @Test
    @Order(3)
    public void updateCurso (){

        Curso curso = new Curso(1l,"Infraestructura 2","Taller");

        service.updateCurso(curso);

        Curso curso1 = service.getCursos().get(0);

        assertEquals(curso.getCategoria(),curso1.getCategoria());
    }

    @Test
    @Order(4)
    public void deleteCurso(){

        service.deleteCurso(1l);

        List<Curso> cursos = service.getCursos();

        assertTrue(cursos.size()==0);
    }
}



