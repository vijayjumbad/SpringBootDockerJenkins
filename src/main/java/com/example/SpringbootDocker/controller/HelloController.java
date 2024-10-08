package com.example.SpringbootDocker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String getMsg() {
		return "welcome to the docker..!!!";
	}
	
	@GetMapping("/jenkins")
	public String msg() {
		return "welcome to the docker jenkins integration..!!!";
	}

}
