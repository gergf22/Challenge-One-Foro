package com.alura.foro.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "topicos")
public class Topico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank (message = "Debe ingresar un título")
	private String titulo;
	@NotBlank (message = "Debe ingresar un mensaje en su posteo")
	private String mensaje;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;
	@ManyToOne
	@JoinColumn (name = "usuario_id", referencedColumnName = "id")
	@NotNull
	private Usuario autor;

	@ManyToOne
	@JoinColumn(name = "curso_id", referencedColumnName = "id")
	@NotNull
	private Curso curso;
	@OneToMany(mappedBy = "topico",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Respuesta> respuestas = new HashSet<>();

	public Topico(String titulo, String mensaje, Curso curso, Usuario autor) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.autor = autor;
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topico other = (Topico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



}
