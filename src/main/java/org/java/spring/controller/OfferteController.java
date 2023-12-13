package org.java.spring.controller;



import org.java.spring.db.dto.PizzaOfferteDTO;
import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serve.OffertaSpecialeService;
import org.java.spring.db.serve.Pizzeriaservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferteController {

	@Autowired
	private Pizzeriaservice pizzeriaService;
	
	@Autowired
	private OffertaSpecialeService offertaSpecialeService;
	
	@GetMapping("/pizza/{id}/offerta")
	public String getOfferteForm(Model model, @PathVariable int id) {
	    Pizza pizza = pizzeriaService.findById(id);
	    OffertaSpeciale offertaSpeciale = new OffertaSpeciale();
	    model.addAttribute("offertaSpeciale", offertaSpeciale);
	    model.addAttribute("pizza", pizza);
	    return "offerte-form";
	}

	@PostMapping("/pizza/{id}/offerta")
	public String storeOfferte(
	        @ModelAttribute PizzaOfferteDTO pizzaOfferteDTO,
	        @PathVariable int id
	) {
	    Pizza pizza = pizzeriaService.findById(id);
	    OffertaSpeciale offertaSpeciale = new OffertaSpeciale(
	            pizzaOfferteDTO.getDataInizio(),
	            pizzaOfferteDTO.getDataFine(),
	            pizzaOfferteDTO.getTitolo(),
	            pizza
	    );
	    offertaSpecialeService.save(offertaSpeciale);
	    return "redirect:/pizza/{id}";
	}
	
	
	

	@GetMapping("/pizza/{pizza_id}/offerte/edit/{id}")
	public String getEditOffertaForm(Model model, @PathVariable int id, @PathVariable int pizza_id) {
		
	    OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(id);
	    model.addAttribute("offertaSpeciale", offertaSpeciale);
	    model.addAttribute("pizza_id", pizza_id);
	    return "edit-form";
	}

	@PostMapping("/pizza/{pizza_id}/offerte/edit/{id}")
	public String updateOfferta(
	        @PathVariable int id,
	        @PathVariable int pizza_id,
	        @ModelAttribute PizzaOfferteDTO pizzaOfferteDTO
	) {
		
	    OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(id);
	    
	    
	    
	    offertaSpeciale.setDataInizio(pizzaOfferteDTO.getDataInizio());
	    offertaSpeciale.setDataFine(pizzaOfferteDTO.getDataFine());
	    offertaSpeciale.setTitolo(pizzaOfferteDTO.getTitolo());
	    offertaSpecialeService.save(offertaSpeciale);
	    
	    return "redirect:/pizza/{pizza_id}";
	}
	
	

	@PostMapping("/pizza/offerte/delete/{id}")
	public String deleteOfferta(@PathVariable int id) {
	    OffertaSpeciale offertaSpeciale = offertaSpecialeService.findById(id);
	    int pizzaId = offertaSpeciale.getPizza().getId();
	    offertaSpecialeService.deleteOfferta(offertaSpeciale);
	    return "redirect:/pizza/" + pizzaId;
	}

}