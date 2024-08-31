package com.alura.foro.dto.responce;

import com.alura.foro.modelo.Curso;
import com.alura.foro.modelo.StatusTopico;
import com.alura.foro.modelo.Topico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TopicoDTO {

    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private StatusTopico statusTopico;

    private UsuarioDTO autor;

    private Curso curso;

    public TopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensaje = topico.getMensaje();
        this.fechaCreacion = topico.getFechaCreacion();
        this.statusTopico = topico.getStatus();
        this.autor = new UsuarioDTO(topico.getAutor());
        this.curso = topico.getCurso();
    }
}
