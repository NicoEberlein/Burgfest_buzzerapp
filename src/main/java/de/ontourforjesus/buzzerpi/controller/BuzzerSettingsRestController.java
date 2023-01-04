package de.ontourforjesus.buzzerpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.ontourforjesus.buzzerpi.DigitalInputDetector;
import de.ontourforjesus.buzzerpi.GameModeData;
import de.ontourforjesus.buzzerpi.gamemodes.GameMode;
import de.ontourforjesus.buzzerpi.gamemodes.GameModeCounter;
import de.ontourforjesus.buzzerpi.gamemodes.GameModeWhoWasFirst;

@RestController
@RequestMapping("/rest")
public class BuzzerSettingsRestController {

	private DigitalInputDetector digitalInputDetector;
	
	private GameMode currentGameMode;
	
	public BuzzerSettingsRestController(@Autowired DigitalInputDetector digitalInputDetector) {
		
		this.digitalInputDetector = digitalInputDetector;
		currentGameMode = new GameModeCounter(this.digitalInputDetector);
		
	}
	
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
		
		if(gamemode.equals("counter")) {
			currentGameMode = new GameModeCounter(digitalInputDetector);
		}else if(gamemode.equals("whowasfirst")) {
			currentGameMode = new GameModeWhoWasFirst(digitalInputDetector);
		}else {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/reset")
	@CrossOrigin
	public ResponseEntity<Object> reset() {
		
		System.out.println("Reset request was received");
		currentGameMode.reset();
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/data")
	@CrossOrigin
	public ResponseEntity<GameModeData> buzzerdata() {
		return ResponseEntity.ok(currentGameMode.getGameModeData());
	}
	
	@GetMapping("/getTeamNames")
	@CrossOrigin
	public String[] getTeamNames() {
		return teamnames;
	}
	
}






