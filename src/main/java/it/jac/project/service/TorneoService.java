package it.jac.project.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.project.dto.ResponseDto;
import it.jac.project.dto.TorneoDto;
import it.jac.project.dto.UtenteDto;
import it.jac.project.entity.Torneo;
import it.jac.project.entity.Utente;
import it.jac.project.repository.TorneoRepository;

@Service
public class TorneoService {

	private static Logger log = LoggerFactory.getLogger(TorneoService.class);

	@Autowired
	TorneoRepository torneoRepository;
	
	public ResponseDto<Boolean> createTorneo(Torneo torneo) {

		ResponseDto<Boolean> response = new ResponseDto<Boolean>();

		boolean found = false; // non ha trovato tornei con lo stesso nome
		try {

			Iterator<Torneo> iterator = this.torneoRepository.findAll().iterator();

			while (iterator.hasNext()) {

				Torneo u = iterator.next();
				if (u.getNome().equals(torneo.getNome())) {
					found = true;
				}

			}

			if (found == false) {
				this.torneoRepository.save(torneo);
				response.setResult(true);
				response.setResultTest(true);
			}

		} catch (Exception e) {

			response.setError("Torneo non creato, nome già presente");

		}
		
		return response;

	}

	public ResponseDto<String> deleteTorneoById(int id) {

		ResponseDto<String> response = new ResponseDto<String>();

		try {

			this.torneoRepository.deleteById(id);

			response.setResult("Torneo eliminato.");
			
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Torneo non eliminato correttamente.");

		}

		return response;

	}

