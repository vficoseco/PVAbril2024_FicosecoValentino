package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.repository.UsuarioRepository;
import ar.edu.unju.edm.service.IUsuarioService;

@Service
@Qualifier("servicioUsuarioEnMySQL")
public class ImpUsuarioService implements IUsuarioService{
	
	@Autowired
	UsuarioRepository UsuarioRepository;

	@Override
	public void cargarUsuario(Usuario unUsuario) {
		unUsuario.setEstado(true);

		if(UsuarioRepository.findBydni(unUsuario.getDni()).isPresent()) {
			return;
		}
		
		UsuarioRepository.save(unUsuario);
	}

	@Override
	public void eliminarUnUsuario(Integer idUsuario) {
		Optional<Usuario> aux = Optional.of(new Usuario());
		aux =UsuarioRepository.findById(idUsuario);
		aux.get().setEstado(false);
		UsuarioRepository.save(aux.get());
	}

	@Override
	public Usuario listarUnUsuario(Integer idUsuario) {
		Optional<Usuario> aux = Optional.of(new Usuario());
		aux = UsuarioRepository.findById(idUsuario);
		
		return aux.get();
	}

	@Override
	public ArrayList<Usuario> listarUsuarios() {
		return (ArrayList<Usuario>) UsuarioRepository.findByEstado(true);
	}

	
	@Override
	public Usuario modificarUnUsuario(Integer dni, Usuario UsuarioModificado) {
		Optional<Usuario> UsuarioOptional = UsuarioRepository.findById(dni);
        if (UsuarioOptional.isPresent()) {
            Usuario Usuario = UsuarioOptional.get();
            Usuario.setApellido(UsuarioModificado.getApellido());
            Usuario.setNombre(UsuarioModificado.getNombre());
            String password = UsuarioModificado.getPassword();
            Usuario.setPassword(password);
            Usuario.setTipoUsuario(UsuarioModificado.getTipoUsuario());
            UsuarioRepository.save(Usuario);
            return Usuario;
        }
		return UsuarioModificado;
	}

}
