package com.poc1.dao;

import java.util.List;

import com.poc1.domain.Product;

public interface ProductService {
	
	public void saveProduct(Product obj);
	public void delete(int id);
	public List<Product> list();
	public Product getProductById(Long id);
}
