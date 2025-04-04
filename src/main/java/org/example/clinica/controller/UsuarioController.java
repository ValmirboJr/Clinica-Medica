package org.example.clinica.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.UsuarioRequestDTO;
import org.example.clinica.dto.UsuarioResponseDTO;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Usuario;
import org.example.clinica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final GenericMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(mapper.entidadeParaDTO(usuarioService.getById(id), UsuarioResponseDTO.class));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<UsuarioResponseDTO> findByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(mapper.entidadeParaDTO(usuarioService.findByNome(nome), UsuarioResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        return ResponseEntity.ok(mapper.entidadeParaDTO(usuarioService.getAll(), UsuarioResponseDTO.class));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioService.login(usuarioRequestDTO);
        if (usuario != null) {
            return ResponseEntity.ok(mapper.entidadeParaDTO(usuario, UsuarioResponseDTO.class));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = mapper.entidadeParaDTO(usuarioService.create(usuarioRequestDTO), UsuarioResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@RequestBody UsuarioRequestDTO usuarioRequestDTO, @PathVariable("id") UUID id) {
        UsuarioResponseDTO usuarioResponseDTO = mapper.entidadeParaDTO(usuarioService.update(id, usuarioRequestDTO), UsuarioResponseDTO.class);
        return ResponseEntity.ok().body(usuarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

