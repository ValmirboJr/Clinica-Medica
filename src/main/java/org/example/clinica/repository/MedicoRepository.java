package org.example.clinica.repository;

import org.example.clinica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {
    List<Medico> findByNome(String nome);
    Optional<Medico> findById(UUID idmedico);
    Optional<Medico> findByCrm(String crm);
}
