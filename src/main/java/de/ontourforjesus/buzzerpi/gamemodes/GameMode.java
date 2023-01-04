package de.ontourforjesus.buzzerpi.gamemodes;

import org.springframework.stereotype.Component;

import de.ontourforjesus.buzzerpi.GameModeData;

@Component
public abstract class GameMode {

	public abstract void reset();
	
	public abstract GameModeData getGameModeData();
	
}
