package it.jac.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.jac.project.entity.Torneo;

public interface TorneoRepository extends CrudRepository<Torneo, Integer>{
	List<Torneo> findAllByGioco(String gioco);
	List<Torneo> findAllByPiattaforma(String piattaforma);
	List<Torneo> findAllByPostiLiberi(int postiLiberi);
}
