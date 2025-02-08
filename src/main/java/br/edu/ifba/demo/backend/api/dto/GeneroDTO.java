package br.edu.ifba.demo.backend.api.dto;

import br.edu.ifba.demo.backend.api.model.GeneroModel;
import lombok.Data;

import java.util.Optional;

@Data
public class GeneroDTO {
    private Long id;
    private String nome;

    public static GeneroDTO converter(GeneroModel generoModel) {
        return Optional.ofNullable(generoModel)
                .map(model -> {
                    GeneroDTO dto = new GeneroDTO();
                    dto.setId(model.getIdGenero());
                    dto.setNome(model.getGeneroNome());
                    return dto;
                })
                .orElse(null);
    }
}