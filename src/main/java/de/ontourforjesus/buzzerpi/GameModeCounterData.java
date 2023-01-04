package de.ontourforjesus.buzzerpi;

public class GameModeCounterData extends GameModeData{

	private static GameModeType gameModeType = GameModeType.Counter;
	
	private int[] buzzerCounter;
	
	public GameModeCounterData(int buzzer1Counter, int buzzer2Counter, int buzzer3Counter) {
	
		buzzerCounter = new int[] {buzzer1Counter, buzzer2Counter, buzzer3Counter};
		
	}
	
	public GameModeCounterData() {
		this(0, 0, 0);
	}
	
	public int[] getBuzzerCounter() {
		return buzzerCounter;
	}
	
	public void setSingleBuzzerCounter(int buzzer, int count) {
		buzzerCounter[buzzer] = count;
	}
	
	public void incrementSingleBuzzerCounter(int buzzer) {
		buzzerCounter[buzzer]++;
	}
	
	public void decrementSingleBuzzerCounter(int buzzer) {
		buzzerCounter[buzzer]--;
	}
	
	@Override
	public void reset() {
		buzzerCounter = new int[3];
	}
	
	
}
