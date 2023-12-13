package org.java.spring.db.repo;

import java.util.List;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffertaSpecialeRepo extends JpaRepository<OffertaSpeciale,Integer> {

	  List<OffertaSpeciale> findByPizza(Pizza pizza);
}
