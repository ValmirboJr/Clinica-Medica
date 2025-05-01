package org.example.clinica.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.MedicoRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Medico;
import org.example.clinica.repository.MedicoRepository;
import org.example.clinica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private final MedicoRepository medicoRepository;

    private final GenericMapper mapper;

    @Override
    public Medico findById(UUID idmedico) {
        return medicoRepository.findById(idmedico).orElse(null);
    }

    @Override
    public Medico getMedicoById(UUID idmedico) throws NotFoundException {
        return medicoRepository.findById(idmedico)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com ID: " + idmedico));
    }

    @Override
    public Medico Create(MedicoRequestDTO medicoRequestDTO) throws NotFoundException {
        Medico medico = new Medico();
        medico.setNome(medicoRequestDTO.getNome());
        medico.setCrm(medicoRequestDTO.getCrm());
        medico.setEspecialidade(medicoRequestDTO.getEspecialidade());
        medico.setTelefone(medicoRequestDTO.getTelefone());
        return medicoRepository.save(mapper.dtoParaEntidade(medicoRequestDTO, Medico.class));
    }

    @Override
    public Medico Update(MedicoRequestDTO medicoRequestDTO, UUID idmedico) throws NotFoundException {
        Medico medico = medicoRepository.findById(idmedico)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o médico pelo id " + idmedico));
        medico.setNome(medicoRequestDTO.getNome());
        medico.setCrm(medicoRequestDTO.getCrm());
        medico.setEspecialidade(medicoRequestDTO.getEspecialidade());
        medico.setTelefone(medicoRequestDTO.getTelefone());
        return medicoRepository.save(medico);
    }

    @Override
    public void delete(String crm) throws NotFoundException {
        Medico medico = medicoRepository.findByCrm(crm)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado com CRM: " + crm));
        medicoRepository.delete(medico);
    }

    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }
}