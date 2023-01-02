package de.ontourforjesus.buzzerpi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuzzerDataRestController {

	@Autowired
	DigitalInputDetector digitalInputDetector;
	
	@GetMapping("/data")
	public ResponseEntity<int[]> getBuzzerData() {
		return ResponseEntity.ok(new int[] {
				digitalInputDetector.getB1counter(),
				digitalInputDetector.getB2counter(),
				digitalInputDetector.getB3counter()
		});
	}
	
}
