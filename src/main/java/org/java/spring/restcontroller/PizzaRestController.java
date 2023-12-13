package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serve.OffertaSpecialeService;
import org.java.spring.db.serve.Pizzeriaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizza")
public class PizzaRestController {

	@Autowired
	private Pizzeriaservice pizzeriaService;
	@Autowired
	private OffertaSpecialeService offertaSpecialeService;
	
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex() {
		
		List<Pizza> pizza = pizzeriaService.findAll();
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(@PathVariable int id) {
		
		Pizza pizza = pizzeriaService.findById(id);
		
		if (pizza == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pizza> create(@RequestBody Pizza pizza) {
		
		pizzeriaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> update(
			@PathVariable int id,
			@RequestBody Pizza newPizza) {
	
		Pizza pizza = pizzeriaService.findById(id);
		
		pizza.setNome(newPizza.getNome());
		pizza.setDescrizione(newPizza.getDescrizione());
		pizza.setFotoUrl(newPizza.getFotoUrl());
		
		pizzeriaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Pizza> delete(
			@PathVariable int id) {
		
		Pizza pizza = pizzeriaService.findById(id);
		
		List<OffertaSpeciale> offertePizza = offertaSpecialeService.findByPizza(pizza);
	        offertaSpecialeService.deleteAll(offertePizza);

	        pizza.clearIngrediente();
		
		pizzeriaService.delete(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK); 
	}
	
}
