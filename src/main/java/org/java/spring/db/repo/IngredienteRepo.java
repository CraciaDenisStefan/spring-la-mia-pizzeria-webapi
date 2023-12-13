package org.java.spring.db.repo;

import java.util.List;

import org.java.spring.db.pojo.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer> {
	
	List<Ingrediente> findByNomeContainingIgnoreCase(String nome);
}
