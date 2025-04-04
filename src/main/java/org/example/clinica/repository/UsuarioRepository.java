package org.example.clinica.repository;

import org.example.clinica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    List<Usuario> findByNome(String nome);
    Optional<Usuario> findById(UUID idusuario);
}
