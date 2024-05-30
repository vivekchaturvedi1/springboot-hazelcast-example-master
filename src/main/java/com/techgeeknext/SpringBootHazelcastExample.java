package com.techgeeknext;

import com.techgeeknext.model.Employee;
import com.techgeeknext.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class SpringBootHazelcastExample {
	
	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootHazelcastExample.class, args);
		EmployeeService employeeService = context.getBean(EmployeeService.class);
		// Insert Employees in the Table
		Employee emp= new Employee();
		emp.setEmpId("111");
		emp.setEmpName("John");
		
		Employee emp1= new Employee();
		emp1.setEmpId("222");
		emp1.setEmpName("Miler");
		
		Employee emp2= new Employee();
		emp2.setEmpId("333");
		emp2.setEmpName("Nick");

		employeeService.insertEmployee(emp);

		List<Employee> employees = new ArrayList<>();
		employees.add(emp1);
		employees.add(emp2);
		employeeService.insertEmployees(employees);

		System.out.println("Main Class - First Time retrieving Employee Record from Service Class");
		employeeService.getAllEmployees().forEach(employee-> System.out.println(employee.toString()));

		System.out.println("Main Class - Second Time onwards retrieving Employee Record from Hazelcast");
		employeeService.getAllEmployees().forEach(employee-> System.out.println(employee.toString()));
	}
}