package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;

@Controller
public class UsuarioController {

	private static final Log VF = LogFactory.getLog(UsuarioController.class);

    @Autowired
    IUsuarioService unServicio;

    @GetMapping("/Usuario")
    public ModelAndView cargarUsuario() {
        ModelAndView cargaUsuario = new ModelAndView("formularioUsuario");
        cargaUsuario.addObject("nuevoUsuario", new Usuario());

        char[] tiposUsuario = {'V', 'A'};
        cargaUsuario.addObject("tiposUsuario", tiposUsuario);

        cargaUsuario.addObject("band", false);
        VF.warn("Cargando nuevo Usuario");
        return cargaUsuario;
    }
	
	@GetMapping("/listadoUsuario")
	public ModelAndView mostrarUsuario(){
		
		ModelAndView listadoUsuarios = new ModelAndView("mostrarUsuarios");
		listadoUsuarios.addObject("UsuarioListado", unServicio.listarUsuarios());
		
		return listadoUsuarios;
	}
	
	@GetMapping("/listaDeUsuarios")
	public ModelAndView mostrarUsuarios(){
		
		ModelAndView listaDeUsuarios = new ModelAndView("listaDeUsuarios");
		listaDeUsuarios.addObject("UsuarioListado", unServicio.listarUsuarios());
		
		return listaDeUsuarios;
	}
	
	@PostMapping("/guardarUsuario")
	public ModelAndView guardarUsuario(@Valid @ModelAttribute("nuevoUsuario") Usuario nUsuario, BindingResult resultado) {
		
		if(resultado.hasErrors()) {
			ModelAndView cargaUsuario = new ModelAndView("formularioUsuario");
			cargaUsuario.addObject("nuevoUsuario", nUsuario);
			return cargaUsuario;
		}
		
		ModelAndView listadoUsuarios = new ModelAndView("mostrarUsuarios");
		
			VF.warn("Mostrando nuevo Usuario"+nUsuario.getNombre());
		
		try {
			VF.warn("Guardando Usuario");
			unServicio.cargarUsuario(nUsuario);
		}catch(Exception e) {
			listadoUsuarios.addObject("CargadoUsuarioErrorMessage", e.getMessage());
			VF.error(e);
		}
				
		listadoUsuarios.addObject("UsuarioListado", unServicio.listarUsuarios());
		
		return listadoUsuarios;
	}
	
	//MODIFICAR
	
    @GetMapping("/modificarUsuario/{dni}")
    public ModelAndView modificarUsuario(@PathVariable(name="dni") Integer dni) {
        ModelAndView modificaUsuario = new ModelAndView("formularioUsuario");

        try {
            VF.warn("Modificando Usuario");
            Usuario Usuario = unServicio.listarUnUsuario(dni);
            modificaUsuario.addObject("nuevoUsuario", Usuario);
        } catch (Exception e) {
            modificaUsuario.addObject("modificarUsuarioErrorMessage", e.getMessage());
            VF.error(e);
        }

        char[] tiposUsuario = {'V', 'A'};
        modificaUsuario.addObject("tiposUsuario", tiposUsuario);

        modificaUsuario.addObject("band", true);
        return modificaUsuario;
    }

	
	// ELIMINAR
	@GetMapping("/eliminarUsuario/{dni}")
	public ModelAndView eliminarUsuario(@PathVariable(name="dni") Integer dni) {
		ModelAndView eliminarUsuario = new ModelAndView("mostrarUsuario");
		
		try {
			VF.warn("Eliminando Usuario");
			unServicio.eliminarUnUsuario(dni);
		}catch(Exception e) {
			eliminarUsuario.addObject("eliminarUsuarioErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		try {
			eliminarUsuario.addObject("UsuarioListado", unServicio.listarUsuarios());
		}catch(Exception e) {
			eliminarUsuario.addObject("listarUsuarioErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		return eliminarUsuario;
	}
	
	
}