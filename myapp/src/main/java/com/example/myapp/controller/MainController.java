package com.example.myapp.controller;
import com.example.myapp.entity.Games; 
import com.example.myapp.repository.GamesRepository;
import com.example.myapp.dto.GamesListDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;


@RestController // JSON を返すコントローラ
public class MainController {
	@Autowired
	private GamesRepository gamesRepository;
	
	//ゲーム名一覧取得
    @GetMapping(value = "/gameList/view", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<GamesListDTO>> getGameList() {
    	List<Games> gamesEntities=gamesRepository.findByEndFlagFalse();
        List<GamesListDTO> result = gamesEntities.stream()
        .map(game -> new GamesListDTO(game.getGameId(), game.getGameName()))
        .toList();
        return ResponseEntity.ok(result);
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
