package org.example.clinica.service.imp;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.UsuarioRequestDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Usuario;
import org.example.clinica.repository.UsuarioRepository;
import org.example.clinica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private final UsuarioRepository usuarioRepository;

    private final GenericMapper mapper;

    @Override
    public Usuario getById(UUID idUsuario) throws NotFoundException {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotFoundException("Não foi possível encontrar o usuário pelo id " + idUsuario));
    }

    @Override
    public Usuario findByNome(String nome) {
        return usuarioRepository.findByNome(nome).stream().findFirst().orElseThrow(() -> new NotFoundException("Não foi possível encontrar o usuário pelo nome: " + nome));
    }


    @Override
    public List<Usuario> getAll() throws NotFoundException {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario login(UsuarioRequestDTO usuarioRequestDTO) throws NotFoundException {
        Usuario usuario = null;
        usuario = usuarioRepository.findByNome(usuarioRequestDTO.getNome()).stream().findFirst().orElse(null);
        return usuario;
    }

    @Override
    public Usuario create(UsuarioRequestDTO usuarioRequestDTO) throws NotFoundException {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(UUID idUsuario, UsuarioRequestDTO usuarioRequestDTO) throws NotFoundException {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotFoundException("Não foi possível encontrar o usuário pelo id " + idUsuario));
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setSenha(usuarioRequestDTO.getSenha());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(UUID idUsuario) throws NotFoundException {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com o ID: " + idUsuario));
        usuarioRepository.delete(usuario);
    }
}
