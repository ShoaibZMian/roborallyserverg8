package com.roborally.g8.server.Models;

import java.util.List;
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
public class ServerPlayerModel {

    private String name; // Use as ID
    private String color;

    private int positionX; // Set as Space
    private int positionY; // Set as Space

    private String heading;

    private List<String> commands;

    public ServerPlayerModel() {
        // Needed to convert from json file to object
    }

    public ServerPlayerModel(String name, String color, int positionX, int positionY, String heading,
    List<String> commands) {
        this.name = name;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.heading = heading;
        this.commands = commands;
    }

    public String GetName() {
        return name;
    }

    public String GetColor() {
        return color;
    }

    public int GetPositionX() {
        return positionX;
    }

    public int GetPositionY() {
        return positionY;
    }

    public String GetHeading() {
        return heading;
    }

    public List<String> GetCommands() {
        return commands;
    }
    
}
