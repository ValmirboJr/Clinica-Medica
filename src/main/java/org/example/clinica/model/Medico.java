package org.example.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "CD_MEDICO")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue
    @Column(name = "ID_MEDICO")
    private UUID id;

    @Column(name = "NOME_MEDICO")
    private String nome;

    @Column(name = "CRM_MEDICO")
    private String crm;

    @Column(name = "ESPECIALIDADE_MEDICO")
    private String especialidade;

    @Column(name = "TELEFONE_MEDICO")
    private String telefone;
}