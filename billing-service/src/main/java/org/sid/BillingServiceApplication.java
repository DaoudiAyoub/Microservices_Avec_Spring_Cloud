package org.sid;


import java.util.Date;

import org.sid.beans.Bill;
import org.sid.beans.Customer;
import org.sid.beans.Product;
import org.sid.beans.ProductItem;
import org.sid.dao.BillRepository;
import org.sid.dao.ProductItemRepository;
import org.sid.service.CustomerService;
import org.sid.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BillRepository billRepository,ProductItemRepository productItemRepository,CustomerService customerService, InventoryService inventoryService){
		return args -> {
			Customer c1=customerService.findCustomerById(1L);
			System.out.println("*****************");
			System.out.println("ID="+c1.getId());
			System.out.println("Name="+c1.getName());
			System.out.println("Email="+c1.getEmail());
			System.out.println("*****************");
			Bill bill=billRepository.save(new Bill(null,new Date(),c1.getId(),null,null));
			
			PagedModel<Product> products=inventoryService.findAllProducts();
			products.getContent().forEach(p->{
				productItemRepository.save(new ProductItem(null,p.getId(),null,p.getPrice(),30,bill));
			});
			
			
		};
	}
}
