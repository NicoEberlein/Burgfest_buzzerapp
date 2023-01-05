package de.ontourforjesus.buzzerpi.gamemodedata;

public class GameModeWhoWasFirstData extends GameModeData{

	private final GameModeType gameModeType = GameModeType.WhoWasFirst;
	
	//No one has pressed a buzzer yet: firstBuzzer = -1
	private int firstBuzzer;
	
	public GameModeWhoWasFirstData() {
		firstBuzzer = -1;
	}
	
	public void setWhoBuzzeredFirst(int whoBuzzeredFirst) {
		
		firstBuzzer = whoBuzzeredFirst;
		
	}
	
	public int getFirstBuzzer() {
		return firstBuzzer;
	}
	
	public GameModeType getGameModeType() {
		return gameModeType;
	}
	
	
	
	
	
}
