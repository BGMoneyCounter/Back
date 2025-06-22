
package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Map;

@RestController // JSON を返すコントローラ
public class GameMasterController {

    @PostMapping("/createGame")
    public Map<String, String> creatGame() {
        return Map.of("message", "GM表示");
    }
    
    

}
