//package com.example.myapp.entity;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;
//@Entity
//@Table(name="players")
//@Getter
//@Setter
//public class Players {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int player_id;
//	
//	@Column(nullable = false)
//	private boolean gm_flag;
//	
//	@Column()
//	private int money
//	
//	@ManyToOne
//    @JoinColumn(name = "games", referencedColumnName = "game_id", nullable = false)
//	private Games game_id;
//}
