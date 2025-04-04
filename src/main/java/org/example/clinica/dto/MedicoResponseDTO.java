package org.example.clinica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicoResponseDTO {

    private UUID idmedico;
    private String nome;
    private String crm;
    private String especialidade;
    private String telefone;
}
