package com.alura.foro.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "respuestas")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String mensaje;
	@ManyToOne
	@JoinColumn(name = "topico_id",referencedColumnName = "id")
	@NotNull
	private Topico topico;
	private LocalDateTime fechaCreacion = LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name = "Usuario_id",referencedColumnName = "id")
	@NotNull
	private Usuario autor;
	private Boolean solucion = false;


	public Respuesta(String mensaje, Topico topico, Usuario autor) {
		this.mensaje = mensaje;
		this.topico = topico;
		this.autor = autor;
	}

	public Respuesta(Long id, String mensaje, Topico topico, Usuario autor) {
		this.id = id;
		this.mensaje = mensaje;
		this.topico = topico;
		this.autor = autor;
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
		Respuesta other = (Respuesta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
