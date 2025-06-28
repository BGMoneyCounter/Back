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
@Table(name="players")
@Getter
@Setter
public class Players {
	@Id
	@GneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id")
    private int playerId;

    @Column(name = "gm_flag", nullable = false)
    private boolean gmFlag;

    @Column(nullable = false)
    private int money;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "game_id", nullable = false)
    private Games game;
}