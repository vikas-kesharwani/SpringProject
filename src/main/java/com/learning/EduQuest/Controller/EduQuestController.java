package com.learning.EduQuest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EduQuest")
public class EduQuestController {
	
	//Get Started
	@GetMapping("/hi")
	public String displayHi() {
		return "Hey! welcome";
	} 

}
