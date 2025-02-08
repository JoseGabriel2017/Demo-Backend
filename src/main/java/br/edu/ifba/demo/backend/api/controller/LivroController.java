package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.demo.backend.api.model.LivroModel;
import br.edu.ifba.demo.backend.api.dto.LivroDTO;
import br.edu.ifba.demo.backend.api.repository.LivroRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/listall")
    public List<LivroModel> listAll() {
        return livroRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<LivroModel> getById(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getByIsbn/{isbn}")
    public ResponseEntity<LivroDTO> buscarPorISBN(@PathVariable String isbn) {
        return Optional.ofNullable(livroRepository.findByIsbn(isbn))
                .map(livro -> ResponseEntity.ok(LivroDTO.converter(livro)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getByTitulo/{titulo}")
    public ResponseEntity<List<LivroDTO>> getByTitulo(@PathVariable String titulo) {
        List<LivroModel> livros = livroRepository.findByTitulo(titulo);
        if (livros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(LivroDTO.converter(livros));
    }

    @PostMapping
    public ResponseEntity<LivroModel> addLivro(@RequestBody LivroModel livro) {
        LivroModel savedLivro = livroRepository.save(livro);
        return new ResponseEntity<>(savedLivro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long id, @RequestBody LivroDTO livroAtualizado) {
        return livroRepository.findById(id)
                .map(livroExistente -> {
                    livroExistente.setTitulo(livroAtualizado.getTitulo());
                    livroExistente.setAutor(livroAtualizado.getAutor());
                    livroExistente.setEditora(livroAtualizado.getEditora());
                    livroExistente.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
                    livroExistente.setGenero(null); // Tratar adequadamente se necessário
                    livroExistente.setIsbn(livroAtualizado.getIsbn());
                    livroExistente.setNumPaginas(livroAtualizado.getNumPaginas());
                    livroExistente.setSinopse(livroAtualizado.getSinopse());
                    livroExistente.setIdioma(livroAtualizado.getIdioma());
                    livroExistente.setDataCadastro(livroAtualizado.getDataCadastro());
                    livroExistente.setPreco(livroAtualizado.getPreco());

                    LivroModel livroSalvo = livroRepository.save(livroExistente);
                    return ResponseEntity.ok(LivroDTO.converter(livroSalvo));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroModel> deleteLivro(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livroRepository.delete(livro);
                    return new ResponseEntity<>(livro, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}