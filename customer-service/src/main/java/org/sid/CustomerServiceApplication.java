package org.sid;

import org.sid.beans.Customer;
import org.sid.dao.CustomerRepository; 
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,RepositoryRestConfiguration restConfiguration){
		return args -> {
			restConfiguration.exposeIdsFor(Customer.class);// afin d'exposer l'Id pour la classe Customer
			customerRepository.save(new Customer(null,"ayoub","ayoub@gmail.com"));
			customerRepository.save(new Customer(null,"llaa","llaa@gmail.com"));
			customerRepository.save(new Customer(null,"younes","younes@gmail.com"));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
