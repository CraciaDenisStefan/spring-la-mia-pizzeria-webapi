package org.java.spring.controller;

import java.util.List; 

import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serve.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	 @GetMapping("/ingredienti")
	    public String getIngredienti(Model model, @RequestParam(required = false) String search) {
		 
	        List<Ingrediente> ingredienti = search == null 
	        		? ingredienteService.findAll()
	        		: ingredienteService.findByNome(search);
	        model.addAttribute("ingredienti", ingredienti);
	        return "ingredienti";
	    }

	    @GetMapping("/ingredienti/create")
	    public String createIngrediente(Model model) {
	        Ingrediente ingrediente = new Ingrediente();
	        model.addAttribute("ingrediente", ingrediente);
	        return "ingredienti-form";
	    }

	    @PostMapping("/ingredienti/create")
	    public String storeIngrediente(@ModelAttribute Ingrediente ingrediente, Model model) {
	    	
	        ingredienteService.save(ingrediente);
	        return "redirect:/ingredienti";
	    }
	    
	    
	    
	    @GetMapping("/ingredienti/edit/{id}")
		public String editIngrediente(Model model,
				@PathVariable int id) {
			
			Ingrediente ingrediente = ingredienteService.findById(id);
			
			model.addAttribute("ingrediente", ingrediente);
		
			return "ingredienti-form";
		}
	    
		@PostMapping("/ingredienti/edit/{id}")
		public String updateIngrediente(Model model,
				@Valid @ModelAttribute Ingrediente ingrediente
				) {
			
			ingredienteService.save(ingrediente);
	        return "redirect:/ingredienti";
			
		}
		
		@PostMapping("/ingredienti/delete/{id}")
		public String deleteIngredienti(@PathVariable int id) {
		    Ingrediente ingrediente = ingredienteService.findById(id);

		    if (ingrediente.getPizza() != null) {
		        for (Pizza pizza : ingrediente.getPizza()) {
		            pizza.getIngrediente().remove(ingrediente);
		        }
		    }

		   
		    ingrediente.getPizza().clear();

		    ingredienteService.delete(ingrediente);

		    return "redirect:/ingredienti";
		}
}
