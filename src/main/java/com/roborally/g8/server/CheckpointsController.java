package com.roborally.g8.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roborally.g8.server.Models.ServerModel;

import java.io.FileWriter;
import java.io.IOException;

/**
 * ...
 *
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@RestController
@RequestMapping("/checkpoints")
public class CheckpointsController {
    @PostMapping
    public ResponseEntity<ServerModel> Post(@RequestBody ServerModel data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(data);

            String projectDir = System.getProperty("user.dir");
            String fileName = data.GetOrderNo() + ".json";
            String filePath = projectDir + "\\src\\main\\java\\com\\roborally\\g8\\server\\JSON\\" + fileName;

            try (FileWriter file = new FileWriter(filePath)) {
                file.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // return ResponseEntity.ok("");
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return (ResponseEntity<ServerModel>) ResponseEntity.internalServerError();
        }
    }
}