package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class projectController {

	@GetMapping("/")
	public String index() {

		return "index";
	}

	@GetMapping("/about")
	public String aboutpage() {
		return "about";
	}

	@GetMapping("/team")
	public String team() {
		return "team";
	}
	@GetMapping("/events")
	public String events() {

		return "events";
	}
	@GetMapping("/partner")
	public String partner() {
		return "partner";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
}
