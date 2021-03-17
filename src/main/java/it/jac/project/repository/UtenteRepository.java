package it.jac.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.project.entity.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Integer>{
	Utente findByUsernameAndPassword(String username, String password);
}
