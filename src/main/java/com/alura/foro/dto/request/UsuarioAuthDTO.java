package com.alura.foro.dto.request;

import com.alura.foro.dto.responce.UsuarioDTO;
import com.alura.foro.modelo.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioAuthDTO {

    private Long id;

    private String nombre;

    private String token;


    public UsuarioAuthDTO (Usuario usuario, String token){
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.token = token;
    }
}
