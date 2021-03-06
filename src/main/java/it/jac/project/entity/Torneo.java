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
@Table(name = "torneo") 	
public class Torneo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome_torneo")
	private String nome;
	
	@Column(name = "gioco")
	private String gioco;
	
	@Column(name = "piattaforma")
	private String piattaforma;

	@Column(name = "capienza")
	private int capienza;
	
	@Column(name = "capienza_minima")
	private int capienzaMinima;
	
	@Column(name = "iscrizioni")
	private int iscrizioni;
	
	@Column(name = "posti_liberi")
	private int postiLiberi;
	
	@Column(name = "partite_da_giocare")
	private int partite;
	
	@Column(name = "quota_iscrizione")
	private int quota;
	
	@Column(name = "premio_primo")
	private String premioPrimo;
	
	@Column(name = "premio_secondo")
	private String premioSecondo;
	
	@Column(name = "premio_terzo")
	private String premioTerzo;
	
	@Column(name = "id_creatore")
	private int idCreatore;
	
	@Column(name = "stato")
	private String stato;

	@Column(name = "descrizione")
	private String descrizione;
	
	@Column(name = "data_creazione")
	private String dataCreazione;
	
	@Column(name = "data_modifica")
	private String dataModifica;

}
