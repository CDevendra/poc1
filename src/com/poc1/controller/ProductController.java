package com.poc1.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String getFileContain(HttpServletRequest request,
											   @RequestParam("file") MultipartFile multiPartFile) 
													   throws FileNotFoundException, IOException{
		String result = productService.upload(multiPartFile);		
		return result;
	}
		
}
