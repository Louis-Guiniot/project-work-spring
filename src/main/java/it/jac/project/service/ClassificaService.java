package it.jac.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.dto.ClassificaDto;
import it.jac.project.dto.PartecipanteDto;
import it.jac.project.dto.ResponseDto;
import it.jac.project.dto.TorneoDto;
import it.jac.project.entity.Classifica;
import it.jac.project.entity.ClassificaGlobale;
import it.jac.project.entity.Iscrizione;
import it.jac.project.entity.Torneo;
import it.jac.project.repository.ClassificaGlobaleRepository;
import it.jac.project.repository.ClassificaRepository;
import it.jac.project.repository.TorneoRepository;

@Service
public class ClassificaService {

	private static Logger log = LoggerFactory.getLogger(TorneoService.class);

	@Autowired
	ClassificaRepository classificaRepository;

	@Autowired
	ClassificaGlobaleRepository classificaGlobaleRepository;

	@Autowired
	TorneoRepository torneoRepository;

	@Autowired
	IscrizioneService iscrizioneService;

	public ResponseDto<List<ClassificaDto>> findAllRecordOfClassifica() {

		ResponseDto<List<ClassificaDto>> response = new ResponseDto<List<ClassificaDto>>();

		List<ClassificaDto> result = new ArrayList<>();
		
		int idIscrizione = iscrizioneService.findLastIscrizione().getResult().getId();

		try {

			Iterator<Classifica> iterator = classificaRepository.findAll().iterator();
					
			while (iterator.hasNext()) {

				Classifica classifica = iterator.next();
								
				if (classifica.getIdIscrizione() == idIscrizione) {
	
					result.add(ClassificaDto.build(classifica));

				}

			}
			
			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessuna classifica trovata.");

		}

		return response;

	}

	public ResponseDto<?> simulaTorneo(int idTorneo, int idUtente) {

			
		try {
			
			Torneo torneo = this.torneoRepository.findById(idTorneo).get();
			int iscrizioniVecchie = torneo.getIscrizioni()-1;

			int punteggioUtente = 0;
			int punteggioAvversario = 0;

			// lista che contiene tutti i partecipanti
			List<PartecipanteDto> lista = new ArrayList<>();

			PartecipanteDto me = new PartecipanteDto();

			// genero punteggio totale dell'utente iscritto
			for (int j = 0; j < torneo.getPartite(); j++) {
				int p = (int) (Math.random() * (10 - 0)); // genero numero casuale tra 0 e 10
				punteggioUtente += p;
			}
			
			me.setIdPartecipante(0); // setto 0 come id per ricordarmi quale è il mio / uso anche amI true
			me.setPunteggioPartecipante(punteggioUtente);
			me.setAmI(true);

			// creo n partecipanti e faccio for per n partite calcolando il punteggio finale
			for (int i = 1; i < iscrizioniVecchie; i++) {

				PartecipanteDto ia = new PartecipanteDto();
				ia.setIdPartecipante(i);
				ia.setAmI(false);

				for (int j = 0; j < torneo.getPartite(); j++) {
					punteggioAvversario += (int) (Math.random() * (10 - 0));
				}

				ia.setPunteggioPartecipante(punteggioAvversario);

				lista.add(ia);

				punteggioAvversario = 0;
			}

			lista.add(me);

			PartecipanteDto temp = new PartecipanteDto();

			boolean sorted = false;

			// inizio ciclo for per ordinamento in base al punteggio raggiunto
			while (!sorted) {
				sorted = true;
				for (int i = 0; i < lista.size() - 1; i++) {
					if (lista.get(i).compareTo(lista.get(i + 1)) == -1) {
						temp = lista.get(i);
						lista.set(i, lista.get(i + 1));
						lista.set(i + 1, temp);
						sorted = false;
					}
				}
			}

			int posizione = 1;
			int podioUtente = 0;
			int posizioneUtente = 0;
			

			log.info("giocatori totali..." + lista.size());

			for (PartecipanteDto p : lista) {

				// se id partecipante = 0 salvo posizione
				if (p.getIdPartecipante() == 0) {
//					iscrizione.setPosizione(posizione);
					
					posizioneUtente = posizione;
				
					// aggiungo vittoria se podio
					if (posizione == 1 || posizione == 2 || posizione == 3) {
						podioUtente = 1;
					}
				}

				posizione++;

			}
			
			// per ogni utente in lista creo record in classifica con informazioni :
			// ID: iscrizione , ID torneo, ID utente, Punteggio
			for (PartecipanteDto p : lista) {

				// creo nuovo record classifica
				Classifica c = new Classifica();

				c.setIdTorneo(idTorneo);
				c.setIdUtente(p.getIdPartecipante());
				c.setPunteggio(p.getPunteggioPartecipante());

				int idIscrizione = iscrizioneService.findLastIscrizione().getResult().getId();
				
				c.setIdIscrizione(idIscrizione); // ricevo l'ultima iscrizione fatta, così
																				// sincronizzare ogni record per iscrizione
				
				classificaRepository.save(c);

			}
			
			
			
			//per nuovo utente
			
			//da aggiornare		


			ClassificaGlobale classificaGlobale = this.classificaGlobaleRepository.findByIdPlayer(idUtente);
			
			try {
				
				
				if(classificaGlobale == null) {
					
					ClassificaGlobale nuovaClassificaGlobale = new ClassificaGlobale();
					
					nuovaClassificaGlobale.setIdPlayer(idUtente);
					nuovaClassificaGlobale.setPunteggioTotale(punteggioUtente);
					nuovaClassificaGlobale.setMediaPodio(posizioneUtente/1);
					nuovaClassificaGlobale.setMediaPunteggio(punteggioUtente);
					nuovaClassificaGlobale.setPartiteGiocate(torneo.getPartite());
					nuovaClassificaGlobale.setVittorie(podioUtente);
					nuovaClassificaGlobale.setTorneiGiocati(1);
					
					classificaGlobaleRepository.save(nuovaClassificaGlobale);
					
					log.info("salvata nuova");
					
				}else {
					
					classificaGlobale.setPunteggioTotale(classificaGlobale.getPunteggioTotale()+punteggioUtente);
					classificaGlobale.setPartiteGiocate(classificaGlobale.getPartiteGiocate() + torneo.getPartite());
					classificaGlobale.setTorneiGiocati(classificaGlobale.getTorneiGiocati()+1);
					classificaGlobale.setMediaPunteggio((double)(classificaGlobale.getPunteggioTotale()/classificaGlobale.getTorneiGiocati()));
					classificaGlobale.setMediaPodio(classificaGlobale.getMediaPodio()+posizioneUtente/classificaGlobale.getTorneiGiocati());
					classificaGlobale.setVittorie(classificaGlobale.getVittorie() + podioUtente);
					
					
					
					classificaGlobaleRepository.save(classificaGlobale);
					
					log.info("aggiornata classificaGlobale");

				}
				
				log.info("-------- classificaGlobale : "+classificaGlobale);
			
			}catch (Exception e) {
	
			}
			
		}catch (Exception e) {
		
		}
			
			
		return null;
		
	}
	
}
