package de.ontourforjesus.buzzerpi.io;

@FunctionalInterface
public interface DigitalInputListener {

	public void inform(int pressedBuzzer);
	
}
