package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // JSON を返すコントローラ
public class MainController {

    @GetMapping("/gameList/view")
    public Map<String, String> gameListView() {
        return Map.of("message", "Main表示");
    }

    @PostMapping("/createGame")
    public Map<String, String> bye() {
        return Map.of("message", "GM表示");
    }
    
    @GetMapping("/joiner/view/{gameId}/{playerId}")
    public Map<String, String> joinerView() {
        return Map.of("message", "Joiner表示");
    }

}
