package it.jac.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.dto.ClassificaDto;
import it.jac.project.dto.ResponseDto;
import it.jac.project.dto.TorneoDto;
import it.jac.project.entity.Classifica;
import it.jac.project.entity.Torneo;
import it.jac.project.repository.ClassificaRepository;

@Service
public class ClassificaService {
	
	private static Logger log = LoggerFactory.getLogger(TorneoService.class);

	@Autowired 
	ClassificaRepository classificaRepository;
	
	@Autowired 
	IscrizioneService iscrizioneService;
	
	public ResponseDto<List<ClassificaDto>> findAllRecordOfClassifica() {

		ResponseDto<List<ClassificaDto>> response = new ResponseDto<List<ClassificaDto>>();

		List<ClassificaDto> result = new ArrayList<>();

		try {

			Iterator<Classifica> iterator = this.classificaRepository.findAll().iterator();

			while (iterator.hasNext()) {

				Classifica torneo = iterator.next();
				
				if(torneo.getIdIscrizione() == iscrizioneService.findLastIscrizione().getResult().getId()) {
					result.add(ClassificaDto.build(torneo));

				}
			
			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessuna iscrizione trovata.");

		}
		
		return response;

	}

}
