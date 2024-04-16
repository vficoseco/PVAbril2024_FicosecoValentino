package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Usuario;

@Service
public interface IUsuarioService {
	public void cargarUsuario(Usuario unUsuario);
	public void eliminarUnUsuario(Integer dni);
	public ArrayList<Usuario> listarUsuarios ();
	public Usuario listarUnUsuario(Integer dni);
	public Usuario modificarUnUsuario(Integer dni, Usuario UsuarioModificada);
}