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
	@GneratedValue(strategy = GenerationType.IDENTITY)
	private int game_id;
	
	@Column(nullable = false)
	private string game_name;
	
	@Column(nullable= false)
	private string pass;
	
	@Column(nullable= false)
	private bool end_flag;
}