	public ResponseDto<List<TorneoDto>> findAllTornei() {

		ResponseDto<List<TorneoDto>> response = new ResponseDto<List<TorneoDto>>();

		List<TorneoDto> result = new ArrayList<>();

		try {

			Iterator<Torneo> iterator = this.torneoRepository.findAll().iterator();

			while (iterator.hasNext()) {

				Torneo torneo = iterator.next();
				result.add(TorneoDto.build(torneo));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun torneo trovato.");

		}
		
		return response;

	}
	
	public ResponseDto<List<TorneoDto>> findAllTorneiByIdCreatore(int idCreatore) {

		ResponseDto<List<TorneoDto>> response = new ResponseDto<List<TorneoDto>>();

		List<TorneoDto> result = new ArrayList<>();

		try {

			Iterator<Torneo> iterator = torneoRepository.findAllByIdCreatore(idCreatore).iterator();

			while (iterator.hasNext()) {

				Torneo torneo = iterator.next();
				result.add(TorneoDto.build(torneo));

			}

			response.setResult(result);
			response.setResultTest(true);
			

		} catch (Exception e) {

			response.setError("Nessun torneo trovato.");

		}
		
		return response;

	}

	public ResponseDto<TorneoDto> findTorneoById(int id) {

		ResponseDto<TorneoDto> response = new ResponseDto<TorneoDto>();

		try {

			Torneo torneo = this.torneoRepository.findById(id).get();
			
			if(torneo != null) {
				
				log.info("torneo trovato.");
				
				response.setResult(TorneoDto.build(torneo));
				response.setResultTest(true);
			
			}



		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");

		}

		return response;

	}

	public ResponseDto<List<TorneoDto>> findTorneoByGame(String game) {

		ResponseDto<List<TorneoDto>> response = new ResponseDto<List<TorneoDto>>();

		List<TorneoDto> result = new ArrayList<>();

		try {

			Iterator<Torneo> iterator = this.torneoRepository.findAllByGioco(game).iterator();

			while (iterator.hasNext()) {

				Torneo torneo = iterator.next();
				result.add(TorneoDto.build(torneo));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun torneo trovato.");

		}

		return response;

	}
	
	public ResponseDto<List<TorneoDto>> findTorneoByPlatform(String platform) {

		ResponseDto<List<TorneoDto>> response = new ResponseDto<List<TorneoDto>>();

		List<TorneoDto> result = new ArrayList<>();

		try {

			Iterator<Torneo> iterator = this.torneoRepository.findAllByPiattaforma(platform).iterator();

			while (iterator.hasNext()) {

				Torneo torneo = iterator.next();
				result.add(TorneoDto.build(torneo));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun torneo trovato.");

		}

		return response;

	}
	
	public ResponseDto<List<TorneoDto>> findTorneoByPostiLiberi(int postiLiberi) {

		ResponseDto<List<TorneoDto>> response = new ResponseDto<List<TorneoDto>>();

		List<TorneoDto> result = new ArrayList<>();

		try {

			Iterator<Torneo> iterator = this.torneoRepository.findAllByPostiLiberi(postiLiberi).iterator();

			while (iterator.hasNext()) {

				Torneo torneo = iterator.next();
				result.add(TorneoDto.build(torneo));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun torneo trovato.");

		}

		return response;

	}
	
	public ResponseDto<List<TorneoDto>> findTorneoByStato(String stato) {

		ResponseDto<List<TorneoDto>> response = new ResponseDto<List<TorneoDto>>();

		List<TorneoDto> result = new ArrayList<>();

		try {

			Iterator<Torneo> iterator = this.torneoRepository.findAllByStato(stato).iterator();

			while (iterator.hasNext()) {

				Torneo torneo = iterator.next();
				result.add(TorneoDto.build(torneo));

			}

			response.setResult(result);
			response.setResultTest(true);

		} catch (Exception e) {

			response.setError("Nessun torneo trovato.");

		}

		return response;

	}

	public ResponseDto<TorneoDto> updateTorneoById(int id, Torneo torneoPassato) {

		ResponseDto<TorneoDto> response = new ResponseDto<TorneoDto>();

		try {

			Torneo torneo = this.torneoRepository.findById(id).get();

			if (torneo != null) {

				if (torneoPassato.getNome() != "")
					torneo.setNome(torneoPassato.getNome());

				if (torneoPassato.getGioco() != "")
					torneo.setGioco(torneoPassato.getGioco());

				if (torneoPassato.getPiattaforma() != "")
					torneo.setPiattaforma(torneoPassato.getPiattaforma());

				if (torneoPassato.getCapienza() != 0)
					torneo.setCapienza(torneoPassato.getCapienza());

				if (torneoPassato.getCapienzaMinima() != 0)
					torneo.setCapienzaMinima(torneoPassato.getCapienzaMinima());
				
				if (torneoPassato.getCapienzaMinima() != 0)
					torneo.setCapienzaMinima(torneoPassato.getCapienzaMinima());
				
				if (torneoPassato.getIscrizioni() != 0)
					torneo.setIscrizioni(torneoPassato.getIscrizioni());

				if (torneoPassato.getIscrizioni() != 0)
					torneo.setIscrizioni(torneoPassato.getIscrizioni());
				
				if (torneoPassato.getQuota() != 0)
					torneo.setQuota(torneoPassato.getQuota());
				
				if (torneoPassato.getPartite() != 0)
					torneo.setPartite(torneoPassato.getPartite());
				
				if (torneoPassato.getPremioPrimo() != "")
					torneo.setPremioPrimo(torneoPassato.getPremioPrimo());

				if (torneoPassato.getPremioSecondo() != "")
					torneo.setPremioSecondo(torneoPassato.getPremioSecondo());

				if (torneoPassato.getPremioTerzo() != "")
					torneo.setPremioTerzo(torneoPassato.getPremioTerzo());
				
				if(torneoPassato.getStato() != "")
					torneo.setStato(torneoPassato.getStato());
				
				this.torneoRepository.save(torneo);

				response.setResult(TorneoDto.build(torneo));
				response.setResultTest(true);

			}

		} catch (Exception e) {

			response.setError("Nessun elemento trovato.");
		}

		return response;

	}
	
}
