package it.jac.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.project.dto.ResponseDto;
import it.jac.project.entity.Iscrizione;
import it.jac.project.service.IscrizioneService;

@RestController
@RequestMapping("rest/iscrizione")
public class IscrizioneRestController {

	private static Logger log = LoggerFactory.getLogger(TorneoRestController.class);

	@Autowired
	IscrizioneService iscrizioneService;
	
	@PostMapping("/iscriviti")
	public ResponseDto<?> iscriviti(@RequestBody Iscrizione iscrizione) {

		log.info("Richiesta iscrizione torneo");

		int idTorneo = iscrizione.getIdTorneo();

		int idUtente = iscrizione.getIdUtente();

		log.info("torneo " + idTorneo);
		log.info("utente " + idUtente);

		
		return this.iscrizioneService.iscriviti(idTorneo, idUtente);
	}
}
