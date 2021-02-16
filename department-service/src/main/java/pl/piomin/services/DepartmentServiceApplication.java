package pl.piomin.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import pl.piomin.services.model.Department;
import pl.piomin.services.repository.DepartmentRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

	@Bean
	DepartmentRepository repository() {
		DepartmentRepository repository = new DepartmentRepository();
		repository.add(new Department(1L, "Development"));
		repository.add(new Department(2L, "Operations"));
		repository.add(new Department(3L, "IT"));
		repository.add(new Department(4L, "Mayti"));
		return repository;
	}
}
