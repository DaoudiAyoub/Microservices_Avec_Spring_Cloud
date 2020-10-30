package org.sid;

import org.sid.beans.Product;
import org.sid.dao.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository,RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Product.class);// afin d'exposer l'Id pour la classe Product
			productRepository.save(new Product(null,"HP",4500));
			productRepository.save(new Product(null,"Mac",2000));
			productRepository.save(new Product(null,"Dell",18000));
			productRepository.findAll().forEach(System.out::println);
		};
	}
}
