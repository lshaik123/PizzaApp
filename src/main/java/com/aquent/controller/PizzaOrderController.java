package com.aquent.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aquent.model.PizaaOrder;
import com.aquent.model.Pizza;
import com.aquent.service.PizzaOrderService;
import com.aquent.serviceimpl.PizzaOrderServiceImpl;

@RestController
@RequestMapping("/dominos")
public class PizzaOrderController {
	
	@Autowired
	private PizzaOrderServiceImpl pizzaOrderServiceImpl;
	
	@Autowired
	private PizzaOrderService pizzaService;
	
	
	
	@RequestMapping(path="/save", method=RequestMethod.POST)
	public  HttpStatus saveTheOrder(@Valid @RequestBody PizaaOrder pizzaOrder) {
		
		pizzaOrderServiceImpl.saveOrder(pizzaOrder);
		
		return HttpStatus.ACCEPTED.OK;
	}
	
	@GetMapping("/items")
	public  List<PizaaOrder> findAll(Optional<String> name) {
		

	//List<List<PizaaOrder>> sortedValue= Arrays.asList(order);
	
		/*
		 * List<List<PizaaOrder>> sortedOrdered=
		 * sortedValue.stream().sorted().collect(Collectors.toList());
		 * sortedOrdered.forEach(System.out::println);
		 */

	return 	pizzaService.findAll();

		
	}
	@GetMapping("/itemnames")
	public  PizaaOrder findByName(Optional<String> name) {
		
		return pizzaService.findByName(name.orElse("_"));
		
	}
	
	@GetMapping("getData")
	public  List<Pizza> readDataFromTextFile() throws IOException {
		
		return pizzaOrderServiceImpl.readFile();
		
		
	}
	@GetMapping("/write")
	public void writeDataFromTextFile() throws IOException {
		 pizzaOrderServiceImpl.saveFile();
	}
}
