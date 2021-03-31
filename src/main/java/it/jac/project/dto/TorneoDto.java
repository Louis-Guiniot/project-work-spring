package it.jac.project.dto;

import org.springframework.beans.BeanUtils;

import it.jac.project.entity.Torneo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	private int idCreatore;
	private String stato;
	
	public static TorneoDto build(Torneo torneo) {

		TorneoDto result = new TorneoDto();
		BeanUtils.copyProperties(torneo, result);

		return result;
	}

}
