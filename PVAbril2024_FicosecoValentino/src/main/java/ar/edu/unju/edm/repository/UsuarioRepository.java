package ar.edu.unju.edm.repository;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository <Usuario, Integer>{
	Usuario findBydni (Integer dni);
	Optional<Usuario> findById (Integer dni);
}