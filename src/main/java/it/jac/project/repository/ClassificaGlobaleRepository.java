package it.jac.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.project.entity.ClassificaGlobale;

@Repository
public interface ClassificaGlobaleRepository extends CrudRepository<ClassificaGlobale, Integer>{

}
