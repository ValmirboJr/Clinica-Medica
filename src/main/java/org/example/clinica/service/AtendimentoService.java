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
    List<Atendimento> CriarAtendimento(List<AtendimentoRequestDTO> atendimentoRequestDTOList, UUID idUsuario, UUID idmedico, LocalTime hora,LocalDate data, String sala) throws NotFoundException;
    List<Atendimento> listarAtendimentos(String crm, LocalTime hora,LocalDate data) throws NotFoundException;
}
