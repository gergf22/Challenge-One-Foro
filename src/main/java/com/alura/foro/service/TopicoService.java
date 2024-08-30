package com.alura.foro.service;

import com.alura.foro.dto.responce.TopicoDTO;
import com.alura.foro.modelo.Topico;
import com.alura.foro.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    public List<TopicoDTO> getTopicos (){
        List<Topico> topicos =  repository.findAll();

        return topicos.stream().map(TopicoDTO::new).collect(Collectors.toList());
    }

    public TopicoDTO setTopico (@Valid Topico topico){

        Topico saveTopic = repository.save(topico);
        TopicoDTO topicoDTO = modelMapper.map(saveTopic,TopicoDTO.class);
        return topicoDTO;
    }

    public void deleteTopico (Long id) throws EntityNotFoundException {
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Tópico no encontrado");
        }


    }

    public void updateTopico (Topico topico) throws EntityNotFoundException{
        if(repository.findById(topico.getId()).isPresent()) {
            repository.save(topico);
        }else {
            throw new EntityNotFoundException("Tópico a acutlaizar no encontrado");
        }
    }

    public TopicoDTO getTopicoById (Long id) throws EntityNotFoundException {
        Topico topico = repository.findById(id).orElseThrow();
        TopicoDTO topicoDTO = modelMapper.map(topico,TopicoDTO.class);
        return topicoDTO;
    }



}
