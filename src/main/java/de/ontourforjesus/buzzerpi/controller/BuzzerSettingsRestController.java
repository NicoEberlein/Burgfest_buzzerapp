package de.ontourforjesus.buzzerpi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.ontourforjesus.buzzerpi.gamemodedata.GameModeCounterData;
import de.ontourforjesus.buzzerpi.gamemodedata.GameModeData;
import de.ontourforjesus.buzzerpi.gamemodes.GameMode;
import de.ontourforjesus.buzzerpi.gamemodes.GameModeCounter;
import de.ontourforjesus.buzzerpi.gamemodes.GameModeWhoWasFirst;
import de.ontourforjesus.buzzerpi.io.DigitalInputDetector;

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
			@RequestParam("teamname1") Optional<String> teamname1,
			@RequestParam("teamname2") Optional<String> teamname2,
			@RequestParam("teamname3") Optional<String> teamname3,
			@RequestParam("scoreteam1") Optional<Integer> scoreTeam1,
			@RequestParam("scoreteam2") Optional<Integer> scoreTeam2,
			@RequestParam("scoreteam3") Optional<Integer> scoreTeam3){
		
		teamname1.ifPresent((value) -> {teamnames[0] = value;});
		teamname2.ifPresent((value) -> {teamnames[1] = value;});
		teamname3.ifPresent((value) -> {teamnames[2] = value;});
		
		if(gamemode.equals("counter")) {
			
			if(!(currentGameMode instanceof GameModeCounter)) {
				currentGameMode = new GameModeCounter(digitalInputDetector);
			}
			
			GameModeCounter gameModeCounter = (GameModeCounter) currentGameMode;
			
			scoreTeam1.ifPresent((value) -> gameModeCounter.setSingleBuzzerCounter(0, value));
			scoreTeam2.ifPresent((value) -> gameModeCounter.setSingleBuzzerCounter(1, value));
			scoreTeam3.ifPresent((value) -> gameModeCounter.setSingleBuzzerCounter(2, value));	
			
			
		}else if(gamemode.equals("whowasfirst")) {
			
			if(!(currentGameMode instanceof GameModeWhoWasFirst)) {
				currentGameMode = new GameModeWhoWasFirst(digitalInputDetector);
			}
			
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
	
	@CrossOrigin
	@GetMapping("/incrementBuzzer/{buzzer}")
	public ResponseEntity<Object> incrementBuzzer(@PathVariable("buzzer") int buzzer) {
		
		System.out.println("Incrementing buzzer " + buzzer);
		
		if(currentGameMode instanceof GameModeCounter) {
			((GameModeCounter) currentGameMode).incrementSingleBuzzerCounter(buzzer);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@CrossOrigin
	@GetMapping("/decrementBuzzer/{buzzer}")
	public ResponseEntity<Object> decrementBuzzer(@PathVariable("buzzer") int buzzer) {
		
		System.out.println("Decrementing buzzer " + buzzer);
		
		if(currentGameMode instanceof GameModeCounter) {
			((GameModeCounter) currentGameMode).decrementSingleBuzzerCounter(buzzer);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
}






