package it.jac.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "iscrizione_torneo") 	
public class Iscrizione {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "idTorneo")
	private int idTorneo;
	
	@Column(name = "idUtente")
	private int idUtente;
	
	@Column(name = "data_iscrizione")
	private String dataIscrizione;
	
}
