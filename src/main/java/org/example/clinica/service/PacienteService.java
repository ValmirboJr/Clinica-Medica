package org.example.clinica.service;

import org.example.clinica.dto.PacienteRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.model.Paciente;

import java.util.List;
import java.util.UUID;

public interface PacienteService {
    List<Paciente> findAll();
    Paciente getById(UUID id) throws NotFoundException;
    Paciente Create(PacienteRequestDTO pacienteRequestDTO) throws NotFoundException;
    Paciente Update(PacienteRequestDTO pacienteRequestDTO, UUID idpaciente) throws NotFoundException;
    void delete(UUID id) throws NotFoundException;
}
