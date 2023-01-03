package de.ontourforjesus.buzzerpi;

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
	
	private final int[] buzzerInputs;
	private final int[] buzzerOutputs;
	
	public DigitalInputDetector(
			@Value("${pinconfiguration.buzzer1.in}") int b1in,
			@Value("${pinconfiguration.buzzer1.out}") int b1out,
			@Value("${pinconfiguration.buzzer2.in}") int b2in,
			@Value("${pinconfiguration.buzzer2.out}") int b2out,
			@Value("${pinconfiguration.buzzer3.in}") int b3in,
			@Value("${pinconfiguration.buzzer3.out}") int b3out
			) {
		
			buzzerInputs = new int[] {b1in,b2in,b3in};
			buzzerOutputs = new int[] {b1out,b2out,b3out};
			
			pi4j = Pi4J.newAutoContext();
			
			setOutputPinsHigh();
			
			DigitalInputConfigBuilder baseConfig = DigitalInput
					.newConfigBuilder(pi4j)
					.provider("pigpio-digital-input")
					.pull(PullResistance.PULL_DOWN);
			
			var input1 = pi4j.din().create(baseConfig.address(buzzerInputs[0]).name("b1in").id("b1in"));
			var input2 = pi4j.din().create(baseConfig.address(buzzerInputs[1]).name("b2in").id("b2in"));
			var input3 = pi4j.din().create(baseConfig.address(buzzerInputs[2]).name("b3in").id("b3in"));
			
/*			for(int i = 0; i<3; i++) {
			
				allInputs[i] = pi4j
						.din().create(baseConfig
								.address(buzzerInputs[i])
								.id("b" + buzzerInputs[i] + "in")
								.name("Buzzer" + buzzerInputs[i] + "In")
						);
				
			}*/
			
			System.out.println(input1.state());
			System.out.println(input2.state());
			System.out.println(input3.state());
			
			input1.addListener(e -> {
				if(e.state() == DigitalState.HIGH) {
					System.out.println("Buzzer 1 Digital State => HIGH");
					informAllRegisteredListeners(1);
				}
			});
			input2.addListener(e -> {
				if(e.state() == DigitalState.HIGH) {
					System.out.println("Buzzer 2 Digital State => HIGH");
					informAllRegisteredListeners(2);
				}
			});
			input3.addListener(e -> {
				if(e.state() == DigitalState.HIGH) {
					System.out.println("Buzzer 3 Digital State => HIGH");
					informAllRegisteredListeners(3);
				}
			});
		
	}
	
	public void registerListener(DigitalInputListener digitalInputListener) {
	
		this.registeredListeners.add(digitalInputListener);
		
	}
	
	private void informAllRegisteredListeners(int pressedBuzzer) {
		for(int i = 0; i<registeredListeners.size(); i++) {
			registeredListeners.get(i).inform(pressedBuzzer);
		}
	}
	
	private void setOutputPinsHigh() {
		
		DigitalOutputConfigBuilder baseConfig = DigitalOutput.newConfigBuilder(pi4j).shutdown(DigitalState.LOW).initial(DigitalState.HIGH).provider("pigpio-digital-output");
		
		DigitalOutput output1 = pi4j.dout().create(baseConfig.address(buzzerOutputs[0]).id("b1out").name("Buzzer1Out"));
		DigitalOutput output2 = pi4j.dout().create(baseConfig.address(buzzerOutputs[1]).id("b2out").name("Buzzer2Out"));
		DigitalOutput output3 = pi4j.dout().create(baseConfig.address(buzzerOutputs[2]).id("b3out").name("Buzzer3Out"));
	}
	
	
}
