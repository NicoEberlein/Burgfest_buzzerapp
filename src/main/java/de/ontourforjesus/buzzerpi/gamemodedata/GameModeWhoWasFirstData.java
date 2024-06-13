package de.ontourforjesus.buzzerpi.gamemodedata;

public class GameModeWhoWasFirstData extends GameModeData{

	private final GameModeType gameModeType = GameModeType.WhoWasFirst;
	
	//No one has pressed a buzzer yet: firstBuzzer = -1
	private int firstBuzzer;
	
	private String teamBackgroundColor;
	
	private final String[] teamBackgroundColors = new String[] {"#008000", "#FFFF00","#FF0000"};
	
	public GameModeWhoWasFirstData() {
		firstBuzzer = -1;
		teamBackgroundColor = null;
	}
	
	public void setWhoBuzzeredFirst(int whoBuzzeredFirst) {
		
		firstBuzzer = whoBuzzeredFirst;
		teamBackgroundColor = teamBackgroundColors[whoBuzzeredFirst];
		
	}
	
	public int getFirstBuzzer() {
		return firstBuzzer;
	}
	
	public String getTeamBackgroundColor() {
		return teamBackgroundColor;
	}

		public GameModeType getGameModeType() {
		return gameModeType;
	}
	
}
