package de.ontourforjesus.buzzerpi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameModeCounter implements DigitalInputListener{

	@Autowired
	private DigitalInputDetector digitalInputDetector;
	
	private int[] buzzerCounter;
	
	public GameModeCounter() {
		
		buzzerCounter = new int[3];
		digitalInputDetector.registerListener(this);
		
	}
	
	@Override
	public void inform(int pressedBuzzer) {
		buzzerCounter[pressedBuzzer]++;	
	}
	
	public int[] getBuzzerCounter() {
		return buzzerCounter;
	}
	
	public void reset() {
		buzzerCounter = new int[3];
	}
	
	

	
	
}
