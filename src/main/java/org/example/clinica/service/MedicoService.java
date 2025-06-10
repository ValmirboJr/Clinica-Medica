package org.example.clinica.service;

import org.example.clinica.dto.MedicoRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.model.Medico;

import java.util.List;
import java.util.UUID;

public interface MedicoService {
    Medico findById(UUID idmedico);
    Medico getMedicoById(UUID idmedico) throws NotFoundException;
    Medico Create(MedicoRequestDTO medicoRequestDTO) throws NotFoundException;
    Medico Update(MedicoRequestDTO medicoRequestDTO, UUID idmedico) throws NotFoundException;
    void delete(String crm) throws NotFoundException;
    List<Medico> findAll();
    List<Medico> findByEspecialidade(String especialidade) throws NotFoundException;
}
