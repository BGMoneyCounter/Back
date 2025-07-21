package com.example.myapp.dto;

public class PlayerListDTO {
    private int playerId;
    private String playerName;
    private int gameId;

    public PlayerListDTO(int playerId, String playerName, int gameId) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.gameId = gameId;
    }

    public int getPlayerId() { 
        return playerId; 
    }
    
    public String getPlayerName() { 
        return playerName; 
    }
    
    public int getGameId() { 
        return gameId; 
    }
}
