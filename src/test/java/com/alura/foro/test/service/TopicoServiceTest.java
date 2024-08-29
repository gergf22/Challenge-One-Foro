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
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TopicoServiceTest {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RespuestaService respuestaService;



    @Test
    @Order(1)
    public void addTopicoCorrectData (){
        Curso curso = new Curso("Back-End","Web");

        Usuario usuario = new Usuario("german","Ger@ger","Qlala2@das");

        Curso curso1= cursoService.setCurso(curso);

        Usuario usuario1 = usuarioService.saveUsuarios(usuario);

        Topico topico = new Topico("ayuda con java","no puedo cargar los test",curso1,usuario1);

        Topico topico1 = topicoService.saveTopico(topico);

        assertEquals(topico1.getAutor(),topico.getAutor());
        assertEquals(topico1.getCurso(),topico.getCurso());


    }

    @Test
    @Order(2)
    public void getTopicosTest (){

        List<Topico> topicos = topicoService.getTopicos();


        assertTrue(topicos.size()==1);
    }



    @Test
    @Order(3)
    public void findTopicoById (){
        String tituloEsperado = "ayuda con java";

        Optional<Topico> topico = topicoService.getTopicoById(1l);

        assertEquals(topico.get().getTitulo(),tituloEsperado);
    }

    @Test
    @Order(4)
    public void updateTopicoCorrectData (){

        Usuario usuario= usuarioService.getUsuarios().get(0);
        Curso curso = cursoService.getCursos().get(0);
        Topico topicoActualizado = new Topico("Por favor ayuda con java","no puedo cargar los test",curso,usuario);
        topicoActualizado.setId(1l);

        Optional<Topico> topicoGuardado = topicoService.getTopicoById(1l);

        topicoService.updateTopico(topicoActualizado);

        Optional<Topico> topicoNuevo =topicoService.getTopicoById(1l);

        assertNotEquals(topicoGuardado.get().getTitulo(),topicoNuevo.get().getTitulo());

    }

    @Test
    @Order(5)
    public void getRespuestasTopico (){
        Respuesta respuesta = respuestaService.setRespuesta(
                new Respuesta("fijate las carpetas",topicoService.getTopicos().get(0),usuarioService.getUsuarios().get(0))
        );

        Respuesta respuesta2 = respuestaService.setRespuesta(
                new Respuesta("Acomoda la carpeta test",topicoService.getTopicos().get(0),usuarioService.getUsuarios().get(0))
        );


        Set<Respuesta> respuestasTopico = topicoService.getRespuestasTopico(1l);

        assertTrue(respuestasTopico.size()==2);
    }
}
