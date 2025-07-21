
package com.example.myapp.controller;

import com.example.myapp.dto.ChangeMoneyRequest;
import com.example.myapp.dto.JoinPlayerRequest;
import com.example.myapp.dto.GMViewResponse;
import com.example.myapp.dto.ChangePlayerNameRequest;
import com.example.myapp.dto.DeletePlayerRequest;
import com.example.myapp.dto.ChangeGameNameRequest;
import com.example.myapp.dto.FinishGameRequest;
import com.example.myapp.entity.Players;
import com.example.myapp.entity.Games;
import com.example.myapp.repository.PlayersRepository;
import com.example.myapp.repository.GamesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gm")
public class GameMasterController {

    @Autowired
    private PlayersRepository playersRepository;
    
    @Autowired
    private GamesRepository gamesRepository;

	//Player一覧、お金、ゲーム名を取得
	@GetMapping("/view/{gameId}")
	public ResponseEntity<GMViewResponse> viewGM(@PathVariable int gameId) {
		Optional<Games> gameOpt = gamesRepository.findById(gameId);
		if (!gameOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Games game = gameOpt.get();
		List<Players> players = playersRepository.findByGame_GameId(gameId);
		List<GMViewResponse.PlayerInfo> playerInfos = players.stream()
			.map(p -> new GMViewResponse.PlayerInfo(p.getPlayerId(), p.getPlayerName(), p.getMoney(), p.isGmFlag()))
			.toList();

		return ResponseEntity.ok(new GMViewResponse(game.getGameName(), playerInfos));
	}
	
	//入力されたPlayerをゲームに追加する
	@PostMapping("/joinPlayer")
	public ResponseEntity<String> addPlayer(@RequestBody JoinPlayerRequest request) {
		Optional<Games> gameOpt = gamesRepository.findById(request.getGameId());
		if (!gameOpt.isPresent()) {
			return ResponseEntity.badRequest().body("ゲームが見つかりません");
		}

		Players newPlayer = new Players();
		newPlayer.setGame(gameOpt.get());
		newPlayer.setPlayerName("新しいプレイヤー");
		newPlayer.setMoney(0);
		newPlayer.setGmFlag(false);
		playersRepository.save(newPlayer);

		return ResponseEntity.ok("プレイヤーを追加しました");
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

    @PutMapping("/changePlayerName")
    public ResponseEntity<String> changePlayerName(@RequestBody ChangePlayerNameRequest request) {
        Optional<Players> playerOpt = playersRepository.findById(request.getPlayerId());
        if (!playerOpt.isPresent()) {
            return ResponseEntity.badRequest().body("プレイヤーが見つかりません");
        }

        Players player = playerOpt.get();
        player.setPlayerName(request.getPlayerName());
        playersRepository.save(player);
        return ResponseEntity.ok("プレイヤー名を変更しました");
    }

    @DeleteMapping("/deletePlayer")
    public ResponseEntity<String> deletePlayer(@RequestBody DeletePlayerRequest request) {
        if (!playersRepository.existsById(request.getPlayerId())) {
            return ResponseEntity.badRequest().body("プレイヤーが見つかりません");
        }
        playersRepository.deleteById(request.getPlayerId());
        return ResponseEntity.ok("プレイヤーを削除しました");
    }

    @PutMapping("/changeGameName")
    public ResponseEntity<String> changeGameName(@RequestBody ChangeGameNameRequest request) {
        Optional<Games> gameOpt = gamesRepository.findById(request.getGameId());
        if (!gameOpt.isPresent()) {
            return ResponseEntity.badRequest().body("ゲームが見つかりません");
        }

        Games game = gameOpt.get();
        game.setGameName(request.getGameName());
        gamesRepository.save(game);
        return ResponseEntity.ok("ゲーム名を変更しました");
    }

    @PutMapping("/finishedGame")
    public ResponseEntity<String> finishGame(@RequestBody FinishGameRequest request) {
        Optional<Games> gameOpt = gamesRepository.findById(request.getGameId());
        if (!gameOpt.isPresent()) {
            return ResponseEntity.badRequest().body("ゲームが見つかりません");
        }

        Games game = gameOpt.get();
        game.setEndFlag(true);
        gamesRepository.save(game);
        return ResponseEntity.ok("ゲームを終了しました");
    }
}
