package com.alura.foro.repository;

import com.alura.foro.modelo.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface RespuestaRepository extends JpaRepository<Respuesta,Long> {

    Optional<List<Respuesta>> findByTopico_id(Long id);
}
