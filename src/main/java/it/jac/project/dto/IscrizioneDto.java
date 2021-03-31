package it.jac.project.dto;

import org.springframework.beans.BeanUtils;

import it.jac.project.entity.Iscrizione;

public class IscrizioneDto {

	private int id;
	
	private int idUtente;

	private int idTorneo;
	
	public static IscrizioneDto build(Iscrizione iscrizione) {

		IscrizioneDto result = new IscrizioneDto();
		BeanUtils.copyProperties(iscrizione, result);

		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}
	
}
