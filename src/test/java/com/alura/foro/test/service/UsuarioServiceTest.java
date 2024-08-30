package com.alura.foro.test.service;

import com.alura.foro.dto.responce.UsuarioDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.UsuarioService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService service;

    private static final Logger logger = Logger.getLogger(UsuarioServiceTest.class);


    @Test
    @Order(1)
    public void setUsuario (){

        Usuario usuario = new Usuario("german","Ger@ger","Qlala2@das");

        UsuarioDTO usuario2= null ;

        try{
            usuario2= service.setUsuario(usuario);
        }catch (BadRequestException e){
            logger.error(e.getMessage());
        };

        assertEquals(usuario.getNombre(),usuario2.getNombre());


    }

    @Test
    @Order(2)
    public void getUsuarios (){

        List<UsuarioDTO> usuarios = service.getUsuarios();

        System.out.println(usuarios);

        assertTrue(usuarios.size()==1);
    }

    @Test
    @Order(3)
    public void updateUsuarios (){

        Usuario usuario = new Usuario(1l,"Gergf22","ger@ger","Qlala2@das");

        service.updateUsuario(usuario);

        UsuarioDTO usuario2 = service.getUsuarios().get(0);

        assertEquals(usuario.getNombre(),usuario2.getNombre());

    }

    @Test
    @Order(4)
    public void deleteUsuario (){
        service.deleteUsuario(1l);

        List<UsuarioDTO> usuarios = service.getUsuarios();

        assertTrue(usuarios.size()==0);
    }

}
