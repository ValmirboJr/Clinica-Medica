package org.example.clinica.repository;

import org.example.clinica.model.Atendimento;
import org.example.clinica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
    Optional<Atendimento> findById(UUID id_atendimento);
    List<Atendimento> findByMedicoAndHoraAndData(Medico medico, LocalTime hora, LocalDate data);
}
