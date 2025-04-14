package org.example.clinica.repository;

import org.example.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    List<Paciente> findByNome(String nome);
}
