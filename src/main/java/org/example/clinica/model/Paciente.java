package org.example.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "CD_PACIENTE")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
public class Paciente {

    @Id
    @GeneratedValue
    @Column(name = "ID_PACIENTE")
    private UUID id;

    @Column(name = "NOME_PACIENTE")
    private String nome;

    @Column(name = "CPF_PACIENTE")
    private String cpf;

    @Column(name = "ENDERECO_PACIENTE")
    private String endereco;

    @Column(name = "TELEFONE_PACIENTE")
    private String telefone;

}
