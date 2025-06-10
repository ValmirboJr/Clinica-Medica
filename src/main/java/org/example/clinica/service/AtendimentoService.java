package org.example.clinica.service;

import org.example.clinica.dto.AtendimentoRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.model.Atendimento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AtendimentoService {

    Atendimento findById(UUID id) throws NotFoundException;
    List<Atendimento> findAll();
    List<Atendimento> CriarAtendimento(List<AtendimentoRequestDTO> dtoList, UUID idPaciente) throws NotFoundException;
    List<Atendimento> listarAtendimentos(String crm, LocalDate data, LocalTime hora) throws NotFoundException;
    List<Atendimento> listarAtendimentosPorEspecialidade(String Especialidade) throws NotFoundException;
    List<Atendimento> findByPaciente(UUID idPaciente) throws NotFoundException;
}
