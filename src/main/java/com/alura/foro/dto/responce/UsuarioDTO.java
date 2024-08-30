package com.alura.foro.dto.responce;

import com.alura.foro.modelo.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private Long id;

    private String nombre;

    public UsuarioDTO (Usuario usuario){
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
