package com.alura.foro.test.service;

import com.alura.foro.dto.responce.RespuestaDTO;
import com.alura.foro.dto.responce.UsuarioDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.*;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.service.CursoService;
import com.alura.foro.service.RespuestaService;
import com.alura.foro.service.TopicoService;
import com.alura.foro.service.UsuarioService;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RespuestaServiceTest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaService respuestaService;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Order(1)
    public void setRespuesta(){

        Curso curso = cursoRepository.save(new Curso("Back End 1", "Web Dev"));
        Usuario usuario = usuarioRepository.save(new Usuario("Ger","ger@ger","Qlala2@das"));

        Topico topico = topicoRepository.save(new Topico("Como configurar Spring","estos son los pasos para configurar Spring",curso,usuario));

        Respuesta respuesta = new Respuesta("Falto el paso 2",topico,usuario);

        RespuestaDTO respuestaDTO = respuestaService.setRespuesta(respuesta);

        assertTrue(respuestaDTO.getTopicoDTO().getStatusTopico() == StatusTopico.NO_SOLUCIONADO );
    }

    @Test
    @Order(2)
    public void getRespuesta () {
        List<RespuestaDTO> respuestas = respuestaService.getRespuestas();

        assertTrue(respuestas.size()==1);


    }

    @Test
    @Order(3)
    public void updateRespuesta () {

        Topico topico = topicoRepository.findById(1l).get();
        Respuesta respuesta = new Respuesta(1l,"falto el paso 3",topico, topico.getAutor());

        respuestaService.updateRespuesta(respuesta);

        RespuestaDTO respuestaActualizada = respuestaService.getRespuestas().get(0);

        assertEquals(respuestaActualizada.getMensaje(),respuesta.getMensaje());
    }


    @Test
    @Order(4)
    public void getRespuestasPorTopico (){

        Topico topico = topicoRepository.findById(1l).get();
        Usuario usuario = topico.getAutor();
        Curso curso = topico.getCurso();
        Topico topico2 = new Topico("topico2","es otro topico",curso,usuario);
        topicoRepository.save(topico2);

        Respuesta respuesta = new Respuesta("Respuesta 2",topico,usuario);

        Respuesta respuesta1 = new Respuesta("respuesta 3", topico2, usuario);

        respuestaService.setRespuesta(respuesta);
        respuestaService.setRespuesta(respuesta1);

        System.out.println(respuestaService.getRespuestasPorTopico(1l));
        System.out.println(respuestaService.getRespuestas());

        assertNotEquals(respuestaService.getRespuestas().size(),respuestaService.getRespuestasPorTopico(1l).size());
    }

    @Test
    @Order(5)
    public void deleteRespuesta () {
        respuestaService.deleteRespuesta(1l);

        List<RespuestaDTO> respuestas = respuestaService.getRespuestas();

        assertTrue(respuestas.size()==2);
    }

}
