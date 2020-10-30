package org.sid.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ProductItem {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY) //or @JsonIgnore 
	private Long productId;
	@Transient
	private Product product;
	private double price;
	private double quantity;
	@ManyToOne
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY) //or @JsonIgnore 
	private Bill bill;
	
	
	public ProductItem(Long id, Long productId, Product product, double price, double quantity, Bill bill) {
		super();
		this.id = id;
		this.productId = productId;
		this.product = product;
		this.price = price;
		this.quantity = quantity;
		this.bill = bill;
	}

	public ProductItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	

}
