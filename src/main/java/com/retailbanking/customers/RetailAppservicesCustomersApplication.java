package com.retailbanking.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.retailbanking")
public class RetailAppservicesCustomersApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailAppservicesCustomersApplication.class, args);
	}

}
