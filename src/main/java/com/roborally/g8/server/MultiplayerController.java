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

import java.util.List;

/*
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@RestController
@RequestMapping("/multiplayer")
public class MultiplayerController {

    private static List<MultiplayerPlayerModel> players = new java.util.ArrayList<>();
    private static MultiplayerModel multiplayerModel;

    @PostMapping("/join")
    public ResponseEntity<MultiplayerPlayerModel> Post(@RequestBody MultiplayerPlayerModel data) {
        try {
            for (MultiplayerPlayerModel player : players) {
                if (player.GetId() == data.GetId()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }
            players.add(data);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

    @GetMapping("/players")
    public ResponseEntity<List<MultiplayerPlayerModel>> Get() {
        try {
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

    @GetMapping("/start")
    public ResponseEntity<MultiplayerModel> Start() {
        try {
            int playerTurn = (int) (Math.random() * (players.size() - 1));
            multiplayerModel = new MultiplayerModel(playerTurn, players);
            return ResponseEntity.ok(multiplayerModel);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

    @GetMapping("/playerTurn")
    public ResponseEntity<Integer> GetPlayerTurn() {
        try {
            return ResponseEntity.ok(multiplayerModel.GetPlayerTurn());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

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
}
