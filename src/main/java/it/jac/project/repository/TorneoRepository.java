package it.jac.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.project.entity.Torneo;

@Repository
public interface TorneoRepository extends CrudRepository<Torneo, Integer>{
	List<Torneo> findAllByGioco(String gioco);
	List<Torneo> findAllByPiattaforma(String piattaforma);
	List<Torneo> findAllByPostiLiberi(int postiLiberi);
	List<Torneo> findAllByStato(String stato);
	List<Torneo> findAllByIdCreatore(int idCreatore);

}
