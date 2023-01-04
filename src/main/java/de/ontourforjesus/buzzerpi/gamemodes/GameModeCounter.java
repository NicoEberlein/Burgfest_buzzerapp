package de.ontourforjesus.buzzerpi.gamemodes;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import de.ontourforjesus.buzzerpi.DigitalInputDetector;
import de.ontourforjesus.buzzerpi.DigitalInputListener;
import de.ontourforjesus.buzzerpi.GameModeCounterData;
import de.ontourforjesus.buzzerpi.GameModeData;

@Component
@Primary
public class GameModeCounter extends GameMode implements DigitalInputListener{
	
	private GameModeCounterData data;
	
	public GameModeCounter(DigitalInputDetector digitalInputDetector) {
		
		data = new GameModeCounterData();
		digitalInputDetector.registerListener(this);
		
	}
	
	@Override
	public void inform(int pressedBuzzer) {
		
		data.incrementSingleBuzzerCounter(pressedBuzzer);
		
	}
	
	@Override
	public void reset() {
		
		data.reset();
		
	}

	@Override
	public GameModeData getGameModeData() {
		return data;
	}
	
	
}
