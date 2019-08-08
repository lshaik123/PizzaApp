package com.aquent.service;

import java.util.List;

import com.aquent.model.Product;

public interface ProductService {

	
	void findProduct(int id);
	
	public List<Product> getAllProducts();
}
