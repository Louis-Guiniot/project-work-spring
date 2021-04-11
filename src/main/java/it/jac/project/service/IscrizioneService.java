package it.jac.project.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import it.jac.project.entity.ClassificaGlobale;
import it.jac.project.entity.Iscrizione;
import it.jac.project.entity.Torneo;
import it.jac.project.repository.ClassificaGlobaleRepository;
import it.jac.project.repository.ClassificaRepository;
import it.jac.project.repository.IscrizioneRepository;
import it.jac.project.repository.TorneoRepository;

@Service
public class IscrizioneService {

	private static Logger log = LoggerFactory.getLogger(IscrizioneService.class);

	private static SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	
	@Autowired
	IscrizioneRepository iscrizioneRepository;

	@Autowired
	TorneoRepository torneoRepository;

	@Autowired
	ClassificaRepository classificaRepository;

	@Autowired
	ClassificaGlobaleRepository classificaGlobaleRepository;

	@Autowired
	TorneoService torneoService;

	public ResponseDto<?> iscriviti(int idTorneo, int idUtente) {

		ResponseDto<Boolean> response = new ResponseDto<Boolean>();

		// genero una nuova iscrizione
		Iscrizione iscrizione = new Iscrizione();
		iscrizione.setIdTorneo(idTorneo);
		iscrizione.setIdUtente(idUtente);

		Torneo torneo = this.torneoRepository.findById(idTorneo).get();

		// modifico numero iscrizioni aggiungendo 1
		int iscrizioniVecchie = torneo.getIscrizioni();
		int iscrizioniNuove = iscrizioniVecchie + 1;

		torneo.setIscrizioni(iscrizioniNuove);

		
		// aggiorno iscrizioni torneo + posti liberi
		this.torneoService.updateIscrizioniTorneoById(idTorneo, torneo);
		
		
		Date dataIscrizione = new Date(System.currentTimeMillis());
		String dataFormattata = formatter.format(dataIscrizione);
		
		iscrizione.setDataIscrizione(dataFormattata);

		// salvo iscrizione con id utente e torneo
		this.iscrizioneRepository.save(iscrizione);
		

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
