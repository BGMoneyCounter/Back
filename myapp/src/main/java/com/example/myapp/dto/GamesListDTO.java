package com.example.myapp.dto;
public class GamesListDTO {
    private int gameId;
    private String gameName;

    public GamesListDTO(int gameId, String gameName) {
        this.gameId = gameId;
        this.gameName = gameName;
    }

    public int getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }
}
