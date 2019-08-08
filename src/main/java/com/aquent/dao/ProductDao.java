package com.aquent.dao;

import org.springframework.stereotype.Service;

import com.aquent.model.Product;
@Service
public interface ProductDao {
	
	 void saveProdct(Product product);
	 void updateProdct(Product product);
	

}
