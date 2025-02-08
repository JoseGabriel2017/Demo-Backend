package br.edu.ifba.demo.backend.api.repository;

import br.edu.ifba.demo.backend.api.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long> {
	LivroModel findByIsbn(String isbn);

	List<LivroModel> findByTitulo(String titulo);
}