package com.roborally.g8.server.Models;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * ...
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */


@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ServerModel  {
    
    private int orderNo;
    private Map<String,Object> gameController;
    private Map<String,Object> space;


    public ServerModel (int orderNo, Map<String,Object> gameController, Map<String,Object> space){
        this.orderNo = orderNo;
        this.gameController = gameController;
        this.space = space;
    }

    public int GetOrderNo(){
        return this.orderNo;
    }
    public Map<String,Object> GetGameController(){
        return this.gameController;
    }
    public Map<String,Object> GetSpace(){
        return this.space;
    }

}
