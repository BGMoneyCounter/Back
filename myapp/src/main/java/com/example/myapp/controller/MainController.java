package com.example.myapp.controller;
import com.example.myapp.entity.Games; 
import com.example.myapp.entity.Players;
import com.example.myapp.repository.GamesRepository;
import com.example.myapp.repository.PlayersRepository;
import com.example.myapp.dto.GamesListDTO;
import com.example.myapp.dto.CreateGameRequest;
import com.example.myapp.dto.JoinGameRequest;
import com.example.myapp.dto.PlayerListDTO;


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
	
	@Autowired
	private PlayersRepository playersRepository;
	
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
    public ResponseEntity<Map<String, Object>> createGame(@RequestBody CreateGameRequest request) {
        // ゲーム名の重複チェック
        if (gamesRepository.findByGameName(request.getGameName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "ゲーム名は既に存在します"));
        }

        // 新規ゲーム作成
        Games newGame = new Games();
        newGame.setGameName(request.getGameName());
        newGame.setPass(request.getPass());
        newGame.setEndFlag(false);
        Games savedGame = gamesRepository.save(newGame);

        Players gmPlayer = new Players();
        gmPlayer.setGame(savedGame);
        gmPlayer.setPlayerName("GM");
        gmPlayer.setMoney(0);
        gmPlayer.setGmFlag(true);
        playersRepository.save(gmPlayer);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("gameId", savedGame.getGameId(), "message", "ゲームを作成しました"));
    }
    
    //ゲーム名とpassが一致するゲームを取得
    @PostMapping("/joinGame")
    public ResponseEntity<Map<String, Object>> joinGame(@RequestBody JoinGameRequest request) {
        Optional<Games> gameOpt = gamesRepository.findByGameNameAndPassAndEndFlagFalse(
            request.getGameName(), request.getPass()
        );

        if (gameOpt.isPresent()) {
            Games game = gameOpt.get();
            return ResponseEntity.ok(Map.of("gameId", game.getGameId(), "gameName", game.getGameName(), "message", "ゲームに参加しました"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "ゲーム名またはパスワードが間違っています"));
        }
    }

    
    //ゲームに登録されているPlayer一覧を表示
    @GetMapping("/game/view/{gameId}/playerList")
    public ResponseEntity<List<PlayerListDTO>> viewPlayerList(@PathVariable int gameId) {
        Optional<Games> gameOpt = gamesRepository.findById(gameId);
        if (!gameOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<Players> players = playersRepository.findByGame_GameId(gameId);
        List<PlayerListDTO> result = players.stream()
            .map(p -> new PlayerListDTO(p.getPlayerId(), p.getPlayerName(), p.getGame().getGameId()))
            .toList();
        return ResponseEntity.ok(result);
    }

    //ゲームに登録されているPlayerを選択し、選択したPlayerとしてゲームに参加する
    @GetMapping("/game/view/{gameId}/{playerId}")
    public ResponseEntity<PlayerListDTO> selectPlayer(@PathVariable int gameId, @PathVariable int playerId) {
        Optional<Players> playerOpt = playersRepository.findById(playerId);
        if (!playerOpt.isPresent() || playerOpt.get().getGame().getGameId() != gameId) {
            return ResponseEntity.notFound().build();
        }
        Players player = playerOpt.get();
        return ResponseEntity.ok(new PlayerListDTO(player.getPlayerId(), player.getPlayerName(), gameId));
    }
}
