package pl.piomin.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.client.DepartmentClient;
import pl.piomin.services.client.EmployeeClient;
import pl.piomin.services.model.Organization;
import pl.piomin.services.repository.OrganizationRepository;

@RestController
public class OrganizationController {

	@Autowired
	OrganizationRepository repository;
	
	@Autowired
	DepartmentClient departmentClient;
	
	@Autowired
	EmployeeClient employeeClient;
	
	@PostMapping
	public Organization add(@RequestBody Organization organization) {
		return repository.add(organization);
	}
	
	@GetMapping
	public List<Organization> findAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Organization findById(@PathVariable("id") Long id) {
		 return repository.findById(id);
	}
	
	@GetMapping("/{id}/with-departments")
	public Organization findByIdWithDepartments(@PathVariable("id") Long id) {
		Organization organization = repository.findById(id);
		organization.setDepartments(departmentClient.findByOrganization(organization.getId()));
		return organization;
	}
	
	@GetMapping("/{id}/with-departments-and-employees")
	public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
		Organization organization = repository.findById(id);
		organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
		return organization;
	}
	
	@GetMapping("/{id}/with-employees")
	public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
		Organization organization = repository.findById(id);
		organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
		return organization;
	}
}
