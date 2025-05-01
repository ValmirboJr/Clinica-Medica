package org.example.clinica.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.PacienteRequestDTO;
import org.example.clinica.dto.PacienteResponseDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private final PacienteService pacienteService;

    @Autowired
    private final GenericMapper mapper;

    @GetMapping("/{idpaciente}")
    public ResponseEntity<PacienteResponseDTO> getById(@PathVariable("idpaciente") UUID id) throws NotFoundException {
        return ResponseEntity.ok(mapper.entidadeParaDTO(pacienteService.getById(id), PacienteResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> getAll() {
        return ResponseEntity.ok(mapper.entidadeParaDTO(pacienteService.findAll(), PacienteResponseDTO.class));
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> create(@RequestBody PacienteRequestDTO pacienteRequestDTO) throws NotFoundException {
        PacienteResponseDTO pacienteResponseDTO = mapper.entidadeParaDTO(pacienteService.Create(pacienteRequestDTO), PacienteResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponseDTO);
    }

    @PutMapping("/{idpaciente}")
    public ResponseEntity<PacienteResponseDTO> update(@PathVariable("idpaciente") UUID idpaciente, @RequestBody PacienteRequestDTO pacienteRequestDTO) throws NotFoundException {
        PacienteResponseDTO pacienteResponseDTO = mapper.entidadeParaDTO(pacienteService.Update(pacienteRequestDTO, idpaciente), PacienteResponseDTO.class);
        return ResponseEntity.ok(pacienteResponseDTO);
    }

    @DeleteMapping("/{idpaciente}")
    public ResponseEntity<Void> delete(@PathVariable("idpaciente") UUID idpaciente) throws NotFoundException {
        pacienteService.delete(idpaciente);
        return ResponseEntity.noContent().build();
    }
}

