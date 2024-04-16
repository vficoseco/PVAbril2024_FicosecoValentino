package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CanchaDeFutbol;

@Service
public interface ICanchaDeFutbolService {
	public void cargarCanchaDeFutbol(CanchaDeFutbol unaCanchaDeFutbol);
	public void eliminarCanchaDeFutbol(Integer Codigo);
	public ArrayList<CanchaDeFutbol> listarCanchasDeFutbol ();
	public CanchaDeFutbol listarUnaCanchaDeFutbol(Integer Codigo);
	public CanchaDeFutbol modificarUnaCanchaDeFutbol(Integer Codigo, CanchaDeFutbol CanchaDeFutbolModificada);
}