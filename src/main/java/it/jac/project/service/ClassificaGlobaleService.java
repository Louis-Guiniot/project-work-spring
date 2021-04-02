package it.jac.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.repository.ClassificaGlobaleRepository;

@Service
public class ClassificaGlobaleService {
	
	private static Logger log = LoggerFactory.getLogger(TorneoService.class);

	@Autowired
	ClassificaGlobaleRepository classificaGlobaleRepository;
	
	
}
