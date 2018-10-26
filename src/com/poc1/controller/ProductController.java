package com.poc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.dao.ProductService;
import com.poc1.domain.Product;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	/*@RequestMapping("/")
	 public String welcome() {
		 return "Welcome.";
	}*/
	 
	@RequestMapping(value="/saveproduct", method=RequestMethod.POST)
	public String saveProduct(@RequestBody Product obj) {
		productService.saveProduct(obj);
		return "Created...";
	}	
	
	@RequestMapping(value="/getProductById", method=RequestMethod.GET)
	public Product getProductById(@RequestParam Long id) {
		return productService.getProductById(id);
	}
	
	@RequestMapping(value="/deleteproduct", method=RequestMethod.GET)
	public String delete(@RequestParam int id) {
		productService.delete(id);
		return "Deleted...";
	}
	
	@RequestMapping(value="/listproduct", method=RequestMethod.GET)
	public List<Product> list() {
		return productService.list();
	}
		
}
