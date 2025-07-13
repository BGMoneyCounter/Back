package com.example.myapp.controller;
import com.example.myapp.entity.Games; 
import com.example.myapp.repository.GamesRepository;
import com.example.myapp.dto.GamesListDTO;
import com.example.myapp.dto.CreateGameRequest;
import com.example.myapp.dto.JoinGameRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;
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
    public ResponseEntity<String> createGame(@RequestBody CreateGameRequest request) {
        // ゲーム名の重複チェック
        if (gamesRepository.findByGameName(request.getGameName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ゲーム名は既に存在します");
        }

        // 新規ゲーム作成
        Games newGame = new Games();
        newGame.setGameName(request.getGameName());
        newGame.setPass(request.getPass());
        newGame.setEndFlag(false);
        gamesRepository.save(newGame);

        return ResponseEntity.status(HttpStatus.CREATED).body("ゲームを作成しました");
    }
    
    //ゲーム名とpassが一致するゲームを取得
    @PostMapping("/joinGame")
    public ResponseEntity<GamesListDTO> joinGame(@RequestBody JoinGameRequest request) {
        Optional<Games> gameOpt = gamesRepository.findByGameNameAndPassAndEndFlagFalse(
            request.getGameName(), request.getPass()
        );

        if (gameOpt.isPresent()) {
            Games game = gameOpt.get();
            GamesListDTO dto = new GamesListDTO(game.getGameId(), game.getGameName());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
