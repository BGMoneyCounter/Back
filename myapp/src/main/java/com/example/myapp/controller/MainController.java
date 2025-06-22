package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // JSON を返すコントローラ
public class MainController {

    @GetMapping("/gameList/view")
    public Map<String, String> view() {
        return Map.of("message", "Hello, Spring Boot!");
    }

    @PostMapping("/createGame")
    public Map<String, String> bye() {
        return Map.of("message", "bye!");
    }

}
