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
import com.poc1.dao.CartService;
import com.poc1.domain.Cart;

@Service
public class CartServiceImpl implements CartService {

	private JdbcTemplate jdbcTemplate;
	private static final Logger LOG = Logger.getLogger(CartServiceImpl.class);
	 
	public CartServiceImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveCart(Cart cart) {

		if (cart.getCartId() > 0) {
			// update
			String sql = "UPDATE cart SET cartName=?, cartPrice=?, cartDesc=?, cartImagePath=? WHERE cartId=?";
			jdbcTemplate.update(sql, cart.getCartName(), /*cart.getCartPrice(), cart.getCartDesc(),*/
					cart.getCartId());

			LOG.info("Update Successfull. : "+sql);
		} else {
			// insert			
				String sql = "INSERT INTO cart (cartId, cartName, customerId, productIdList, cartImagePath)" + " VALUES (?, ?, ?, ?)";
				//jdbcTemplate.update(sql, cart.getCartName(), cart.getCartPrice(), cart.getCartDesc(), cart.getCartImagePath());
				LOG.info("Insert Successfully. : " +sql);
		}
	}

	@Override
	public Cart getCartById(Long cartId) {
		String sql = "SELECT * FROM cart WHERE cartId=" + cartId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Cart>() {

			@Override
			public Cart extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					Cart cart = new Cart();
					cart.setCartId(rs.getInt("cartId"));
					cart.setCartName(rs.getString("cartName"));
					//cart.setCartPrice(rs.getString("cartPrice"));
					//cart.setCartDesc(rs.getString("cartDesc"));
					LOG.info("SQL : " + sql);
					return cart;
				}

				return null;
			}
		});
	}
	
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM cart WHERE cartId = ?";
		jdbcTemplate.update(sql, id);
		LOG.info("Deleted Record with ID = " + id );
	}

	@Override
	public List<Cart> list() {
		String sql = "SELECT * FROM cart";
		List<Cart> listCart = jdbcTemplate.query(sql, new RowMapper<Cart>() {
			@Override
			public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cart aCart = new Cart();
				
				aCart.setCartId(rs.getInt("cartId"));
				aCart.setCartName(rs.getString("cartName"));
				//aCart.setCartPrice(rs.getString("cartPrice"));
				//aCart.setCartDesc(rs.getString("cartDesc"));	
				LOG.info("SQL : " + sql);
				return aCart;
			}			
		});		
		return listCart;
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
