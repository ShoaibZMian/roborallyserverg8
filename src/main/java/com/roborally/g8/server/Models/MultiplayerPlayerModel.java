package com.roborally.g8.server.Models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * ...
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class MultiplayerPlayerModel {

    private int id;
    private String name;
    private String ipAddress;

    public MultiplayerPlayerModel() {
        // Needed to convert from json file to object
    }

    public MultiplayerPlayerModel(int id, String name, String ipAddress) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
    }

    public int GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public String GetIpAddress() {
        return ipAddress;
    }
}
