package com.poc1.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.poc1.domain.Product;

public interface ProductService {
	
	public void saveProduct(Product obj);
	public void delete(int id);
	public List<Product> list();
	public Product getProductById(Long id);
	
	public String upload(MultipartFile multiPartFile) throws FileNotFoundException, IOException;
}
