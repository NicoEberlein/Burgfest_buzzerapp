package de.ontourforjesus.buzzerpi.io;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;

@Component
public class DigitalInputDetector{
	
	private ArrayList<DigitalInputListener> registeredListeners = new ArrayList<>();
	
	private Context pi4j;
	private DigitalInput[] allInputs;
	
	private final int[] buzzerInputs;
	private final int[] buzzerOutputs;
	
	public DigitalInputDetector(
			@Value("${pinconfiguration.buzzer0.in}") int b0in,
			@Value("${pinconfiguration.buzzer0.out}") int b0out,
			@Value("${pinconfiguration.buzzer1.in}") int b1in,
			@Value("${pinconfiguration.buzzer1.out}") int b1out,
			@Value("${pinconfiguration.buzzer2.in}") int b2in,
			@Value("${pinconfiguration.buzzer2.out}") int b2out
			) {
		
			buzzerInputs = new int[] {b0in,b1in,b2in};
			buzzerOutputs = new int[] {b0out,b1out,b2out};
			
			pi4j = Pi4J.newAutoContext();
			
			setupOutputPins();
			setupInputPins();
		
	}
	
	public void registerListener(DigitalInputListener digitalInputListener) {
	
		this.registeredListeners.add(digitalInputListener);
		System.out.println("Registered new DigitalInputListener");
		
	}
	
	public void removeListener(DigitalInputListener digitalInputListener) {
		
		this.registeredListeners.remove(digitalInputListener);
		System.out.println("Removed a registered listener");
		
	}
	
	private void informAllRegisteredListeners(int pressedBuzzer) {
		for(int i = 0; i<registeredListeners.size(); i++) {
			System.out.println("Inform listener " + i + " that buzzer " + pressedBuzzer + " state changed to DigitalState.HIGH");
			registeredListeners.get(i).inform(pressedBuzzer);
		}
	}
	
	private void setupOutputPins() {
		
		DigitalOutputConfigBuilder baseConfig = DigitalOutput.newConfigBuilder(pi4j).shutdown(DigitalState.LOW).initial(DigitalState.HIGH).provider("pigpio-digital-output");
		
		DigitalOutput output1 = pi4j.create(baseConfig.address(buzzerOutputs[0]).id("b0out").name("Buzzer0Out"));
		DigitalOutput output2 = pi4j.create(baseConfig.address(buzzerOutputs[1]).id("b1out").name("Buzzer1Out"));
		DigitalOutput output3 = pi4j.create(baseConfig.address(buzzerOutputs[2]).id("b2out").name("Buzzer2Out"));
	}
	
	private void setupInputPins() {
		
		DigitalInputConfigBuilder baseConfig = DigitalInput
				.newConfigBuilder(pi4j)
				.provider("pigpio-digital-input")
				.pull(PullResistance.PULL_DOWN);
		
		allInputs = new DigitalInput[3];
		
		for(int i = 0; i<3; i++) {
		
			allInputs[i] = pi4j
					.create(baseConfig
							.address(buzzerInputs[i])
							.id("b" + i + "in")
							.name("Buzzer" + i + "In")
					);
			
		}
		
		allInputs[0].addListener(e -> {
			if(e.state() == DigitalState.HIGH) {
				System.out.println("Buzzer 0 Digital State => HIGH");
				informAllRegisteredListeners(0);
			}
		});
		allInputs[1].addListener(e -> {
			if(e.state() == DigitalState.HIGH) {
				System.out.println("Buzzer 1 Digital State => HIGH");
				informAllRegisteredListeners(1);
			}
		});
		allInputs[2].addListener(e -> {
			if(e.state() == DigitalState.HIGH) {
				System.out.println("Buzzer 2 Digital State => HIGH");
				informAllRegisteredListeners(2);
			}
		});
		
	}
	
	
}
