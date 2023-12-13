package org.java.spring.controller;

import java.util.List; 

import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serve.IngredienteService;
import org.java.spring.db.serve.OffertaSpecialeService;
import org.java.spring.db.serve.Pizzeriaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private Pizzeriaservice pizzeriaService;
	
	@Autowired
	private OffertaSpecialeService offertaSpecialeService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping("/")
	public String getPizze(Model model,@RequestParam(required = false) String search) {
		
		List<Pizza> pizze = search == null  
				? pizzeriaService.findAll()
				: pizzeriaService.findByNome(search);
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("search", search == null ? "" : search);
		
		return "index";
	}
	
	@GetMapping("/pizza/{id}")
	public String getPizza(Model model,
			@PathVariable int id) {
		
		Pizza pizza = pizzeriaService.findById(id);
		List<Ingrediente> ingredienti = pizza.getIngrediente();
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredienti", ingredienti);
		
		return "pizza";
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		Pizza pizza = new Pizza();	
				
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredienti", ingredienti);
		return "pizza-form";
	}
	
	@PostMapping("/pizza/create")
	public String storePizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza, 
			BindingResult bindingResult) {
		
				
		return savePizza(model, pizza, bindingResult);
	}
	
	@GetMapping("/pizza/edit/{id}")
	public String editPizza(Model model,
			@PathVariable int id) {
		
		Pizza pizza = pizzeriaService.findById(id);
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("pizza", pizza);
		 model.addAttribute("ingredienti", ingredienti);
		return "pizza-form";
	}
	@PostMapping("/pizza/edit/{id}")
	public String updatePizza(Model model,
			@Valid @ModelAttribute Pizza pizza, 
			BindingResult bindingResult) {
		
		return savePizza(model, pizza, bindingResult);
	}
	
	@PostMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		
		Pizza pizza = pizzeriaService.findById(id);
        List<OffertaSpeciale> offertePizza = offertaSpecialeService.findByPizza(pizza);
        offertaSpecialeService.deleteAll(offertePizza);

        pizza.clearIngrediente();
        pizzeriaService.delete(pizza);
			
		return "redirect:/";
	}
	
	private String savePizza(Model model,
			@Valid @ModelAttribute Pizza pizza, 
			BindingResult bindingResult) {
		
		
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "pizza-form";
		}
			
			pizzeriaService.save(pizza);
			  
		return "redirect:/";
	}
	
}
