package org.example.clinica.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "CD_USUARIO")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "ID_USUARIO")
    private UUID id;

    @Column(name = "NOME_USUARIO")
    private String nome;

    @Column(name = "SENHA_USUARIO")
    private String senha;
}
