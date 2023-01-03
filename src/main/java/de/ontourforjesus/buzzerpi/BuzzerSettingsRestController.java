package de.ontourforjesus.buzzerpi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class BuzzerSettingsRestController {

	@Autowired
	GameModeCounter gameModeCounter;
	
	private String[] teamnames = new String[] {"Team 1", "Team 2", "Team 3"};
	
	@PostMapping(value = "/settings", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@CrossOrigin
	public ResponseEntity<Object> setSettings(
			@RequestParam("gamemode") String gamemode,
			@RequestParam("teamname1") String teamname1,
			@RequestParam("teamname2") String teamname2,
			@RequestParam("teamname3") String teamname3) {
		
		teamnames[0] = teamname1;
		teamnames[1] = teamname2;
		teamnames[2] = teamname3;
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/reset")
	@CrossOrigin
	public ResponseEntity<Object> reset() {
		
		gameModeCounter.reset();
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/data")
	@CrossOrigin
	public int[] buzzerdata() {
		return gameModeCounter.getBuzzerCounter();
	}
	
	@GetMapping("/getTeamNames")
	@CrossOrigin
	public String[] getTeamNames() {
		return teamnames;
	}
	
}






