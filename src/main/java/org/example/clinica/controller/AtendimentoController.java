package org.example.clinica.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinica.dto.AtendimentoRequestDTO;
import org.example.clinica.dto.AtendimentoResponseDTO;
import org.example.clinica.excecao.NotFoundException;
import org.example.clinica.mapper.GenericMapper;
import org.example.clinica.model.Atendimento;
import org.example.clinica.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/Atendimento")
public class AtendimentoController {

    @Autowired
    private final AtendimentoService atendimentoService;

    @Autowired
    private final GenericMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> getById(@PathVariable("id") UUID id) throws NotFoundException {
        Atendimento atendimento = atendimentoService.findById(id);
        return ResponseEntity.ok(mapper.entidadeParaDTO(atendimento, AtendimentoResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> getAll() {
        List<Atendimento> atendimentos = atendimentoService.findAll();
        return ResponseEntity.ok(mapper.entidadeParaDTO(atendimentos, AtendimentoResponseDTO.class));
    }

    @PostMapping("/{idPaciente}")
    public ResponseEntity<List<AtendimentoResponseDTO>> criarAtendimento(
            @PathVariable UUID idPaciente,
            @RequestBody @Validated List< AtendimentoRequestDTO> dtoList
    ) throws NotFoundException {
        List<Atendimento> criados = atendimentoService.CriarAtendimento(dtoList, idPaciente);

        List<AtendimentoResponseDTO> response =
                mapper.entidadeParaDTO(criados, AtendimentoResponseDTO.class);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<AtendimentoResponseDTO>> listarPorPaciente(
            @PathVariable UUID idPaciente) throws NotFoundException {

        List<Atendimento> lista = atendimentoService.findByPaciente(idPaciente);
        List<AtendimentoResponseDTO> dtos =
                mapper.entidadeParaDTO(lista, AtendimentoResponseDTO.class);

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/listar")
    public ResponseEntity<List<AtendimentoResponseDTO>> listarAtendimentos(
            @RequestBody Map<String,String> filtro
    ) throws NotFoundException {
        String crm = filtro.get("crm");
        LocalDate data = LocalDate.parse(filtro.get("data"));
        LocalTime hora = LocalTime.parse(filtro.get("hora"));
        List<Atendimento> atends = atendimentoService.listarAtendimentos(crm, data,hora);
        List<AtendimentoResponseDTO> dtos =
                mapper.entidadeParaDTO(atends, AtendimentoResponseDTO.class);
        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/atendimentos/especialidade")
    public ResponseEntity<List<Atendimento>> listarAtendimentosPorEspecialidade(
            @RequestParam String especialidade) {
        List<Atendimento> atendimentos = atendimentoService.listarAtendimentosPorEspecialidade(especialidade);
        return ResponseEntity.ok(atendimentos);
    }
}