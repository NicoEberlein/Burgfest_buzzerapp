package de.ontourforjesus.buzzerpi;

public class GameModeWhoWasFirstData extends GameModeData{

	private static GameModeType gameModeType = GameModeType.WhoWasFirst;
	
	//No one has pressed a buzzer yet: firstBuzzer = 0
	private int firstBuzzer;
	
	public GameModeWhoWasFirstData() {
		firstBuzzer = 0;
	}

	@Override
	public void reset() {
		
		firstBuzzer = 0;
		
	}
	
	public void setWhoBuzzeredFirst(int whoBuzzeredFirst) {
		
		firstBuzzer = whoBuzzeredFirst;
		
	}
	
	
	
	
	
}
