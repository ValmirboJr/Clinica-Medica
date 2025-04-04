package org.example.clinica.repository;

import org.example.clinica.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
    Optional<Atendimento> findById(UUID id_atendimento);

}
