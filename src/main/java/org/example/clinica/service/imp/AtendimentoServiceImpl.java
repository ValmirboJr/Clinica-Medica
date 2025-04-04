package org.example.clinica.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.AtendimentoRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Atendimento;
import org.example.clinica.repository.AtendimentoRepository;
import org.example.clinica.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

    @Autowired
    private final AtendimentoRepository atendimentoRepository;

    private final GenericMapper mapper;


    @Override
    public Atendimento findById(UUID id_atendimento) throws NotFoundException {
        return atendimentoRepository.findById(id_atendimento).orElseThrow(() -> new NotFoundException("Não foi possível encontrar o seu atendimento pelo id " + id_atendimento));
    }

    @Override
    public List<Atendimento> findAll() {
        return atendimentoRepository.findAll();
    }


    @Override
    public List<Atendimento> CriarAtendimento(List<AtendimentoRequestDTO> atendimentoRequestDTOList, UUID idUsuario, UUID idmedico, LocalTime hora, LocalDate data, String sala) throws NotFoundException {
        return List.of();
    }

    @Override
    public List<Atendimento> listarAtendimentos(String crm, LocalTime hora,LocalDate data) {
        return List.of();
    }
}
