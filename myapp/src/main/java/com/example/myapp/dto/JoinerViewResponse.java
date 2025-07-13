package com.example.myapp.dto;

import java.util.List;
import com.example.myapp.entity.Players;

public class JoinerViewResponse {

    private String gameName;
    private List<Players> players;

    // ゲッター・セッター
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }
}
