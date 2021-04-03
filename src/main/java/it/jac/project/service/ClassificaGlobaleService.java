package it.jac.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.dto.ClassificaDto;
import it.jac.project.dto.ClassificaGlobaleDto;
import it.jac.project.dto.ResponseDto;
import it.jac.project.entity.Classifica;
import it.jac.project.entity.ClassificaGlobale;
import it.jac.project.repository.ClassificaGlobaleRepository;

@Service
public class ClassificaGlobaleService {
	
	private static Logger log = LoggerFactory.getLogger(TorneoService.class);

	@Autowired
	ClassificaGlobaleRepository classificaGlobaleRepository;
	
	public ResponseDto<List<ClassificaGlobaleDto>> findGlobale() {

		ResponseDto<List<ClassificaGlobaleDto>> response = new ResponseDto<List<ClassificaGlobaleDto>>();

		List<ClassificaGlobaleDto> result = new ArrayList<>();

		try {

			Iterator<ClassificaGlobale> iterator = this.classificaGlobaleRepository.findAll().iterator();

			while (iterator.hasNext()) {

				ClassificaGlobale classificaGlobale = iterator.next();
				
					result.add(ClassificaGlobaleDto.build(classificaGlobale));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessuna classifica globale trovata.");

		}
		
		return response;

	}
	
	
}
