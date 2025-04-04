package org.example.clinica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoResponseDTO {

    private UUID id;
    private LocalDate data;
    private LocalTime hora;
    private String sala;
    private UUID idMedico;
    private String nomeMedico;
    private UUID idPaciente;
    private String nomePaciente;
}
