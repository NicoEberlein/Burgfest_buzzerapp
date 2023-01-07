package de.ontourforjesus.buzzerpi.gamemodes;

import de.ontourforjesus.buzzerpi.gamemodedata.GameModeCounterData;
import de.ontourforjesus.buzzerpi.gamemodedata.GameModeData;
import de.ontourforjesus.buzzerpi.io.DigitalInputDetector;
import de.ontourforjesus.buzzerpi.io.DigitalInputListener;

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
	
	public void setSingleBuzzerCounter(int buzzer, int counter) {
		
		data.setSingleBuzzerCounter(buzzer, counter);
		
	}
	
	public void incrementSingleBuzzerCounter(int buzzer) {
		
		data.incrementSingleBuzzerCounter(buzzer);
		
	}
	
	public void decrementSingleBuzzerCounter(int buzzer) {
		
		data.decrementSingleBuzzerCounter(buzzer);
		
	}
	
	@Override
	public void reset() {
		
		data = new GameModeCounterData();
		
	}

	@Override
	public GameModeData getGameModeData() {
		return data;
	}
	
	
}
