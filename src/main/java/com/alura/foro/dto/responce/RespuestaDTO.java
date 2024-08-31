package com.alura.foro.dto.responce;

import com.alura.foro.modelo.Respuesta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class RespuestaDTO {

    private Long id;

    private String mensaje;

    private TopicoDTO topico;

    private LocalDateTime fechaCreacion;

    private UsuarioDTO autor;

    private Boolean solucion;

    public RespuestaDTO(Respuesta respuesta) {
        this.id = respuesta.getId();
        this.mensaje = respuesta.getMensaje();
        this.topico = new TopicoDTO(respuesta.getTopico());
        this.fechaCreacion = respuesta.getFechaCreacion();
        this.autor = new UsuarioDTO(respuesta.getAutor());
        this.solucion = respuesta.getSolucion();
    }
}
