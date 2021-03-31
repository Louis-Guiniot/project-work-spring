package it.jac.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.dto.IscrizioneDto;
import it.jac.project.dto.PartecipanteDto;
import it.jac.project.dto.ResponseDto;
import it.jac.project.entity.Classifica;
import it.jac.project.entity.Iscrizione;
import it.jac.project.entity.Torneo;
import it.jac.project.repository.ClassificaRepository;
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
	ClassificaRepository classificaRepository;

	@Autowired
	TorneoService torneoService;
	
	public ResponseDto<?> iscriviti(int idTorneo, int idUtente) {

		ResponseDto<Boolean> response = new ResponseDto<Boolean>();

		Iscrizione iscrizione = new Iscrizione();
		iscrizione.setIdTorneo(idTorneo);
		iscrizione.setIdUtente(idUtente);

		Torneo torneo = this.torneoRepository.findById(idTorneo).get();

		// modifico numero iscrizioni aggiungendo 1
		int iscrizioniVecchie = torneo.getIscrizioni();
		int iscrizioniNuove = iscrizioniVecchie + 1;

		torneo.setIscrizioni(iscrizioniNuove);

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

		for (PartecipanteDto u : lista) {
			log.info("partecipante" + u.toString());
		}

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

		// mostro in console la lista ordinata
		log.info("----------------------- \n\n");
		for (PartecipanteDto p : lista) {
			log.info(p.toString());
		}

		// aggiorno iscrizioni torneo + posti liberi
		this.torneoService.updateTorneoById(idTorneo, torneo);

		// salvo iscrizione con id utente e torneo
		this.iscrizioneRepository.save(iscrizione);

		// per ogni utente in lista creo record in classifica con informazioni : 
		// ID: iscrizione , ID torneo, ID utente, Punteggio
		for (PartecipanteDto p : lista) {

			// creo nuovo record classifica
			Classifica c = new Classifica();

			c.setIdTorneo(idTorneo);
			c.setIdUtente(p.getIdPartecipante());
			c.setPunteggio(p.getPunteggioPartecipante());

			c.setIdIscrizione(findLastIscrizione().getResult().getId()); // ricevo l'ultima iscrizione fatta, così
																			// sincronizzare ogni record per iscrizione

			classificaRepository.save(c);
		}

		response.setResult(true);
		response.setResultTest(true);

		return response;
	}

	public ResponseDto<IscrizioneDto> findLastIscrizione() {

		ResponseDto<List<IscrizioneDto>> response = new ResponseDto<List<IscrizioneDto>>();

		int last = 0;
		try {
			Iterator<Iscrizione> iterator = this.iscrizioneRepository.findAll().iterator();

			while (iterator.hasNext()) {

				Iscrizione iscrizione = iterator.next();

				if (!iterator.hasNext()) {
					last = iscrizione.getId();
				}

			}

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}
		return findIscrizioneById(last);
	}

	public ResponseDto<IscrizioneDto> findIscrizioneById(int id) {

		ResponseDto<IscrizioneDto> response = new ResponseDto<IscrizioneDto>();

		try {

			Iscrizione iscrizione = this.iscrizioneRepository.findById(id).get();

			if (iscrizione != null) {

				response.setResult(IscrizioneDto.build(iscrizione));
				response.setResultTest(true);

			}

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

}
