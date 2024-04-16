package ar.edu.unju.edm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;

@SpringBootApplication
public class PVAbril2024_FicosecoValentinoApplication {

    public static void main(String[] args) {
    	 Integer dni = 12345678;
    	 Usuario usuario = new Usuario(dni, "a", "a", 'a', "a");
    	     usuario = new Usuario(dni, "a", "a", 'a', "a");
        	 usuario.setDni(dni);
        	 usuario.setNombre("Nombre");
        	 usuario.setApellido("Apellido");
        	 usuario.setPassword("password");
        	 usuario.setTipoUsuario('A');
             SpringApplication.run(PVAbril2024_FicosecoValentinoApplication.class, args);
    }
   
    }