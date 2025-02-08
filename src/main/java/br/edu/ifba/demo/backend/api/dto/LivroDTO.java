package br.edu.ifba.demo.backend.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.demo.backend.api.model.LivroModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO implements Serializable {
    private Long idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private Integer anoPublicacao;
    private String genero;
    private String isbn;
    private Integer numPaginas;
    private String sinopse;
    private String idioma;
    private LocalDateTime dataCadastro;
    private Double preco;

    public static List<LivroDTO> converter(List<LivroModel> livros) {
        return livros.stream()
                .map(LivroDTO::converter)
                .collect(Collectors.toList());
    }

    public static LivroDTO converter(LivroModel livroModel) {
        return new LivroDTO(
                livroModel.getIdLivro(),
                livroModel.getTitulo(),
                livroModel.getAutor(),
                livroModel.getEditora(),
                livroModel.getAnoPublicacao(),
                livroModel.getGenero() != null ? livroModel.getGenero().getGeneroNome() : null,
                livroModel.getIsbn(),
                livroModel.getNumPaginas(),
                livroModel.getSinopse(),
                livroModel.getIdioma(),
                livroModel.getDataCadastro(),
                livroModel.getPreco());
    }
}