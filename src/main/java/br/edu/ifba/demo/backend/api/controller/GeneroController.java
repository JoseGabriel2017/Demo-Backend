package br.edu.ifba.demo.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifba.demo.backend.api.dto.GeneroDTO;
import br.edu.ifba.demo.backend.api.model.GeneroModel;
import br.edu.ifba.demo.backend.api.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/genero")
@RequiredArgsConstructor
public class GeneroController {

    @Autowired
    private final GeneroRepository generoRepository;

    @GetMapping("/listall")
    public List<GeneroDTO> listAll() {
        return generoRepository.findAll()
                .stream()
                .map(GeneroDTO::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneroModel> getById(@PathVariable Long id) {
        Optional<GeneroModel> genero = generoRepository.findById(id);
        return genero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> addGenero(@RequestBody GeneroDTO generoDTO) {
        GeneroModel genero = new GeneroModel();
        genero.setGeneroNome(generoDTO.getNome());
        GeneroModel savedGenero = generoRepository.save(genero);
        return ResponseEntity.ok(GeneroDTO.converter(savedGenero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneroModel> updateGenero(@PathVariable Long id, @RequestBody GeneroDTO generoDTO) {
        return generoRepository.findById(id)
                .map(genero -> {
                    genero.setGeneroNome(generoDTO.getNome());
                    GeneroModel updatedGenero = generoRepository.save(genero);
                    return ResponseEntity.ok(updatedGenero);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenero(@PathVariable Long id) {
        return generoRepository.findById(id)
                .map(genero -> {
                    generoRepository.delete(genero);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}