package com.example.myapp.dto;

import java.util.List;

public class GMViewResponse {
    private String gameName;
    private List<PlayerInfo> players;

    public static class PlayerInfo {
        private int playerId;
        private String playerName;
        private int money;
        private boolean gmFlag;

        public PlayerInfo(int playerId, String playerName, int money, boolean gmFlag) {
            this.playerId = playerId;
            this.playerName = playerName;
            this.money = money;
            this.gmFlag = gmFlag;
        }

        public int getPlayerId() { 
            return playerId; 
        }
        
        public String getPlayerName() { 
            return playerName; 
        }
        
        public int getMoney() { 
            return money; 
        }
        
        public boolean isGmFlag() { 
            return gmFlag; 
        }
    }

    public GMViewResponse(String gameName, List<PlayerInfo> players) {
        this.gameName = gameName;
        this.players = players;
    }

    public String getGameName() { 
        return gameName; 
    }
    
    public List<PlayerInfo> getPlayers() { 
        return players; 
    }
}
