package com.roborally.g8.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roborally.g8.server.Models.ServerModel;

@RestController
@RequestMapping("/checkpoints")
public class CheckpointsController {
    @PostMapping
    public ResponseEntity<ServerModel> Post(@RequestBody ServerModel data) {
        try {
            //return ResponseEntity.ok("");
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return (ResponseEntity<ServerModel>) ResponseEntity.internalServerError();
        }
    }

}