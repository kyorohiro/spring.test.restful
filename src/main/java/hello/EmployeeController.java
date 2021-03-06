package hello;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class EmployeeController {
	private final EmployeeRepository repository;
	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/employees")
	List<Employee> all() {
		return repository.findAll();
	}

	@GetMapping("/employees/{id}")
	Resource<Employee> one(@PathVariable(name="id") Long id) {
		Employee employee  = repository.findById(id).orElseThrow(
					() -> new EmployeeController.EmployeeNotFoundException(id)
				);
		
		//
		//
		// hateoas
		return new Resource<Employee>(employee,
				linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
	}

	@RequestMapping(path= "/employees", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}
	
	@PutMapping("/employees/{id}")	
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return repository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					return repository.save(employee);
				})
				.orElseThrow(
					() -> new EmployeeController.EmployeeNotFoundException(id)
				);
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@SuppressWarnings("serial")
	static class EmployeeNotFoundException extends RuntimeException {
		EmployeeNotFoundException(Long id) {
			super("Could not find employee " + id);
		}
	}
	
}

