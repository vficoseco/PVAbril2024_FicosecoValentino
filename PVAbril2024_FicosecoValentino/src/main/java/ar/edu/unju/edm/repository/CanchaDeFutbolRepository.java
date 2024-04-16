package ar.edu.unju.edm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.CanchaDeFutbol;

@Repository
public interface CanchaDeFutbolRepository extends CrudRepository <CanchaDeFutbol, Integer>{
	CanchaDeFutbol findByCodigo (Integer Codigo);
	
	
}

