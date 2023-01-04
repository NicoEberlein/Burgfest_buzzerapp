package de.ontourforjesus.buzzerpi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuzzerController {
	
	@RequestMapping("/")
	public String getBuzzerVisualizationPage() {
		
		return "index";
	}
	
	@RequestMapping("/settings")
	public String getBuzzerSettingsPage() {
		return "settings";
	}
	
}
