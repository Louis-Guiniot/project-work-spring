package it.jac.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.controller.UtenteRestController;
import it.jac.project.dto.ResponseDto;
import it.jac.project.dto.UtenteDto;
import it.jac.project.entity.Utente;
import it.jac.project.repository.UtenteRepository;

@Service
public class UtenteService {
	
	private static Logger log = LoggerFactory.getLogger(UtenteRestController.class);

	@Autowired
	UtenteRepository utenteRepository;

	public ResponseDto<Boolean> createUtente(Utente utente) {

		ResponseDto<Boolean> response = new ResponseDto<Boolean>();

		boolean found = false; // non ha trovato utenti con la stessa mail/username
		try {

			Iterator<Utente> iterator = this.utenteRepository.findAll().iterator();
			
			String emailDaComparare = utente.getEmail();
			String usernameDaComparare = utente.getUsername().toLowerCase();
			log.info(usernameDaComparare);
			log.info(emailDaComparare);


			while (iterator.hasNext()) {

				Utente u = iterator.next();
				
				if (u.getEmail().equals(emailDaComparare) || u.getUsername().toLowerCase().equals(usernameDaComparare)) {
					found = true;
					log.info("utente già presente con tali informazioni");
				}

			}

			if (!found) {
				
				log.info("utente creato");
				this.utenteRepository.save(utente);
				response.setResult(true);
				response.setResultTest(true);
			}

		} catch (Exception e) {

			response.setError("Utente non creato, email o username già presenti");

		}

		return response;

	}

	public ResponseDto<String> deleteUtenteById(int id) {

		ResponseDto<String> response = new ResponseDto<String>();

		try {

			this.utenteRepository.deleteById(id);

			response.setResult("Utente eliminato.");
			
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Utente non eliminato correttamente.");

		}

		return response;

	}

	public ResponseDto<List<UtenteDto>> findAllUtenti() {

		ResponseDto<List<UtenteDto>> response = new ResponseDto<List<UtenteDto>>();

		List<UtenteDto> result = new ArrayList<>();

		try {

			Iterator<Utente> iterator = this.utenteRepository.findAll().iterator();

			while (iterator.hasNext()) {

				Utente utente = iterator.next();
				result.add(UtenteDto.build(utente));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun utente trovato.");

		}

		return response;

	}

	public ResponseDto<UtenteDto> findUtenteById(int id) {

		ResponseDto<UtenteDto> response = new ResponseDto<UtenteDto>();

		try {

			Utente utente = this.utenteRepository.findById(id).get();
			
			if(utente != null) {
				
				log.info("utente trovato.");
				
				response.setResult(UtenteDto.build(utente));
				response.setResultTest(true);
			
			}



		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

	public ResponseDto<UtenteDto> findUtenteByUsernameAndPassword(String username, String password) {

		ResponseDto<UtenteDto> response = new ResponseDto<UtenteDto>();

		try {

			Utente utente = this.utenteRepository.findByUsernameAndPassword(username, password);
			
			if (
					utente != null && 
					null != utente.getPassword() &&
					null != utente.getUsername() && 
					utente.getUsername().equals(username) &&
					utente.getPassword().equals(password)
					
			) {

				log.info("utente trovato.");

				response.setResult(UtenteDto.build(utente));
				response.setResultTest(true);

			}else {
				
				log.info("utente non trovato.");
			}

		} catch (Exception e) {

			response.setError("Nessun utente trovato.");

		}

		return response;

	}

	public ResponseDto<UtenteDto> updateUtenteById(int id, Utente utentePassato) {

		ResponseDto<UtenteDto> response = new ResponseDto<UtenteDto>();

		try {

			Utente utente = this.utenteRepository.findById(id).get();

			if (utente != null) {

				if (utentePassato.getNome() != "")
					utente.setNome(utentePassato.getNome());

				if (utentePassato.getCognome() != "")
					utente.setCognome(utentePassato.getCognome());

				if (utentePassato.getUsername() != "")
					utente.setUsername(utentePassato.getUsername());
				
				if (utentePassato.getPassword() != "")
					utente.setPassword(utentePassato.getPassword());

				if (utentePassato.getEmail() != "")
					utente.setEmail(utentePassato.getEmail());

				if (utentePassato.getGenere() != "")
					utente.setGenere(utentePassato.getGenere());


				this.utenteRepository.save(utente);

				response.setResult(UtenteDto.build(utente));
				response.setResultTest(true);

			}

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
		}

		return response;

	}

}
