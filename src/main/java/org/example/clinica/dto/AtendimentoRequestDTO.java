package org.example.clinica.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class AtendimentoRequestDTO {

    private List<AtendimentoRequestDTO> atendimentoRequestDTOList;
    private UUID idMedico;
    private LocalDate data;
    private LocalTime hora;
    private String sala;
}
