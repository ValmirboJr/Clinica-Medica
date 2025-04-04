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
public class PacienteResponseDTO {

    private UUID id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
}
