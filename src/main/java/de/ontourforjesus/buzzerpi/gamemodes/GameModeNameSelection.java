package de.ontourforjesus.buzzerpi.gamemodes;

import java.util.ArrayList;

import de.ontourforjesus.buzzerpi.gamemodedata.GameModeData;
import de.ontourforjesus.buzzerpi.gamemodedata.GameModeNameSelectionData;
import de.ontourforjesus.buzzerpi.io.DigitalInputDetector;
import de.ontourforjesus.buzzerpi.io.DigitalInputListener;

public class GameModeNameSelection extends GameMode implements DigitalInputListener {

    private GameModeNameSelectionData data;

    public GameModeNameSelection(DigitalInputDetector digitalInputDetector) {

        data = new GameModeNameSelectionData();
        digitalInputDetector.registerListener(this);

    }

    @Override
    public void inform(int pressedBuzzer) {
        data.incrementLastPressId();
    }

    @Override
    public void reset() {
        data = new GameModeNameSelectionData();
    }

    @Override
    public GameModeData getGameModeData() {
        return data;
    }

    public void addName(String name) {
        data.addName(name);
    }

    public void removeName(String name) {
        data.removeName(name);
    }

    public void setNames(ArrayList<String> names) {
        data.setNames(names);
    }
    
}
