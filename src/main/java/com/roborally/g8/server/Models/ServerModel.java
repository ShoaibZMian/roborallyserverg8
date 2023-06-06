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
public class ServerModel  {
    
    
    private String name;
    private Map<String,Object> gameController;
    private Map<String,Object> space;

    public ServerModel() {
        // Needed to convert from json file to object
    }


    public ServerModel (String name, Map<String,Object> gameController, Map<String,Object> space){
        this.name = name;
        this.gameController = gameController;
        this.space = space;
    }

    public String GetName(){
        return this.name;
    }
    public Map<String,Object> GetGameController(){
        return this.gameController;
    }
    public Map<String,Object> GetSpace(){
        return this.space;
    }

}
