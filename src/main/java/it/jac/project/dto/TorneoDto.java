package it.jac.project.dto;

import lombok.Data;

@Data
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

}
