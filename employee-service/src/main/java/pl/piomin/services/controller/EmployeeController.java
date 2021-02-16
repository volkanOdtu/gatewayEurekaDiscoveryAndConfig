package pl.piomin.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.model.Employee;
import pl.piomin.services.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository repository;
	
	@PostMapping("/")
	public Employee add(@RequestBody Employee employee) {
		 return repository.add(employee);
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	
	@GetMapping("/")
	public List<Employee> findAll() {
		 return repository.findAll();
	}
	
	@GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
		 return repository.findByDepartment(departmentId);
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		 return repository.findByOrganization(organizationId);
	}
	
}
