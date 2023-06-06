package com.roborally.g8.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roborally.g8.server.Models.ServerModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.List;


/*
 * @author Shaoib Zafar Mian, s200784@dtu.dk
 */

@RestController
@RequestMapping("/savegame")
public class SaveGameController {
    @PostMapping
    public ResponseEntity<ServerModel> Post(@RequestBody ServerModel data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(data);

            String projectDir = System.getProperty("user.dir");
            String fileName = data.GetName() + ".json";
            String filePath = projectDir + "\\src\\main\\java\\com\\roborally\\g8\\server\\JSON\\" + fileName;

            File file = new File(filePath);
            if (file.exists()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return (ResponseEntity<ServerModel>) ResponseEntity.internalServerError();
        }
    }
    
    @GetMapping("/{name}")
    public ResponseEntity<ServerModel> Get(@PathVariable String name) {
        try {
            String projectDir = System.getProperty("user.dir");
            String fileName = name + ".json";
            String filePath = projectDir + "\\src\\main\\java\\com\\roborally\\g8\\server\\JSON\\" + fileName;

            ObjectMapper objectMapper = new ObjectMapper();
            ServerModel data = objectMapper.readValue(new File(filePath), ServerModel.class);

            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return (ResponseEntity<ServerModel>) ResponseEntity.internalServerError();
        }
    }

    @GetMapping
    public ResponseEntity<List<String>> GetAll() {
        try {
            String projectDir = System.getProperty("user.dir");
            String directoryPath = projectDir + "\\src\\main\\java\\com\\roborally\\g8\\server\\JSON\\";

            ObjectMapper objectMapper = new ObjectMapper();
            List<String> nameList = Files.walk(Paths.get(directoryPath))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .map(file -> {
                    try {
                        ServerModel data = objectMapper.readValue(file, ServerModel.class);
                        return data.GetName();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());

            return ResponseEntity.ok(nameList);
        } catch (Exception e) {
            return (ResponseEntity<List<String>>) ResponseEntity.internalServerError();
        }
    }
}
