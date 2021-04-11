package it.jac.project.dto;

import org.springframework.beans.BeanUtils;

import it.jac.project.entity.Classifica;
import lombok.Data;

@Data
public class ClassificaDto {
	
	int id;
	int idTorneo;
	int idUtente;
	int idIscrizione;
	int punteggio;
	int posizione;
	
	public static ClassificaDto build(Classifica classifica) {

		ClassificaDto result = new ClassificaDto();
		BeanUtils.copyProperties(classifica, result);

		return result;
	}
}
