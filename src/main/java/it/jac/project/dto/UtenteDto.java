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
	private String email;
	private String password;
	private String genere;
	private String datanascita;
	
	public static UtenteDto build(Utente utente) {

		UtenteDto result = new UtenteDto();
		BeanUtils.copyProperties(utente, result);

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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getDatanascita() {
		return datanascita;
	}

	public void setDatanascita(String datanascita) {
		this.datanascita = datanascita;
	}

	@Override
	public String toString() {
		return "UtenteDto [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", email="
				+ email + ", password=" + password + ", genere=" + genere + ", datanascita=" + datanascita + "]";
	}
	
	
	
}
