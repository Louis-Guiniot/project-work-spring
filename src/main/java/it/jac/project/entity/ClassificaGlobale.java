package it.jac.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity 
@Table (name = "classifica_globale")
public class ClassificaGlobale {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "idPlayer")
	private int idPlayer;
	
	@Column(name = "torneo_giocati")
	private int torneiGiocati;
	
	@Column(name = "partite_giocate")
	private int partiteGiocate;
	
	@Column(name = "punteggio_totale")
	private int punteggioTotale;
	
	@Column(name = "punteggio_totale")
	private double mediaPunteggio;
	
	@Column(name = "vittorie")
	private int vittorie;
	
	@Column(name = "media_podio")
	private double mediaPodio;
	

	
}
