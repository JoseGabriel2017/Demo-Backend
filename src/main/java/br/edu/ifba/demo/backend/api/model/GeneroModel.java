package br.edu.ifba.demo.backend.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "genero")
public class GeneroModel {

    @Column(name = "genero_nome", nullable = false, unique = true)
    private String generoNome;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    private Long idGenero;
}