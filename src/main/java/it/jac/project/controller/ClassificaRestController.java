package it.jac.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.project.dto.ResponseDto;
import it.jac.project.entity.Iscrizione;
import it.jac.project.entity.Utente;
import it.jac.project.service.ClassificaService;

@RestController
@RequestMapping("rest/classifica")
public class ClassificaRestController {

	private static Logger log = LoggerFactory.getLogger(TorneoRestController.class);

	@Autowired
	ClassificaService classificaService;

	@GetMapping(path = "/elencoRecordClassifica")
	public ResponseDto<?> findClassificaRecord() {

		log.info("Richiesta della classifica");

		return classificaService.findAllRecordOfClassifica();
	}

	@PostMapping("/simula")
	public ResponseDto<?> simulaTorneo(@RequestBody Iscrizione iscrizione) {

		log.info("Richiesta simulazione torneo");

		int idTorneo = iscrizione.getIdTorneo();

		int idUtente = iscrizione.getIdUtente();

		log.info("torneo " + idTorneo);
		log.info("utente " + idUtente);

		
		return this.classificaService.simulaTorneo(idTorneo, idUtente);
	}
}
