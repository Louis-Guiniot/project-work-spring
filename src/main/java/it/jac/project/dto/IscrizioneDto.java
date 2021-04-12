package it.jac.project.dto;

import org.springframework.beans.BeanUtils;

import it.jac.project.entity.Iscrizione;
import lombok.Data;

@Data
public class IscrizioneDto {

	private int id;
	
	private int idUtente;

	private int idTorneo;
	
	private String dataIscrizione;
	
	public static IscrizioneDto build(Iscrizione iscrizione) {

		IscrizioneDto result = new IscrizioneDto();
		BeanUtils.copyProperties(iscrizione, result);

		return result;
	}

	
}
