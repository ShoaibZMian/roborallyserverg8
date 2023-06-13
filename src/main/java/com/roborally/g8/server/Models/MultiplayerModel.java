package com.roborally.g8.server.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * This class represents the state of a multiplayer game. It encapsulates game-specific
 * details such as the player's turn, the list of players, 
 * the game state, and the total number of players.
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class MultiplayerModel {

    private int playerTurn; // if 0 then player 0 turn, if 1 then player 1 turn, based on player id
    private List<MultiplayerPlayerModel> players;
    private ServerModel gamestate;
    private int totalPlayers;

  /**
     * Default constructor. Required for conversion from JSON file to object.
     */

    public MultiplayerModel() {
       
    }

 /**
     * A constructor to initialize the playerTurn and players fields of the MultiplayerModel.
     *
     * @param playerTurn Specifies whose turn it is to play, based on player id
     * @param players The list of players in the game
     */

    public MultiplayerModel(int playerTurn, List<MultiplayerPlayerModel> players) {
        this.playerTurn = playerTurn;
        this.players = players;
    }

    public int GetPlayerTurn() {
        return playerTurn;
    }

    public List<MultiplayerPlayerModel> GetPlayers() {
        return players;
    }

    public void SetPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public ServerModel GetGamestate() {
        return gamestate;
    }

    public void SetGamestate(ServerModel gamestate) {
        this.gamestate = gamestate;
    }

    public int GetTotalPlayers() {
        return totalPlayers;
    }

    public void SetTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }
}
