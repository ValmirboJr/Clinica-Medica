package org.example.clinica.service;

import org.example.clinica.dto.PacienteRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.model.Paciente;

import java.util.List;
import java.util.UUID;

public interface PacienteService {
    List<Paciente> findAll();
    Paciente findById(UUID id) throws NotFoundException;
    Paciente findByNome(String nome) throws NotFoundException;
    Paciente Create(PacienteRequestDTO pacienteRequestDTO) throws NotFoundException;
    Paciente Update(PacienteRequestDTO pacienteRequestDTO) throws NotFoundException;
    void delete(UUID id) throws NotFoundException;
}
