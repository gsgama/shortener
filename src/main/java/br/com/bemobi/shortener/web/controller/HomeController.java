package br.com.bemobi.shortener.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping
	public String index() {
		return "Hello Guigão!";
	}
	
}
