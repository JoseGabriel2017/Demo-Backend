package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.edu.ifba.demo.backend.api.model.GeneroModel;
import br.edu.ifba.demo.backend.api.repository.GeneroRepository;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    private final GeneroRepository generoRepository;

    public GeneroController(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @GetMapping
    public String teste() {
        return "Testando Rota Gênero";
    }

    @GetMapping("/listall")
    public List<GeneroModel> listAll() {
        return generoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<GeneroModel> addGenero(@RequestBody GeneroModel genero) {
        GeneroModel savedGenero = generoRepository.save(genero);
        return new ResponseEntity<>(savedGenero, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneroModel> deleteGenero(@PathVariable Long id) {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}