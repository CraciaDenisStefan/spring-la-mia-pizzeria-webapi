package org.java.spring.db.serve;

import java.util.List; 

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.repo.PizzeriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pizzeriaservice {

	@Autowired
	
	private PizzeriaRepository pizzeriaRepository;
	
	public List<Pizza> findAll() {
		
		return pizzeriaRepository.findAll();
	}
	public Pizza findById(int id) {
		
		return pizzeriaRepository.findById(id).get();
	}
	
	public List<Pizza> findByNome(String query) {
		
		return pizzeriaRepository.findByNomeContainingIgnoreCase(query);
	}

	public void save(Pizza pizza) {
		
		pizzeriaRepository.save(pizza);
	}
	public void delete(Pizza pizza) {
		
		pizzeriaRepository.delete(pizza);
	}
	
}
