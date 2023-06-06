package com.roborally.g8.server.Models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.sym.Name;

/**
 * ...
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ServerModel {

    private String name;
    private String currentPlayerName;
    private String gameName;
    private String phase;
    private int step;
    private Map<String, Object>[] players;

    public ServerModel() {
        // Needed to convert from json file to object
    }

    public ServerModel(String name, String currentPlayerName, String gameName, String phase, int step,
            Map<String, Object>[] players) {
        this.name = name;
        this.currentPlayerName = currentPlayerName;
        this.gameName = gameName;
        this.phase = phase;
        this.step = step;
        this.players = players;
    }

    public String GetName() {
        return name;
    }

    public String GetCurrentPlayerName() {
        return currentPlayerName;
    }

    public String GetGameName() {
        return gameName;
    }

    public String GetPhase() {
        return phase;
    }

    public int GetStep() {
        return step;
    }

    public Map<String, Object>[] GetPlayers() {
        return players;
    }
}
