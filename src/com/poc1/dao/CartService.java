package com.poc1.dao;

import java.util.List;

import com.poc1.domain.Cart;

public interface CartService {

	public void saveCart(Cart obj);
	public void delete(int id);
	public List<Cart> list();
	public Cart getCartById(Long id);
}
