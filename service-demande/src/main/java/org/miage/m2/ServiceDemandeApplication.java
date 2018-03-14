package org.miage.m2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceDemandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDemandeApplication.class, args);
	}
}
