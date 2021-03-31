package it.jac.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.project.entity.Classifica;

@Repository
public interface ClassificaRepository extends CrudRepository<Classifica, Integer>{

}
