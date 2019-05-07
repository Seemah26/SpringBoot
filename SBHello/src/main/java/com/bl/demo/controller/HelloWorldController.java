package com.bl.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

	 @GetMapping("/hello")
	    public String sayHello() {
		 System.out.println("Heyyy");
	        return "Hi I am Seema!";
	    }
	 
	 @GetMapping("/")
	    public String sayHello1() {
		 System.out.println("Heyyy");
	        return "Hi I am aaaaaaaaaa!";
	    }
}
