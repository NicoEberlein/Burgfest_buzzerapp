package de.ontourforjesus.buzzerpi.gamemodes;

import org.springframework.stereotype.Component;

import de.ontourforjesus.buzzerpi.DigitalInputDetector;
import de.ontourforjesus.buzzerpi.DigitalInputListener;
import de.ontourforjesus.buzzerpi.GameModeData;
import de.ontourforjesus.buzzerpi.GameModeWhoWasFirstData;

@Component
public class GameModeWhoWasFirst extends GameMode implements DigitalInputListener{	
	
	private GameModeWhoWasFirstData data;
	
	public GameModeWhoWasFirst(DigitalInputDetector digitalInputDetector) {
		
		data = new GameModeWhoWasFirstData();
		digitalInputDetector.registerListener(this);
		
	}
	
	@Override
	public void inform(int pressedBuzzer) {
		
	}
	
	public void reset() {
		data.reset();
	}

	public GameModeData getGameModeData() {
		return data;
	}
	
	
}
