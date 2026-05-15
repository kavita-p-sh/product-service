package com.ecommerce.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.ecommerce.product",
		"com.ecommerce.common.exception",
		"com.ecommerce.common.dto",
		"com.ecommerce.common.util",
		"com.ecommerce.common.audit"
})
@EntityScan(basePackages = {
		"com.ecommerce.common.entity"
})
@EnableJpaRepositories(basePackages = {
		"com.ecommerce.common.repository"
})
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}
}