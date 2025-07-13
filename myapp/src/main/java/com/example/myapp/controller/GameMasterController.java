
package com.example.myapp.controller;
import com.example.myapp.dto.ChangeMoneyRequest; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PutMapping("/changeMoney")
    public ResponseEntity<String> changeMoney(@RequestBody ChangeMoneyRequest req) {
        Optional<Players> opt = playersRepository.findById(req.getPlayerId());
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body("プレイヤーが見つかりません");
        }

        Players p = opt.get();
        int updated = p.getMoney() + req.getMoney();
        if (updated < 0) updated = 0;  // 負にならないよう補正

        p.setMoney(updated);
        playersRepository.save(p);
        return ResponseEntity.ok("お金を更新しました");
    }
	

}
