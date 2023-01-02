package de.ontourforjesus.buzzerpi;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalInputConfig;
import com.pi4j.io.gpio.digital.DigitalInputProvider;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

@Component
public class DigitalInputDetector{
	
	private Context pi4j;
	
	private final int b1in;
	private final int b1out;
	
	private final int b2in;
	private final int b2out;
	
	private final int b3in;
	private final int b3out;
	
	private int b1counter = 0;
	private int b2counter = 0;
	private int b3counter = 0;
	
	public DigitalInputDetector(
			@Value("${pinconfiguration.buzzer1.in}") int b1in,
			@Value("${pinconfiguration.buzzer1.out}") int b1out,
			@Value("${pinconfiguration.buzzer2.in}") int b2in,
			@Value("${pinconfiguration.buzzer2.out}") int b2out,
			@Value("${pinconfiguration.buzzer3.in}") int b3in,
			@Value("${pinconfiguration.buzzer3.out}") int b3out
			) {
		
			this.b1in = b1in;
			this.b1out = b1out;
			
			this.b2in = b2in;
			this.b2out = b2out;
			
			this.b3in = b3in;
			this.b3out = b3out;
			
			pi4j = Pi4J.newAutoContext();
			DigitalInputProvider provider = pi4j.provider("pigpio-digital-input");
			
			setOutputPinsHigh();
			
			Properties properties1 = new Properties();
			properties1.put("id", "buzzer1in");
			properties1.put("address", b1in);
			properties1.put("pull", "down");
			properties1.put("name", "buzzer1Input");
			DigitalInputConfig config1 = DigitalInput.newConfigBuilder(pi4j).load(properties1).build();
			var input1 = provider.create(config1);
			
			System.out.println(input1.state());
			
			input1.addListener(e -> {
				if(e.state() == DigitalState.HIGH) {
					b1counter++;
					printCounters();
				}
			});
			
			Properties properties2 = new Properties();
			properties2.put("id", "buzzer2in");
			properties2.put("address", b2in);
			properties2.put("pull", "down");
			properties2.put("name", "buzzer1Input");
			DigitalInputConfig config2 = DigitalInput.newConfigBuilder(pi4j).load(properties2).build();
			var input2 = provider.create(config2);
			
			System.out.println(input2.state());
			
			input2.addListener(e -> {
				if(e.state() == DigitalState.HIGH) {
					b2counter++;
					printCounters();
				}
			});
			
			Properties properties3 = new Properties();
			properties3.put("id", "buzzer3in");
			properties3.put("address", b3in);
			properties3.put("pull", "down");
			properties3.put("name", "buzzer1Input");
			DigitalInputConfig config3 = DigitalInput.newConfigBuilder(pi4j).load(properties3).build();
			var input3 = provider.create(config3);
			
			System.out.println(input3.state());
			
			input3.addListener(e -> {
				if(e.state() == DigitalState.HIGH) {
					b3counter++;
					printCounters();
				}
			});
		
	}
	
	private void setOutputPinsHigh() {
		
		var config1 = DigitalOutput.newConfigBuilder(pi4j).id("dout1").name("Dout1").address(b1out).shutdown(DigitalState.LOW).initial(DigitalState.HIGH).provider("pigpio-digital-output");
		var config2 = DigitalOutput.newConfigBuilder(pi4j).id("dout2").name("Dout2").address(b2out).shutdown(DigitalState.LOW).initial(DigitalState.HIGH).provider("pigpio-digital-output");
		var config3 = DigitalOutput.newConfigBuilder(pi4j).id("dout3").name("Dout3").address(b3out).shutdown(DigitalState.LOW).initial(DigitalState.HIGH).provider("pigpio-digital-output");
	
		var output1 = pi4j.create(config1);
		var output2 = pi4j.create(config2);
		var output3 = pi4j.create(config3);
	}
	
	private void printCounters() {
		System.out.println("Buzzer 1: " + b1counter);
		System.out.println("Buzzer 2: " + b2counter);
		System.out.println("Buzzer 3: " + b3counter);
	}

	public int getB1counter() {
		return b1counter;
	}

	public int getB2counter() {
		return b2counter;
	}

	public int getB3counter() {
		return b3counter;
	}

	
	
}
