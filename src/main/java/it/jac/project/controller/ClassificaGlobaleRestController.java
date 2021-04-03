package it.jac.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.project.dto.ResponseDto;
import it.jac.project.service.ClassificaGlobaleService;

@RestController
@RequestMapping("rest/classificaGlobale")
public class ClassificaGlobaleRestController {
	
	private static Logger log = LoggerFactory.getLogger(TorneoRestController.class);

	@Autowired
	ClassificaGlobaleService classificaGlobaleService;
	
	@GetMapping(path = "/recordClassificaGlobale")
	public ResponseDto<?> findGlobaleRecord() {

		log.info("Richiesta della classifica globale");

		return classificaGlobaleService.findGlobale();
	}

}
