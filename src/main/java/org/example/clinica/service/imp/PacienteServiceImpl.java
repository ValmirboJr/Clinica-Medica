package org.example.clinica.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.PacienteRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Paciente;
import org.example.clinica.model.Usuario;
import org.example.clinica.repository.PacienteRepository;
import org.example.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    private final GenericMapper mapper;

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente getById(UUID idpaciente) throws NotFoundException {
        return pacienteRepository.findById(idpaciente).orElseThrow(()-> new NotFoundException("Não foi possível encontrar o usuário pelo id " + idpaciente));
    }


    @Override
    public Paciente Create(PacienteRequestDTO pacienteRequestDTO) throws NotFoundException {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteRequestDTO.getNome());
        paciente.setCpf(pacienteRequestDTO.getCpf());
        paciente.setEndereco(pacienteRequestDTO.getEndereco());
        paciente.setTelefone(pacienteRequestDTO.getTelefone());
        return pacienteRepository.save(mapper.dtoParaEntidade(pacienteRequestDTO,Paciente.class));
    }

    @Override
    public Paciente Update(PacienteRequestDTO pacienteRequestDTO, UUID idpaciente) throws NotFoundException {
        Paciente paciente = pacienteRepository.findById(idpaciente)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado para atualização"));

        paciente.setNome(pacienteRequestDTO.getNome());
        paciente.setCpf(pacienteRequestDTO.getCpf());
        paciente.setEndereco(pacienteRequestDTO.getEndereco());
        paciente.setTelefone(pacienteRequestDTO.getTelefone());

        return pacienteRepository.save(paciente);
    }
    

    @Override
    public void delete(UUID idpaciente) throws NotFoundException {
        Paciente paciente = pacienteRepository.findById(idpaciente)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o ID: " + idpaciente));
        pacienteRepository.delete(paciente);
    }
}
