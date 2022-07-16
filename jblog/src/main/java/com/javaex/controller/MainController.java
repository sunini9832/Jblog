package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	/*JBlog 메인 폼 출력*/
	@GetMapping("/")
	public String main() {
		return "main/index";
	}


}
