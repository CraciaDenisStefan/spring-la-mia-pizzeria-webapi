package org.java.spring.db.serve;

import java.util.List;   

import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.repo.IngredienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepo ingredienteRepo;
	

	
	
	public List<Ingrediente> findAll() {
		
		return ingredienteRepo.findAll();
	}
	public Ingrediente findById(int id) {
		
		return ingredienteRepo.findById(id).get();
	}
	public void save(Ingrediente category) {
		
		ingredienteRepo.save(category);
	}
	
	public List<Ingrediente> findByNome(String query) {
		
		return ingredienteRepo.findByNomeContainingIgnoreCase(query);
	}
	public void delete(Ingrediente ingrediente) {
		
		ingredienteRepo.delete(ingrediente);
	}
	
	
}
