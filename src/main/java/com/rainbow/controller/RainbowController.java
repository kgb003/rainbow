package com.rainbow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RainbowController {
	
	@GetMapping("/")
	public String main(Model model){
		
		return "index";
	}
}
