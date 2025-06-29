package com.example.myapp.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name="games")
@Getter
@Setter
public class Games {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;

    @Column(name = "game_name", nullable = false)
    private String gameName;

    @Column(name="game_pass",nullable = false)
    private String pass;

    @Column(name = "end_flag", nullable = false)
    private boolean endFlag;
    
    public int getGameId() { return gameId; }
    public String getGameName() { return gameName; }
    public String getPass() { return pass; }
    public boolean isEndFlag() { return endFlag; }

    public void setGameId(int gameId) { this.gameId = gameId; }
    public void setGameName(String gameName) { this.gameName = gameName; }
    public void setPass(String pass) { this.pass = pass; }
    public void setEndFlag(boolean endFlag) { this.endFlag = endFlag; }

}