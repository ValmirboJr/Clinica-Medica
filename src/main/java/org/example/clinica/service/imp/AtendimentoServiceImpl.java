package org.example.clinica.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.AtendimentoRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Atendimento;
import org.example.clinica.model.Medico;
import org.example.clinica.model.Paciente;
import org.example.clinica.model.Usuario;
import org.example.clinica.repository.AtendimentoRepository;
import org.example.clinica.repository.MedicoRepository;
import org.example.clinica.repository.PacienteRepository;
import org.example.clinica.repository.UsuarioRepository;
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
    public List<Atendimento> CriarAtendimento(List<AtendimentoRequestDTO> atendimentoRequestDTOList, UUID idPaciente, UUID idmedico, LocalTime hora, LocalDate data, String sala) throws NotFoundException {
        List<Atendimento> atendimentosCriados = new ArrayList<>();

        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o ID: " + idPaciente));

        Medico medico = medicoRepository.findById(idmedico)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com o ID: " + idmedico));

        List<Atendimento> atendimentosExistentes = atendimentoRepository.findByMedicoAndHoraAndData(medico, hora, data);
        if (!atendimentosExistentes.isEmpty()) {
            throw new NotFoundException("Médico já possui atendimento agendado para este horário e data");
        }

        for (AtendimentoRequestDTO atendimentoDTO : atendimentoRequestDTOList) {
            Atendimento atendimento = new Atendimento();
            atendimento.setPaciente(paciente);
            atendimento.setMedico(medico);
            atendimento.setHora(hora);
            atendimento.setData(data);
            atendimento.setSala(sala);
            atendimento = atendimentoRepository.save(atendimento);
            atendimentosCriados.add(atendimento);
        }

        return atendimentosCriados;
    }

    @Override
    public List<Atendimento> listarAtendimentos(String crm, LocalTime hora,LocalDate data) {
        Optional<Medico> medico = medicoRepository.findByCrm(crm);
        if (medico.isEmpty()) {
            return Collections.emptyList();
        }
        return atendimentoRepository.findByMedicoAndHoraAndData(medico.get(), hora, data);
    }
}
