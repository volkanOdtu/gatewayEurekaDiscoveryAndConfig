package pl.piomin.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.client.EmployeeClient;
import pl.piomin.services.model.Department;
import pl.piomin.services.repository.DepartmentRepository;

@RestController
public class DepartmentController {
	
	@Autowired
	DepartmentRepository repository;// departmentRepository;
	
	@Autowired
	EmployeeClient employeeClient;
	
	@PostMapping("/")
	public Department add(@RequestBody Department department) {
		return repository.add(department);
	}
	
	@GetMapping("/{id}")	
	public Department findById(@PathVariable("id") Long id) {
		 return repository.findById(id);
	}
	
	@GetMapping("/")
	public List<Department> findAll(){
		 return repository.findAll();
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId){
		return repository.findByOrganization(organizationId);
	}
	
	@GetMapping("/organization/{organizationId}/with-employees")
	public List<Department> findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId){
		List<Department> departments = repository.findByOrganization(organizationId);
		departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
		
		return departments;
	}
	
}
