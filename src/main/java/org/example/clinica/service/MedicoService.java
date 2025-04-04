package org.example.clinica.service;

import org.example.clinica.dto.MedicoRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.model.Medico;

import java.util.UUID;

public interface MedicoService {

    Medico getMedicoById(UUID id) throws NotFoundException;
    Medico Create(MedicoRequestDTO medicoRequestDTO) throws NotFoundException;
    Medico Update(MedicoRequestDTO medicoRequestDTO) throws NotFoundException;
    void delete(MedicoRequestDTO medicoRequestDTO) throws NotFoundException;
}
