package it.jac.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGioco() {
		return gioco;
	}

	public void setGioco(String gioco) {
		this.gioco = gioco;
	}

	public String getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public int getCapienzaMinima() {
		return capienzaMinima;
	}

	public void setCapienzaMinima(int capienzaMinima) {
		this.capienzaMinima = capienzaMinima;
	}

	public int getIscrizioni() {
		return iscrizioni;
	}

	public void setIscrizioni(int iscrizioni) {
		this.iscrizioni = iscrizioni;
	}

	public int getPostiLiberi() {
		return postiLiberi;
	}

	public void setPostiLiberi(int postiLiberi) {
		this.postiLiberi = postiLiberi;
	}

	public int getPartite() {
		return partite;
	}

	public void setPartite(int partite) {
		this.partite = partite;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public String getPremioPrimo() {
		return premioPrimo;
	}

	public void setPremioPrimo(String premioPrimo) {
		this.premioPrimo = premioPrimo;
	}

	public String getPremioSecondo() {
		return premioSecondo;
	}

	public void setPremioSecondo(String premioSecondo) {
		this.premioSecondo = premioSecondo;
	}

	public String getPremioTerzo() {
		return premioTerzo;
	}

	public void setPremioTerzo(String premioTerzo) {
		this.premioTerzo = premioTerzo;
	}

	public int getIdCreatore() {
		return idCreatore;
	}

	public void setIdCreatore(int idCreatore) {
		this.idCreatore = idCreatore;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

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

	@Override
	public String toString() {
		return "Torneo [id=" + id + ", nome=" + nome + ", gioco=" + gioco + ", piattaforma=" + piattaforma
				+ ", capienza=" + capienza + ", capienzaMinima=" + capienzaMinima + ", iscrizioni=" + iscrizioni
				+ ", postiLiberi=" + postiLiberi + ", partite=" + partite + ", quota=" + quota + ", premioPrimo="
				+ premioPrimo + ", premioSecondo=" + premioSecondo + ", premioTerzo=" + premioTerzo + ", idCreatore="
				+ idCreatore + ", stato=" + stato + "]";
	}
	
}
