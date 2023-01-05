package de.ontourforjesus.buzzerpi.gamemodes;

import org.springframework.stereotype.Component;

import de.ontourforjesus.buzzerpi.gamemodedata.GameModeData;
import de.ontourforjesus.buzzerpi.gamemodedata.GameModeWhoWasFirstData;
import de.ontourforjesus.buzzerpi.io.DigitalInputDetector;
import de.ontourforjesus.buzzerpi.io.DigitalInputListener;

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
