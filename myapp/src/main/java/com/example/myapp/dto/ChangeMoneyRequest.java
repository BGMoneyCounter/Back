package com.example.myapp.dto;

public class ChangeMoneyRequest {
    private int playerId;
    private int money;  // 「増減分」を表す。プラスマイナス両対応。

    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}
