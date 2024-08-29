package com.alura.foro.test.service;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Respuesta;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.CursoService;
import com.alura.foro.service.RespuestaService;
import com.alura.foro.service.TopicoService;
import com.alura.foro.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RespuestaServiceTest {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private RespuestaService respuestaService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private UsuarioService usuarioService;

    @Test
    @Order(1)
    public void setRespuesta(){

        Curso curso = cursoService.setCurso(new Curso("Back End 1", "Web Dev"));
        Usuario usuario = usuarioService.saveUsuarios(new Usuario("Ger","ger@ger","Qlala2@das"));
        Topico topico = topicoService.saveTopico(new Topico("Como configurar Spring","estos son los pasos para configurar Spring",curso,usuario));

        Respuesta respuesta = new Respuesta("Falto el paso 2",topico,usuario);

        Respuesta respuesta1 = respuestaService.setRespuesta(respuesta);

        assertEquals(respuesta1.getId(),1l);
    }

    @Test
    @Order(2)
    public void getRespuesta () {
        List<Respuesta> respuestas = respuestaService.getRespuestas();

        assertTrue(respuestas.size()==1);


    }

    @Test
    @Order(3)
    public void updateRespuesta () {
        Respuesta respuesta = new Respuesta(1l,"falto el paso 3",topicoService.getTopicos().get(0), usuarioService.getUsuarios().get(0));

        respuestaService.updateRespuesta(respuesta);

        Respuesta respuesta1 = respuestaService.getRespuestas().get(0);

        assertEquals(respuesta1.getMensaje(),respuesta.getMensaje());
    }


    @Test
    @Order(4)
    public void getRespuestasPorTopico (){

        Topico topico = topicoService.getTopicoById(1l).get();
        Topico topico2 = new Topico("topico2","es otro topico",cursoService.getCursos().get(0),usuarioService.getUsuarios().get(0));
        topicoService.saveTopico(topico2);

        Respuesta respuesta = new Respuesta("Respuesta 2",topico,usuarioService.getUsuarios().get(0));

        Respuesta respuesta1 = new Respuesta("respuesta 3", topico2, usuarioService.getUsuarios().get(0));

        respuestaService.setRespuesta(respuesta);
        respuestaService.setRespuesta(respuesta1);

        System.out.println(respuestaService.getRespuestasPorTopico(1l).get());
        System.out.println(respuestaService.getRespuestas());

        assertNotEquals(respuestaService.getRespuestas().size(),respuestaService.getRespuestasPorTopico(1l).get().size());
    }

    @Test
    @Order(5)
    public void deleteRespuesta () {
        respuestaService.deleteRespuesta(1l);

        List<Respuesta> respuestas = respuestaService.getRespuestas();

        assertTrue(respuestas.size()==2);
    }


}
