package it.jac.project.repository;

import org.springframework.data.repository.CrudRepository;
import it.jac.project.entity.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Integer>{
	Utente findByUsernameAndPassword(String username, String password);
}
