// src/main/java/com/example/myapp/dto/JoinGameRequest.java
package com.example.myapp.dto;

public class JoinGameRequest {
    private String gameName;
    private String pass;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
