package xyz.bhrnsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.bhrnsw.beans.Employee;
import xyz.bhrnsw.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService es;
	
	@RequestMapping("/information")
	public void findById(Integer id){
		System.out.println("-----------***************");
		Employee e = es.getInformationById(id);
		System.out.println(e.getLastName());
	}
}
