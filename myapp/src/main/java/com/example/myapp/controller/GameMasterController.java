
package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Map;

@RestController // JSON を返すコントローラ
public class GameMasterController {

	//Player一覧、お金、ゲーム名を取得
	@GetMapping("/gm/view/{gameId}/{playerId}")
	public Map<String, String> viewPlayer(
	    @PathVariable String gameId,
	    @PathVariable String playerId) {
			return Map.of(
				"message", "Player表示",
				"gameId", gameId,
				"playerId", playerId
				);
	    }
	
	//入力されたPlayerをゲームに追加する
	@PostMapping("/joinPlayer")
	public Map<String, String> addPlayer() {
			return Map.of(
				"message", "Player参加"
				);
	    }
	
	//入力されたお金に変更する
	@PuttMapping("/changeMoney")
	public Map<String, String> vhangeMoney() {
			return Map.of(
				"message", "お金変更"
				);
	    }
	

}
