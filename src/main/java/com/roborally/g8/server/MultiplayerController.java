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

/*
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@RestController
@RequestMapping("/multiplayer")
public class MultiplayerController {
    public static MultiplayerModel multiplayerModel;
    public static List<MultiplayerPlayerModel> players = new java.util.ArrayList<>();
    public static int totalPlayers;

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

    @GetMapping("/getstate")
    public ResponseEntity<ServerModel> GetSaveState() {
        try {
            return ResponseEntity.ok(multiplayerModel.GetGamestate());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

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

    @GetMapping("/totalPlayers")
    public ResponseEntity<Integer> GetTotalPlayers() {
        try {
            return ResponseEntity.ok(totalPlayers);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

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

    @GetMapping("/getMultiplayerModel")
    public ResponseEntity<MultiplayerModel> GetMultiplayerModel() {
        try {
            return ResponseEntity.ok(multiplayerModel);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error msg.....", e);
        }
    }

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
