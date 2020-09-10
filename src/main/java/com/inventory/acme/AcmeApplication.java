package com.inventory.acme;

import com.inventory.acme.models.Product;
import com.inventory.acme.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcmeApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(AcmeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		productRepository.save(new Product(null, "Product 1", "image1", 500.00, 7, "Description 1"));
		productRepository.save(new Product(null, "Product 2", "image2", 1000.00, 5, "Description 2"));
		productRepository.save(new Product(null, "Product 3", "image3", 1000.00, 3, "Description 3"));
		productRepository.save(new Product(null, "Product 4", "image4", 1000.00, 0, "Description 4"));
	}
}
