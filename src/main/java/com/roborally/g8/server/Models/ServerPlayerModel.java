package com.roborally.g8.server.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * Class representing a player in the RoboRally game on the server side.
 * This model is used for communication between the client and the server.
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ServerPlayerModel {

    private String name; // Use as ID
    private String color;

    private int positionX; // Set as Space
    private int positionY; // Set as Space

    private String heading;

    private List<String> commands;

    private List<Integer> checkPoints;

 /**
     * Default constructor.
     */

    public ServerPlayerModel() {
    }

   /**
     * Constructor to initialize all fields.
     */

    public ServerPlayerModel(String name, String color, int positionX, int positionY, String heading,
            List<String> commands, List<Integer> checkPoints) {
        this.name = name;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.heading = heading;
        this.commands = commands;
        this.checkPoints = checkPoints;
    }

    public String GetName() {
        return this.name;
    }

    public String GetColor() {
        return this.color;
    }

    public int GetPositionX() {
        return this.positionX;
    }

    public int GetPositionY() {
        return this.positionY;
    }

    public String GetHeading() {
        return this.heading;
    }

    public List<String> GetCommands() {
        return this.commands;
    }

    public List<Integer> GetCheckPoints() {
        return this.checkPoints;
    }   
}
