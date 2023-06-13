package com.roborally.g8.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.roborally.g8.server.Models.MultiplayerModel;
import com.roborally.g8.server.Models.MultiplayerPlayerModel;
import com.roborally.g8.server.Models.ServerModel;

import java.util.List;

/**
 * Class handling the multiplayer functionality of the RoboRally game.
 * 
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@RestController
@RequestMapping("/multiplayer")
public class MultiplayerController {
    public static MultiplayerModel multiplayerModel;
    public static List<MultiplayerPlayerModel> players = new java.util.ArrayList<>();
    public static int totalPlayers;


     /**
     * Saves the current state of the game.
     *
     * @param data The current state of the server.
     * @return A ResponseEntity indicating the outcome of the operation.
     */

    @PostMapping("/savestate")
    public ResponseEntity<MultiplayerPlayerModel> PostSaveState(@RequestBody ServerModel data) {
        try {
            multiplayerModel.SetGamestate(data);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

     /**
     * Retrieves the current state of the game.
     *
     * @return A ResponseEntity containing the current game state.
     */

    @GetMapping("/getstate")
    public ResponseEntity<ServerModel> GetSaveState() {
        try {
            return ResponseEntity.ok(multiplayerModel.GetGamestate());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

     /**
     * Sets the total number of players in the game.
     *
     * @param totalPlayers The total number of players.
     * @return A ResponseEntity indicating the outcome of the operation.
     */

    @PostMapping("/setTotalPlayers")
    public ResponseEntity<MultiplayerPlayerModel> PostSetTotalPlayers(@RequestBody int totalPlayers) {
        try {
            MultiplayerController.totalPlayers = totalPlayers;
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

      /**
     * Retrieves the total number of players in the game.
     *
     * @return A ResponseEntity containing the total number of players.
     */

    @GetMapping("/totalPlayers")
    public ResponseEntity<Integer> GetTotalPlayers() {
        try {
            return ResponseEntity.ok(totalPlayers);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

     /**
     * Adds a player to the game.
     *
     * @param data The player to be added.
     * @return A ResponseEntity indicating the outcome of the operation.
     */

    @PostMapping("/join")
    public ResponseEntity<MultiplayerPlayerModel> Post(@RequestBody MultiplayerPlayerModel data) {
        try {
            for (MultiplayerPlayerModel player : players) {
                if (player.GetId() == data.GetId()) {
                    // return ResponseEntity.status(HttpStatus.CONFLICT).build();
                    return ResponseEntity.ok(null);
                }
            }
            players.add(data);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

     /**
     * Retrieves the list of players in the game.
     *
     * @return A ResponseEntity containing the list of players.
     */

    @GetMapping("/players")
    public ResponseEntity<List<MultiplayerPlayerModel>> Get() {
        try {
            // Sleep for 1 sec
            Thread.sleep(1000);
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

     /**
     * Starts the game if the number of players matches the total.
     *
     * @return A ResponseEntity containing the game model if the game started, or an error status.
     */

    @GetMapping("/start")
    public ResponseEntity<MultiplayerModel> Start() {
        try {
            if (players.size() == totalPlayers) {
                int playerTurn = 0;
                multiplayerModel = new MultiplayerModel(playerTurn, players);
                multiplayerModel.SetTotalPlayers(totalPlayers);
                return ResponseEntity.ok(multiplayerModel);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

 /**
     * Retrieves the current player's turn.
     *
     * @return A ResponseEntity containing the current player's turn.
     */

    @GetMapping("/playerTurn")
    public ResponseEntity<Integer> GetPlayerTurn() {
        try {
            return ResponseEntity.ok(multiplayerModel.GetPlayerTurn());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

    /**
     * Proceeds to the next player's turn.
     *
     * @return A ResponseEntity containing the new player's turn.
     */

    @GetMapping("/nextPlayerTurn")
    public ResponseEntity<Integer> NextPlayerTurn() {
        try {
            int nextPlayerTurn = multiplayerModel.GetPlayerTurn() + 1;
            if (nextPlayerTurn >= players.size()) {
                nextPlayerTurn = 0;
            }
            multiplayerModel.SetPlayerTurn(nextPlayerTurn);
            return ResponseEntity.ok(multiplayerModel.GetPlayerTurn());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

    /**
     * Retrieves the current multiplayer model.
     *
     *     * @return A ResponseEntity containing the current multiplayer model.
     */


    @GetMapping("/getMultiplayerModel")
    public ResponseEntity<MultiplayerModel> GetMultiplayerModel() {
        try {
            return ResponseEntity.ok(multiplayerModel);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

   /**
     * Updates the current multiplayer model.
     *
     * @param data The new multiplayer model.
     * @return A ResponseEntity indicating the outcome of the operation.
     */

    @PostMapping("/postMultiplayerModel")
    public ResponseEntity<MultiplayerModel> PostUpdateMultiplayerModel(@RequestBody MultiplayerModel data) {
        try {
            multiplayerModel = data;
            players = multiplayerModel.GetPlayers();
            totalPlayers = multiplayerModel.GetTotalPlayers();
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }
}
