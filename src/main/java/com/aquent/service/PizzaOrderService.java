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
	
	
}
