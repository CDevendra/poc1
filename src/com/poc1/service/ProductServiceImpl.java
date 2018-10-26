package com.poc1.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc1.dao.ProductService;
import com.poc1.domain.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private JdbcTemplate jdbcTemplate;

	public ProductServiceImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveProduct(Product product) {

		if (product.getProductId() > 0) {
			// update
			String sql = "UPDATE product SET productName=?, productPrice=?, productDesc=? WHERE productId=?";
			jdbcTemplate.update(sql, product.getProductName(), product.getProductPrice(), product.getProductDesc(),
					product.getProductId());
		} else {
			// insert
			String sql = "INSERT INTO product (productName, productPrice, productDesc)" + " VALUES (?, ?, ?)";
			jdbcTemplate.update(sql, product.getProductName(), product.getProductPrice(), product.getProductDesc());
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
		System.out.println("Deleted Record with ID = " + id );
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
				return aProduct;
			}			
		});		
		return listProduct;
	}
	
	/*@Override
	public void update(int id) {
		String sql = "UPDATE------ product WHERE productId=?";
		jdbcTemplate.update(sql, id);
	}*/

}
