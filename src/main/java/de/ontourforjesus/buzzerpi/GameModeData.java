package de.ontourforjesus.buzzerpi;

public abstract class GameModeData {

	protected enum GameModeType {
		Counter,
		WhoWasFirst
	}
	
	public abstract void reset();
	
}
