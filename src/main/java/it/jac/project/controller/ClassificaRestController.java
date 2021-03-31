package it.jac.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.project.dto.ResponseDto;
import it.jac.project.service.ClassificaService;

@RestController
@RequestMapping("rest/classifica")
public class ClassificaRestController {
	
	private static Logger log = LoggerFactory.getLogger(TorneoRestController.class);

	@Autowired
	ClassificaService classificaService;
	
	@GetMapping(path = "/recordClassifica")
	public ResponseDto<?> findClassificaRecord() {

		log.info("Richiesta della classifica");

		return classificaService.findAllRecordOfClassifica();
	}
}
