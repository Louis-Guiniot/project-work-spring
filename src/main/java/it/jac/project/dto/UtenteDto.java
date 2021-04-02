package it.jac.project.dto;

import org.springframework.beans.BeanUtils;

import it.jac.project.entity.Utente;
import lombok.Data;

@Data
public class UtenteDto {
	
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private String genere;
	private String datanascita;
	
	public static UtenteDto build(Utente utente) {

		UtenteDto result = new UtenteDto();
		BeanUtils.copyProperties(utente, result);

		return result;
	}

	
	@Override
	public String toString() {
		return "UtenteDto [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", email="
				+ email + ", password=" + password + ", genere=" + genere + ", datanascita=" + datanascita + "]";
	}
	
	
	
}
