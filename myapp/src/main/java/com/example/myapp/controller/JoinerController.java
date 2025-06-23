
package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Map;

@RestController // JSON を返すコントローラ
public class JoinerController {
    
//Player一覧、お金、ゲーム名を取得
@GetMapping("/joiner/view/{gameId}/{playerId}")
public Map<String, String> joinerView(
    	@PathVariable String gameId,
        @PathVariable String playerId) {
        	return Map.of(
        		"message", "Joiner表示",
        		"gameId", gameId,
        		"playerId", playerId
        		);
    	}	
}
