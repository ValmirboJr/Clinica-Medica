package org.example.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "CD_ATENDIMENTO")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Atendimento {

    @Id
    @GeneratedValue
    @Column(name = "ID_ATENDIMENTO")
    private UUID id_atendimento;

    @ManyToOne
    @JoinColumn(name = "ID_MEDICO", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    private Paciente paciente;

    @Column(name = "data_atendimento", nullable = false)
    private LocalDate data;

    @Column(name = "hora_atendimento", nullable = false)
    private LocalTime hora;

    @Column(name = "sala_atendimento", length = 50)
    private String sala;

}
