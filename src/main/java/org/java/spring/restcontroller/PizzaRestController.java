package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serve.Pizzeriaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizza")
public class PizzaRestController {

	@Autowired
	private Pizzeriaservice pizzeriaService;
	
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex() {
		
		List<Pizza> pizza = pizzeriaService.findAll();
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
}
