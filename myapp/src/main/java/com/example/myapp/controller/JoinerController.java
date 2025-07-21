package com.example.myapp.controller;

import com.example.myapp.entity.Games;
import com.example.myapp.entity.Players;
import com.example.myapp.repository.GamesRepository;
import com.example.myapp.repository.PlayersRepository;
import com.example.myapp.dto.JoinerViewResponse;
import com.example.myapp.dto.JoinPlayerRequest;  
import com.example.myapp.dto.ChangeMoneyRequest;
import com.example.myapp.dto.ChangePlayerNameRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/joiner") // 基本パスはここで定義
public class JoinerController {

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private PlayersRepository playersRepository;

    @GetMapping("/view/{gameId}")
    public ResponseEntity<JoinerViewResponse> getJoinerView(@PathVariable int gameId) {
        Optional<Games> gameOpt = gamesRepository.findById(gameId);
        if (!gameOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Games game = gameOpt.get();
        List<Players> players = playersRepository.findByGame_GameId(gameId);

        JoinerViewResponse response = new JoinerViewResponse();
        response.setGameName(game.getGameName());
        response.setPlayers(players);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/joinPlayer")
    public ResponseEntity<?> joinPlayer(@RequestBody JoinPlayerRequest request) {
        Optional<Games> gameOpt = gamesRepository.findById(request.getGameId());
        if (!gameOpt.isPresent()) {
            return ResponseEntity.badRequest().body("ゲームが見つかりません");
        }

        Games game = gameOpt.get();

        Players newPlayer = new Players();
        newPlayer.setGame(game);
        newPlayer.setPlayerName("新しいプレイヤー");
        newPlayer.setGmFlag(false);
        newPlayer.setMoney(0);

        Players saved = playersRepository.save(newPlayer);

        return ResponseEntity.ok(saved.getPlayerId());
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

    @PutMapping("/changeMoney")
    public ResponseEntity<String> changeMoney(@RequestBody ChangeMoneyRequest req) {
        Optional<Players> opt = playersRepository.findById(req.getPlayerId());
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body("プレイヤーが見つかりません");
        }

        Players p = opt.get();
        int updated = p.getMoney() + req.getMoney();
        if (updated < 0) updated = 0;

        p.setMoney(updated);
        playersRepository.save(p);
        return ResponseEntity.ok("お金を更新しました");
    }
}
