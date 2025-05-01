package org.example.clinica.mapper;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.AtendimentoResponseDTO;
import org.example.clinica.model.Atendimento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenericMapperImpl implements GenericMapper {

    private final ModelMapper mapper;

    @PostConstruct
    public void setupMappings() {
        mapper.typeMap(Atendimento.class, AtendimentoResponseDTO.class)
                .addMappings(m -> {
                    m.map(src -> src.getMedico().getIdmedico(),     AtendimentoResponseDTO::setIdMedico);
                    m.map(src -> src.getMedico().getNome(),   AtendimentoResponseDTO::setNomeMedico);
                    m.map(src -> src.getPaciente().getIdpaciente(),   AtendimentoResponseDTO::setIdPaciente);
                    m.map(src -> src.getPaciente().getNome(), AtendimentoResponseDTO::setNomePaciente);
                });
    }

    public <T> T entidadeParaDTO(Object entidade, Class<T> dto){
        return mapper.map(entidade, dto);
    }

    public <T> T dtoParaEntidade(Object dto, Class<T> entidade){
        return mapper.map(dto, entidade);
    }

    public <T> List<T> entidadeParaDTO(List<?> entidades, Class<T> dto){
        return entidades.stream().map(entidade -> mapper.map(entidade, dto)).collect(Collectors.toList());
    }

}
