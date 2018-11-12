package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
class Employee {
	private @Id @GeneratedValue Long id;
	private String name;
	private String role;
	
	
	public Employee() {}
	public Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setName(String v) {
		this.name = v;
	}
	
	public void setRole(String v) {
		this.role = v;
	}
}