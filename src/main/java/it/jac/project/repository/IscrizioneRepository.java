package it.jac.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.project.entity.Iscrizione;

@Repository
public interface IscrizioneRepository extends CrudRepository<Iscrizione, Integer>{

}
