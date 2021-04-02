package it.jac.project.dto;

import org.springframework.beans.BeanUtils;

import it.jac.project.entity.Classifica;
import lombok.Data;

@Data
public class ClassificaDto {
	
	int id;
	int idIscrizione;
	int idTorneo;
	int idUtente;
	int punteggio;
	int posizione;
	
	public static ClassificaDto build(Classifica iscrizione) {

		ClassificaDto result = new ClassificaDto();
		BeanUtils.copyProperties(iscrizione, result);

		return result;
	}
}
