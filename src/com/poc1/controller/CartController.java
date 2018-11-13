package com.poc1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc1.dao.CartService;
import com.poc1.domain.Cart;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	/*
	 * @RequestMapping("/") public String welcome() { return "Welcome."; }
	 */

	@RequestMapping(value = "/savecart", method = RequestMethod.POST)
	public String saveCart(@RequestBody Cart obj) {
		cartService.saveCart(obj);
		return "Created...";
	}

	@RequestMapping(value = "/getCartById", method = RequestMethod.GET)
	public Cart getCartById(@RequestParam Long id) {
		return cartService.getCartById(id);
	}

	@RequestMapping(value = "/deletecart", method = RequestMethod.GET)
	public String delete(@RequestParam int id) {
		cartService.delete(id);
		return "Deleted...";
	}

	@RequestMapping(value = "/listcart", method = RequestMethod.GET)
	public List<Cart> list() {
		return cartService.list();
	}

}
