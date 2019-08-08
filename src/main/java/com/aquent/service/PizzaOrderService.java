package com.aquent.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.aquent.model.PizaaOrder;
import com.aquent.model.Pizza;

@Service
public interface PizzaOrderService {

	
	public List<Pizza> readFile() throws IOException;
	
	public void saveFile() throws IOException;
	
	public PizaaOrder saveOrder(PizaaOrder pizzaOrder);
	
	@Query("select s from PizaOrder s where like %?1% ")
	public List<PizaaOrder> findAll();
	@Query("select s from PizaOrder s where like %?1% ")
	public PizaaOrder findByName(String name);
	
	
	@Query("select s from PizaOrder s where like %?1% ")
	public List<PizaaOrder> findAlls(String name);
	
	
	public Pizza save(Pizza pizza);
	
	public void sortByValue();
}
