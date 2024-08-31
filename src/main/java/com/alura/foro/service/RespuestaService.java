package com.alura.foro.service;

import com.alura.foro.dto.responce.RespuestaDTO;
import com.alura.foro.exceptions.BadRequestException;
import com.alura.foro.modelo.Respuesta;
import com.alura.foro.modelo.Topico;
import com.alura.foro.repository.RespuestaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository repository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private ModelMapper modelMapper;


    public RespuestaDTO setRespuesta (Respuesta respuesta)  {
        Topico topico = respuesta.getTopico();

        topicoService.validarStatusRespondido(topico);

        Respuesta saveResp = repository.save(respuesta);
        RespuestaDTO respuestaDTO = modelMapper.map(saveResp,RespuestaDTO.class);
        return respuestaDTO;


    }

    public List<RespuestaDTO> getRespuestas (){
       List<Respuesta> respuestas = repository.findAll();
       return respuestas.stream().map(RespuestaDTO::new).collect(Collectors.toList());
    }

    public void deleteRespuesta (Long id) throws EntityNotFoundException {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Respuesta no encontrada");
        }

    }

    public void updateRespuesta (Respuesta respuesta) throws EntityNotFoundException{

        if (repository.findById(respuesta.getId()).isPresent()){
            repository.save(respuesta);
        } else {
            throw new EntityNotFoundException("Respuesta no encontrada");
        }

    }

    public List<RespuestaDTO> getRespuestasPorTopico (Long id) throws EntityNotFoundException{

        if(topicoService.getTopicoById(id) != null){
            List<Respuesta> respuestas = repository.findByTopico_id(id).get();
            return respuestas.stream().map(RespuestaDTO::new).collect(Collectors.toList());

        }else {
            throw new EntityNotFoundException("Topico buscado no existe");
        }


    }
}
