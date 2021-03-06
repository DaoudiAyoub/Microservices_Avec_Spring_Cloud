package org.sid.service;

import org.sid.beans.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="INVENTORY-SERVICE",url="http://localhost:8888/")
public interface InventoryService {	
	@GetMapping("/products/{id}")
  public Product findProductById(@PathVariable(name="id")Long id);
	
	@GetMapping("/products")
	public PagedModel<Product> findAllProducts();

}
