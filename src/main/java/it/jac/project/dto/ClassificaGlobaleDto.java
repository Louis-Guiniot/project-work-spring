package it.jac.project.dto;

import org.springframework.beans.BeanUtils;
import it.jac.project.entity.ClassificaGlobale;

public class ClassificaGlobaleDto {
	
	int id;
	int idPlayer;
	int torneiGiocati;
	int partiteGiocate;
	int punteggioTotale;
	double mediaPunteggio;
	int vittorie;
	double mediaPodio;
	

	public static ClassificaGlobaleDto build(ClassificaGlobale classificaGlobale) {

		ClassificaGlobaleDto result = new ClassificaGlobaleDto();
		BeanUtils.copyProperties(classificaGlobale, result);

		return result;
	}
	
}
