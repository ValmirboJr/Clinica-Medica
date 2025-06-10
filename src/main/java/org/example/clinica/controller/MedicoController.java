package org.example.clinica.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.MedicoRequestDTO;
import org.example.clinica.dto.MedicoResponseDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.service.MedicoService;
import org.example.clinica.service.imp.MedicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private final GenericMapper mapper;
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private MedicoServiceImpl medicoServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> getById(@PathVariable("id") UUID id) throws NotFoundException {
        return ResponseEntity.ok(mapper.entidadeParaDTO(medicoService.getMedicoById(id), MedicoResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponseDTO>> getAll() {
        return ResponseEntity.ok(mapper.entidadeParaDTO(medicoService.findAll(), MedicoResponseDTO.class));
    }

    @GetMapping("/especialidade")
    public ResponseEntity<List<MedicoResponseDTO>> findByEspecialidade(
            @RequestParam("especialidade") String especialidade) throws NotFoundException {
        List<?> medicos = medicoServiceImpl.findByEspecialidade(especialidade);
        List<MedicoResponseDTO> dtos = mapper.entidadeParaDTO(medicos, MedicoResponseDTO.class);
        if (dtos.isEmpty()) {
            throw new NotFoundException("Nenhum médico encontrado com especialidade: " + especialidade);
        }
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<MedicoResponseDTO> create(@RequestBody MedicoRequestDTO medicoRequestDTO) {
        MedicoResponseDTO medicoResponseDTO = mapper.entidadeParaDTO(medicoService.Create(medicoRequestDTO), MedicoResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> update(@PathVariable("id") UUID id, @RequestBody MedicoRequestDTO medicoRequestDTO) throws NotFoundException {
        MedicoResponseDTO medicoResponseDTO = mapper.entidadeParaDTO(medicoService.Update(medicoRequestDTO, id), MedicoResponseDTO.class);
        return ResponseEntity.ok(medicoResponseDTO);
    }

    @DeleteMapping("/crm/{crm}")
    public ResponseEntity<Void> delete(@PathVariable("crm") String crm) throws NotFoundException {
        medicoService.delete(crm);
        return ResponseEntity.noContent().build();
    }
}
