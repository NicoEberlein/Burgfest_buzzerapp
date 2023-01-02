package de.ontourforjesus.buzzerpi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuzzerController {

	@RequestMapping("/")
	public String getBuzzerVisualizationPage() {
		return "buzzerpage";
	}
	
}
