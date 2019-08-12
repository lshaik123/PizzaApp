package com.aquent.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aquent.model.PizaaOrder;
import com.aquent.model.Pizza;
import com.aquent.serviceimpl.PizzaOrderServiceImpl;

@RestController
@RequestMapping("/pizza")
public class PizzaOrderController {
	
	
	 private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PizzaOrderController.class);

	@Autowired
	private PizzaOrderServiceImpl pizzaOrderServiceImpl;

	
	
	
	@GetMapping("/getdata")
	public  List<Pizza> readDataFromTextFile() throws IOException {
		LOG.info(" readDataFromTextFile method is called from pizza orgerController");

		return pizzaOrderServiceImpl.readFile();
		
		
	}
	@GetMapping("/writedata")
	public void writeDataToTextFile() throws IOException {
		LOG.info(" PizzaOrderController.writeDataToTextFile method is called from pizza orgerController");

		 pizzaOrderServiceImpl.saveFile();
	}
	
	@PostMapping("/save")
	public int insertData(@Valid @RequestBody PizaaOrder pizzaOrder) {
		
		return pizzaOrderServiceImpl.insert(pizzaOrder);
		
	}
	
	@GetMapping("/findall")
	public List<PizaaOrder>finAllItems(){
		
		return pizzaOrderServiceImpl.findAll();
		
	}
	
	@GetMapping("/findOne")
	public PizaaOrder finOneItem(int id){
		
		return pizzaOrderServiceImpl.findById(id);
		
	}
}
