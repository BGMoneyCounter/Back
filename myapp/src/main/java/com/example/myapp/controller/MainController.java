package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Map;

@RestController // JSON を返すコントローラ
public class MainController {
	
	//ゲーム名一覧取得
    @GetMapping("/gameList/view")
    public Map<String, String> viewGameList() {
        	return Map.of(
        			"gameId","gameId",
        			"playerId","playerId"
        			);
    }
    
    //ゲームを作成する
    @PostMapping("/createGame")
    public Map<String, String> createGame() {
        return Map.of("message", "GM表示");
    }
    
    //ゲーム名とpassが一致するゲームを取得
    @PostMapping("/joinGame")
    public Map<String,String> joinGame(){
    	return Map.of("message","joinGame");
    }
    
    //ゲームに登録されているPlayer一覧を表示
    @GetMapping("/game/view/{gameId}/playerList")
    public Map<String, String> viewPlayerList(
        	@PathVariable String gameId) {
            	return Map.of(
            		"playerId", "playerId",
            		"playerName", "playerName",
            		"gameId",gameId
            		);
        	}	

    //ゲームに登録されているPlayerを選択し、選択したPlayerとしてゲームに参加する
    @GetMapping("/game/view/{gameId}/{playerId}")
    public Map<String, String> selectPlayer(
        	@PathVariable String gameId,
        	@PathVariable String playerId) {
            	return Map.of(
            		"gameId", gameId,
            		"playerId",playerId,
            		"playerName", "playerName"            		
            		);
        	}	
}
