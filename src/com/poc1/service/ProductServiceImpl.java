package com.poc1.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc1.dao.ProductService;
import com.poc1.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private JdbcTemplate jdbcTemplate;
	private static final Logger LOG = Logger.getLogger(ProductServiceImpl.class);
	 
	public ProductServiceImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveProduct(Product product) {

		if (product.getProductId() > 0) {
			// update
			String sql = "UPDATE product SET productName=?, productPrice=?, productDesc=?, productImagePath=? WHERE productId=?";
			jdbcTemplate.update(sql, product.getProductName(), product.getProductPrice(), product.getProductDesc(),
					product.getProductId());

			LOG.info("Update Successfull. : "+sql);
		} else {
			// insert			
				String sql = "INSERT INTO product (productName, productPrice, productDesc, productImagePath)" + " VALUES (?, ?, ?, ?)";
				jdbcTemplate.update(sql, product.getProductName(), product.getProductPrice(), product.getProductDesc(), product.getProductImagePath());
				LOG.info("Insert Successfully. : " +sql);
		}
	}

	@Override
	public Product getProductById(Long productId) {
		String sql = "SELECT * FROM product WHERE productId=" + productId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Product>() {

			@Override
			public Product extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					Product product = new Product();
					product.setProductId(rs.getInt("productId"));
					product.setProductName(rs.getString("productName"));
					product.setProductPrice(rs.getString("productPrice"));
					product.setProductDesc(rs.getString("productDesc"));
					LOG.info("SQL : " + sql);
					return product;
				}

				return null;
			}
		});
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM product WHERE productId = ?";
		jdbcTemplate.update(sql, id);
		LOG.info("Deleted Record with ID = " + id );
	}

	@Override
	public List<Product> list() {
		String sql = "SELECT * FROM product";
		List<Product> listProduct = jdbcTemplate.query(sql, new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product aProduct = new Product();
				
				aProduct.setProductId(rs.getInt("productId"));
				aProduct.setProductName(rs.getString("productName"));
				aProduct.setProductPrice(rs.getString("productPrice"));
				aProduct.setProductDesc(rs.getString("productDesc"));	
				LOG.info("SQL : " + sql);
				return aProduct;
			}			
		});		
		return listProduct;
	}

	public String upload(MultipartFile multiPartFile) throws FileNotFoundException, IOException{
		
		String filename1 = multiPartFile.getOriginalFilename();
		String directoryPath = Helper.getFileDirectoryByFileType();
		String location = directoryPath + "/" + filename1;
		File dest = new File(location);
		
		multiPartFile.transferTo(dest);
		LOG.info("File uploaded : " + dest.getAbsolutePath());		
		return dest.getAbsolutePath();
		
	}


}
