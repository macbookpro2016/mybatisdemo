package xyz.bhrnsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.bhrnsw.beans.User;
import xyz.bhrnsw.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService ts;
	@RequestMapping("/test")
	public void test(){
		System.out.println("TEst");
		User u = ts.test();
		System.out.println(u.getUserName());
	}
}
