package it.jac.project.controller;

import java.util.Random;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.project.dto.ResponseDto;
import it.jac.project.entity.Iscrizione;
import it.jac.project.entity.Torneo;
import it.jac.project.entity.Utente;
import it.jac.project.service.IscrizioneService;
import it.jac.project.service.TorneoService;

@RestController
@RequestMapping("/rest/torneo")
public class TorneoRestController {
	
private static Logger log = LoggerFactory.getLogger(TorneoRestController.class);
	
	@Autowired
	TorneoService torneoService;
	
	@Autowired
	IscrizioneService iscrizioneService;
	
	@GetMapping(path = "/elencoTornei")
	public ResponseDto<?> findAllTornei() {

		log.info("Richiesta della lista di tutti i tornei");

		return torneoService.findAllTornei();
	}

	
	@PostMapping("/creaTorneo")
	public ResponseDto<?> createTorneo(@RequestBody Torneo torneo){

		log.info("Richiesta di creazione nuovo torneo");
		log.info("informazioni passate --------------");

		log.info("nome "+torneo.getNome());
		log.info("gioco "+torneo.getGioco());
		log.info("piattaforma "+torneo.getPiattaforma());
		log.info("capienza "+torneo.getCapienza());
		log.info("capienza minima "+torneo.getCapienzaMinima());
		
		//iscrizioni generate randomicamente
		int iscrizioniRandom = (int) (Math.random() * (torneo.getCapienza() - torneo.getCapienzaMinima()));
		log.info("iscrizioni generate "+iscrizioniRandom);
		torneo.setIscrizioni(iscrizioniRandom);
		
		//posti liberi calcolati con capienza - iscrizioni
		int postiLiberi = torneo.getCapienza() - iscrizioniRandom;
		log.info("posti liberi "+postiLiberi);
		torneo.setPostiLiberi(postiLiberi);
		
		log.info("partite "+torneo.getPartite());
		log.info("quota"+torneo.getQuota());
		log.info("premio primo "+torneo.getPremioPrimo());
		log.info("premio secondo "+torneo.getPremioSecondo());
		log.info("premio terzo "+torneo.getPremioTerzo());
		log.info("id creatore "+torneo.getIdCreatore());
		
		torneo.setStato("PRONTO");
		
		log.info("descrizione "+torneo.getDescrizione());

		

		return torneoService.createTorneo(torneo);
	}
	
	@PostMapping("/aggiornaTorneo")
	public ResponseDto<?> aggiornaTorneo(@RequestBody Torneo torneo){

		log.info("Richiesta di aggiornamento torneo");
		log.info("nuove informazioni --------------");
		
		log.info("nome "+torneo.getNome());
		log.info("gioco "+torneo.getGioco());
		log.info("piattaforma "+torneo.getPiattaforma());
		log.info("capienza "+torneo.getCapienza());
		log.info("capienza minima"+torneo.getCapienzaMinima());
		log.info("posti disponibili"+torneo.getPostiLiberi());
		log.info("iscrizioni"+torneo.getIscrizioni());
		log.info("partite"+torneo.getPartite());
		log.info("quota"+torneo.getQuota());
		log.info("1^"+torneo.getPremioPrimo());
		log.info("2^"+torneo.getPremioSecondo());
		log.info("3^"+torneo.getPremioTerzo());
		log.info("stato"+torneo.getStato());
		log.info("descrizione "+torneo.getDescrizione());
		
		int idDapassare = torneo.getId();

		return torneoService.updateTorneoById(idDapassare,torneo);
	}
	
	@PostMapping(path = "/eliminaTorneo")
	public ResponseDto<?> deleteTorneoById(@RequestBody Torneo torneo) {

		int id = torneo.getId();
		
		log.info("Richiesta delete torneo con id: ",id);

		return torneoService.deleteTorneoById(id);
	}

}
