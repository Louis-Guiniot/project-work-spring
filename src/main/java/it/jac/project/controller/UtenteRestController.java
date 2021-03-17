package it.jac.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.project.dto.ResponseDto;
import it.jac.project.entity.Utente;
import it.jac.project.service.UtenteService;


@RestController
@RequestMapping("/rest/utente")
public class UtenteRestController {
	
	private static Logger log = LoggerFactory.getLogger(UtenteRestController.class);
	
	@Autowired
	UtenteService utenteService;

	@GetMapping(path = "/elencoUtenti")
	public ResponseDto<?> findAllUtenti() {

		log.info("Richiesta della lista di tutti gli utenti");

		return utenteService.findAllUtenti();
	}
	
	@PostMapping("/creaUtente")
	public ResponseDto<?> createUtente(@RequestBody Utente utente){

		log.info("Richiesta di creazione nuovo utente");
		log.info("informaizoni passate --------------");

		log.info("username "+utente.getUsername());
		log.info("password "+utente.getPassword());
		log.info("nome "+utente.getNome());
		log.info("cognome "+utente.getCognome());
		log.info("data nascita "+utente.getDataNascita());
		log.info("genere "+utente.getGenere());
		log.info("email "+utente.getEmail());

		return utenteService.createUtente(utente);
	}
	
	@PostMapping("/aggiornaUtente")
	public ResponseDto<?> aggiornaUtente(@RequestBody Utente utente){

		log.info("Richiesta di aggiornamento utente");
		log.info("nuove informaizoni --------------");
		
		log.info("username "+utente.getUsername());
		log.info("password "+utente.getPassword());
		log.info("nome "+utente.getNome());
		log.info("cognome "+utente.getCognome());
		log.info("data nascita "+utente.getDataNascita());
		log.info("genere "+utente.getGenere());
		log.info("email "+utente.getEmail());
		
		int idDapassare = utente.getId();

		return utenteService.updateUtenteById(idDapassare,utente);
	}
	
	@DeleteMapping(path = "/deleteUtente/{id}")
	public ResponseDto<?> deleteUtenteById(@PathVariable int id) {

		log.info("Richiesta delete utente con id: ",id);
		
		return null;
//		return utenteService.deleteUtenteById(id);
	}
	
}
