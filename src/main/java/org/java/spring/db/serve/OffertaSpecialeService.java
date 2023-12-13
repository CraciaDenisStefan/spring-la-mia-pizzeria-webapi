package org.java.spring.db.serve;

import java.util.List;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.repo.OffertaSpecialeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaSpecialeService {

	@Autowired
	private OffertaSpecialeRepo offertaSpecialeRepo;
	
	public List<OffertaSpeciale> findAll() {
		
		return offertaSpecialeRepo.findAll();
	}
	public OffertaSpeciale findById(int id) {
		
		return offertaSpecialeRepo.findById(id).get();
	}
	public void save(OffertaSpeciale offertaSpeciale) {
		
		offertaSpecialeRepo.save(offertaSpeciale);
	}
	public void deleteOfferta(OffertaSpeciale offertaSpeciale) {
		
		offertaSpecialeRepo.delete(offertaSpeciale);
	}
	
    public List<OffertaSpeciale> findByPizza(Pizza pizza) {
        return offertaSpecialeRepo.findByPizza(pizza);
    }
    public void deleteAll(List<OffertaSpeciale> offerte) {
        offertaSpecialeRepo.deleteAll(offerte);
    }
}
