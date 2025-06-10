package org.example.clinica.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.AtendimentoRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Atendimento;
import org.example.clinica.model.Medico;
import org.example.clinica.model.Paciente;
import org.example.clinica.repository.AtendimentoRepository;
import org.example.clinica.repository.MedicoRepository;
import org.example.clinica.repository.PacienteRepository;
import org.example.clinica.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

    @Autowired
    private final AtendimentoRepository atendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private final GenericMapper mapper;

    @Autowired
    private MedicoRepository medicoRepository;


    @Override
    public Atendimento findById(UUID id_atendimento) throws NotFoundException {
        return atendimentoRepository.findById(id_atendimento).orElseThrow(() -> new NotFoundException("Não foi possível encontrar o seu atendimento pelo id " + id_atendimento));
    }

    @Override
    public List<Atendimento> findAll() {
        return atendimentoRepository.findAll();
    }

    @Override
    public List<Atendimento> CriarAtendimento(List<AtendimentoRequestDTO> dtoList, UUID idPaciente) throws NotFoundException {
        List<Atendimento> criados = new ArrayList<>();
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado: " + idPaciente));

        for (AtendimentoRequestDTO dto : dtoList) {
            UUID idMedico = dto.getIdMedico();
            LocalTime hora = dto.getHora();
            LocalDate data = dto.getData();
            String sala = dto.getSala();

            Medico medico = medicoRepository.findById(idMedico)
                    .orElseThrow(() -> new NotFoundException("Médico não encontrado: " + idMedico));

            if (!atendimentoRepository
                    .findByMedicoAndHoraAndData(medico, data, hora)
                    .isEmpty()) {
                throw new NotFoundException(
                        "Médico já agendado em " + data + " " + hora);
            }

            Atendimento at = new Atendimento();
            at.setPaciente(paciente);
            at.setMedico(medico);
            at.setHora(hora);
            at.setData(data);
            at.setSala(sala);
            criados.add(atendimentoRepository.save(at));
        }

        return criados;
    }


    @Override
    public List<Atendimento> listarAtendimentos(String crm, LocalDate data, LocalTime hora) {
        Medico medico = medicoRepository.findByCrm(crm)
                .orElseThrow(() ->
                        new NotFoundException("Médico não encontrado com o CRM: " + crm)
                );
        return atendimentoRepository.findByMedicoAndHoraAndData(medico, data, hora);
    }

    @Override
    public List<Atendimento> findByPaciente(UUID idPaciente) throws NotFoundException {
        pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado: " + idPaciente));

        return atendimentoRepository.findByPaciente_Id(idPaciente);
    }

    @Override
    public List<Atendimento> listarAtendimentosPorEspecialidade(String especialidade) {
        List<Atendimento> lista = atendimentoRepository.findByMedicoEspecialidade(especialidade);
        if (lista.isEmpty()) {
            throw new NotFoundException("Nenhum atendimento encontrado para especialidade: " + especialidade);
        }
        return lista;
    }
}