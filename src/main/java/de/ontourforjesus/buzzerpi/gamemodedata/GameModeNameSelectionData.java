package de.ontourforjesus.buzzerpi.gamemodedata;

import java.util.ArrayList;

public class GameModeNameSelectionData extends GameModeData {
    
    private final GameModeType gameModeType = GameModeType.NameSelection;

    private ArrayList<String> names;
    private int lastPressId;

    public GameModeNameSelectionData(ArrayList<String> names, int lastPressId) {
        names.add("Test 1");
        names.add("Test 2");
        names.add("Test 3");
        this.names = names;
        this.lastPressId = lastPressId;
    }

    public GameModeNameSelectionData() {
        this(new ArrayList<String>(), 0);
    }


    public void incrementLastPressId() {
        lastPressId++;
    }

    public void addName(String name) {
        names.add(name);
    }

    public void removeName(String name) {
        names.remove(name);
    }
    

    public int getLastPressId() {
        return lastPressId;
    }

    public void setLastPressId(int lastPressId) {
        this.lastPressId = lastPressId;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public GameModeType getGameModeType() {
		return gameModeType;
	}

}
