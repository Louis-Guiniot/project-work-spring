package it.jac.project.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.dto.ResponseDto;
import it.jac.project.dto.TorneoDto;
import it.jac.project.dto.UtenteDto;
import it.jac.project.entity.Iscrizione;
import it.jac.project.entity.Torneo;
import it.jac.project.repository.IscrizioneRepository;
import it.jac.project.repository.TorneoRepository;

@Service
public class IscrizioneService {
	
	private static Logger log = LoggerFactory.getLogger(IscrizioneService.class);

	@Autowired
	IscrizioneRepository iscrizioneRepository;
	
	@Autowired
	TorneoRepository torneoRepository;
	
	@Autowired
	TorneoService torneoService;

	public ResponseDto<?> iscriviti(int idTorneo, int idUtente) {
		
		ResponseDto<Boolean> response = new ResponseDto<Boolean>();

		
		Iscrizione iscrizione = new Iscrizione();
		iscrizione.setIdTorneo(idTorneo);
		iscrizione.setIdUtente(idUtente);
		
		Torneo torneo = this.torneoRepository.findById(idTorneo).get();
		
		//modifico numero iscrizioni aggiungendo 1
		int iscrizioniVecchie = torneo.getIscrizioni();
		int iscrizioniNuove = iscrizioniVecchie + 1;

		torneo.setIscrizioni(iscrizioniNuove);
		
		
		int punteggioUtente = 0;
		int punteggioAvversario = 0;
		
		List<UtenteDto> lista = new ArrayList<>();

		
		for(int i = 0; i<iscrizioniVecchie; i++) {
			
			UtenteDto ia = new UtenteDto ();
			ia.setId(i);
			
			for(int j = 0; j<torneo.getPartite(); j++) {
				punteggioAvversario += (int) (Math.random() * (10 - 0));
			}
					
			ia.setUsername(String.valueOf(punteggioAvversario));
			
			lista.add(ia);
			
			punteggioAvversario = 0;
		}
		
			for(int j = 0; j<torneo.getPartite(); j++) {
				int p = (int) (Math.random() * (10 - 0));
				log.info("p - "+ p);
				punteggioUtente += p;
		
		}
			
			log.info("punteggio utente - "+ punteggioUtente);
			
			
			for(UtenteDto u : lista) {
				log.info("ia punteggio "+ u.toString());
			}
			
		
				
		this.torneoService.updateTorneoById(idTorneo, torneo);
		
		this.iscrizioneRepository.save(iscrizione);
		
		response.setResult(true);
		response.setResultTest(true);
		
		return response;
	}

}
