package com.roborally.g8.server.Models;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * Class representing the state of a game on the server side.
 * This model is used for communication between the client and the server.
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ServerModel {

    private String name;
    private String boardName;
    private String currentPlayerName;
    private String gameName;
    private String phase;
    private int step;
    private Map<String, Object>[] players;
    private MultiplayerModel multiplayerModel;

  /**
     * Default constructor. Required for conversion from JSON file to object.
     */

    public ServerModel() {
        
    }

/**
     * Constructor to initialize all fields.
     */

    public ServerModel(String name, String boardName, String currentPlayerName, String gameName, String phase, int step,
            Map<String, Object>[] players, MultiplayerModel multiplayerModel) {
        this.name = name;
        this.boardName = boardName;
        this.currentPlayerName = currentPlayerName;
        this.gameName = gameName;
        this.phase = phase;
        this.step = step;
        this.players = players;
        this.multiplayerModel = multiplayerModel;
    }

    public String GetName() {
        return name;
    }

    public String GetBoardName() {
        return boardName;
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

    public MultiplayerModel GetMultiplayerModel() {
        return multiplayerModel;
    }

    public void SetMultiplayerModel(MultiplayerModel multiplayerModel) {
        this.multiplayerModel = multiplayerModel;
    }
}
