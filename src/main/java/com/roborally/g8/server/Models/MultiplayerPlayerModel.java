package com.roborally.g8.server.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * This class represents a multiplayer player in the game. It encapsulates player-specific
 * details such as id, name, IP address, and a list of checkpoints for the player.
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class MultiplayerPlayerModel {

    private int id;
    private String name;
    private String ipAddress;
    private List<Integer> checkPoints;

 /**
     * Default constructor. Required for conversion from JSON file to object.
     */

    public MultiplayerPlayerModel() {
        
    }

    /**
     * A constructor to initialize all the fields of the MultiplayerPlayerModel.
     *
     * @param id The unique identifier for the player
     * @param name The name of the player
     * @param ipAddress The IP address of the player
     * @param checkPoints The list of checkpoints that the player has crossed
     */

    public MultiplayerPlayerModel(int id, String name, String ipAddress, List<Integer> checkPoints) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.checkPoints = checkPoints;
    }

    public int GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public List<Integer> GetCheckPoints() {
        return checkPoints;
    }
}
