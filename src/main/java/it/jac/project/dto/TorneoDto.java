package it.jac.project.dto;

import org.springframework.beans.BeanUtils;

import it.jac.project.entity.Torneo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class TorneoDto {

	private int id;
	private String nome;
	private String gioco;
	private String piattaforma;
	private int capienza;
	private int capienzaMinima;
	private int iscrizioni;
	private int postiLiberi;
	private int partite;
	private int quota;
	private String premioPrimo;
	private String premioSecondo;
	private String premioTerzo;
	private int idCreatore;
	private String stato;
	
	public static TorneoDto build(Torneo torneo) {

		TorneoDto result = new TorneoDto();
		BeanUtils.copyProperties(torneo, result);

		return result;
	}

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

}
