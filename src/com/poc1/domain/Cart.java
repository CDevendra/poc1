package com.poc1.domain;

import java.util.List;


public class Cart {
	
	private int cartId;
    private String cartName;
    private String customerId;
    private List<Product> productIdList;
    private int totalProductQuantity;
    private int totalProductcost;
    
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCartName() {
		return cartName;
	}
	public void setCartName(String cartName) {
		this.cartName = cartName;
	}
	public List<Product> getProductId() {
		return productIdList;
	}
	public void setProductId(List<Product> productIdList) {
		this.productIdList = productIdList;
	}
	public int getTotalProductQuantity() {
		return totalProductQuantity;
	}
	public void setTotalProductQuantity(int totalProductQuantity) {
		this.totalProductQuantity = totalProductQuantity;
	}
	public int getTotalProductcost() {
		return totalProductcost;
	}
	public void setTotalProductcost(int totalProductcost) {
		this.totalProductcost = totalProductcost;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + ", cartName=" + cartName + ", productIdList="
				+ productIdList + ", totalProductQuantity=" + totalProductQuantity + ", totalProductcost="
				+ totalProductcost + "]";
	}
    
    
    
}
