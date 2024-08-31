package com.alura.foro.test.service;

import com.alura.foro.dto.responce.TopicoDTO;
import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.Topico;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.repository.CursoRepository;
import com.alura.foro.repository.UsuarioRepository;
import com.alura.foro.service.TopicoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TopicoServiceTest {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;




    @Test
    @Order(1)
    public void addTopicoCorrectData (){
        Curso curso = cursoRepository.save(new Curso("Back-End","Web"));

        Usuario usuario = usuarioRepository.save(new Usuario("german","Ger@ger","Qlala2@das"));

        Topico topico = new Topico("ayuda con java","no puedo cargar los test",curso,usuario);

        TopicoDTO topico1 = topicoService.setTopico(topico);

        assertEquals(topico1.getAutor().getNombre() + topico1.getCurso().getNombre() ,
                topico.getAutor().getNombre() + topico.getCurso().getNombre());

    }

    @Test
    @Order(2)
    public void getTopicosTest (){

        List<TopicoDTO> topicos = topicoService.getTopicos();


        assertTrue(topicos.size()==1);
    }



    @Test
    @Order(3)
    public void findTopicoById (){
        String tituloEsperado = "ayuda con java";

        TopicoDTO topico = topicoService.getTopicoById(1l);

        assertEquals(topico.getTitulo(),tituloEsperado);
    }

    @Test
    @Order(4)
    public void updateTopicoCorrectData (){

        Usuario usuario= usuarioRepository.findById(1l).get();
        Curso curso = cursoRepository.findById(1L).get();
        Topico topicoActualizado = new Topico("Por favor ayuda con java","no puedo cargar los test",curso,usuario);
        topicoActualizado.setId(1l);


        TopicoDTO topicoGuardado = topicoService.getTopicoById(1l);

        topicoService.updateTopico(topicoActualizado);

        TopicoDTO topicoNuevo =topicoService.getTopicoById(1l);

        assertNotEquals(topicoGuardado.getTitulo(),topicoNuevo.getTitulo());

    }

    @Test
    @Order(5)
    public void deleteTopico (){
        topicoService.deleteTopico(1l);

        assertTrue(topicoService.getTopicos().size()==0);
    }


}
