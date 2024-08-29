package com.alura.foro.test.service;

import com.alura.foro.modelo.Usuario;
import com.alura.foro.service.UsuarioService;
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


    @Test
    @Order(1)
    public void setUsuario (){

        Usuario usuario = new Usuario("german","Ger@ger","Qlala2@das");

        Usuario usuario2 = service.saveUsuarios(usuario);

        System.out.println(usuario);
        System.out.println(usuario2);

        assertEquals(usuario.getNombre(),usuario2.getNombre());


    }

    @Test
    @Order(2)
    public void getUsuario (){

        List<Usuario> usuarios = service.getUsuarios();

        System.out.println(usuarios);

        assertTrue(usuarios.size()==1);
    }

    @Test
    @Order(3)
    public void updateUsuarios (){

        Usuario usuario = new Usuario(1l,"Gergf22","ger@ger","Qlala2@das");

        service.updateUsuario(usuario);

        Usuario usuario2 = service.getUsuarios().get(0);

        assertEquals(usuario.getNombre(),usuario2.getNombre());

    }

    @Test
    @Order(4)
    public void deleteUsuario (){
        service.deleteUsuario(1l);

        List<Usuario> usuarios = service.getUsuarios();

        assertTrue(usuarios.size()==0);
    }

}
