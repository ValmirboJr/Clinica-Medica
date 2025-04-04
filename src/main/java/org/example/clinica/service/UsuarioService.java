package org.example.clinica.service;

import org.example.clinica.dto.UsuarioRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.model.Usuario;

import java.util.List;
import java.util.UUID;


public interface UsuarioService {

    Usuario getById(UUID idUsuario) throws NotFoundException;
    Usuario findByNome(String nome);
    List<Usuario> getAll() throws NotFoundException;
    Usuario login(UsuarioRequestDTO usuarioRequestDTO) throws NotFoundException;
    Usuario create(UsuarioRequestDTO usuarioRequestDTO) throws NotFoundException;
    Usuario update(UUID idUsuario, UsuarioRequestDTO usuarioRequestDTO) throws NotFoundException;
    void delete(UUID idUsuario) throws NotFoundException;
}
