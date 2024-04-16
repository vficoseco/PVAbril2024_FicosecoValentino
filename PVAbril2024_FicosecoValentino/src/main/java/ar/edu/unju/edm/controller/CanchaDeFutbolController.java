package ar.edu.unju.edm.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.CanchaDeFutbol;
import ar.edu.unju.edm.service.ICanchaDeFutbolService;

@Controller
public class CanchaDeFutbolController {
	
	private static final Log VF = LogFactory.getLog(CanchaDeFutbolController.class);
	
	@Autowired
	CanchaDeFutbol unCanchaDeFutbol;
	
	@Autowired
	ICanchaDeFutbolService unServicio;
	
	@GetMapping("/CanchaDeFutbol")
	public ModelAndView cargarCanchaDeFutbol() {
		ModelAndView cargaCanchaDeFutbol = new ModelAndView("formularioCanchaDeFutbol");
		cargaCanchaDeFutbol.addObject("nuevaCanchaDeFutbol", unCanchaDeFutbol);
		
		cargaCanchaDeFutbol.addObject("band", false);
		VF.warn("Cargando nueva CanchaDeFutbol");
		return cargaCanchaDeFutbol;
	}
	@GetMapping("/CanchaDeFutbol/{Codigo}")
	public ModelAndView cargarCanchaDeFutbolCodigo(@PathVariable(name="Codigo") Integer Codigo) {
		ModelAndView listadoCanchaDeFutbols = new ModelAndView("perfilCanchaDeFutbol");
		
		listadoCanchaDeFutbols.addObject("CanchaDeFutbol",unServicio.listarUnaCanchaDeFutbol(Codigo));
		
		return listadoCanchaDeFutbols;
	}
	@GetMapping("/listadoCanchaDeFutbol")
	public ModelAndView mostrarCanchaDeFutbol() {
		ModelAndView listadoCanchaDeFutbols = new ModelAndView("mostrarCanchaDeFutbols");
		listadoCanchaDeFutbols.addObject("CanchaDeFutbolListado", unCanchaDeFutbol);
		
		listadoCanchaDeFutbols.addObject("CanchaDeFutbolListado",unServicio.listarCanchasDeFutbol());
		
		return listadoCanchaDeFutbols;
	}
	
	@GetMapping("/listaDeCanchaDeFutbols")
	public ModelAndView mostrarCanchaDeFutbols() {
		ModelAndView listadoCanchaDeFutbols = new ModelAndView("listaDeCanchaDeFutbols");
		listadoCanchaDeFutbols.addObject("CanchaDeFutbolListado", unCanchaDeFutbol);
		
		listadoCanchaDeFutbols.addObject("CanchaDeFutbolListado",unServicio.listarCanchasDeFutbol());
		
		return listadoCanchaDeFutbols;
	}
	
	@PostMapping(value = "/guardarCanchaDeFutbol", consumes = "multipart/form-data")
public ModelAndView guardarCanchaDeFutbol(@ModelAttribute("nuevaCanchaDeFutbol") CanchaDeFutbol CanchaDeFutbolnueva, @RequestParam("file") MultipartFile[] archivo, BindingResult resultado) {

    if (resultado.hasErrors()) {
        ModelAndView cargaCanchaDeFutbol = new ModelAndView("formularioCanchaDeFutbol");
        cargaCanchaDeFutbol.addObject("nuevaCanchaDeFutbol", CanchaDeFutbolnueva);
        return cargaCanchaDeFutbol;
    }
		ModelAndView listadoCanchaDeFutbols = new ModelAndView("mostrarCanchaDeFutbols");

        VF.warn("Mostrando la nueva Cancha de Futbol " + CanchaDeFutbolnueva.getCapacidad());
    try {
        unServicio.cargarCanchaDeFutbol(CanchaDeFutbolnueva);
    } catch (Exception e) {
        listadoCanchaDeFutbols.addObject("cargaCanchaDeFutbolErrorMessage", e.getMessage());
        VF.error(e);
    }

    listadoCanchaDeFutbols.addObject("CanchaDeFutbolListado", unServicio.listarCanchasDeFutbol());

    return listadoCanchaDeFutbols;
}
	
	
	@GetMapping("/modificarCanchaDeFutbol/{LgetCodigo}")
	public ModelAndView modificarCanchaDeFutbol(@PathVariable(name="LgetCodigo") Integer Codigo) {
		ModelAndView editarCanchaDeFutbol = new ModelAndView("formularioCanchaDeFutbol");
		
		try {
			editarCanchaDeFutbol.addObject("nuevaCanchaDeFutbol",unServicio.listarUnaCanchaDeFutbol(Codigo));
		}catch(Exception e) {
			editarCanchaDeFutbol.addObject("modificarCanchaDeFutbolErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		VF.warn("CanchaDeFutbol a modificar: " + Codigo);
		editarCanchaDeFutbol.addObject("band", true);
		
		return editarCanchaDeFutbol;
	}
	
	@PostMapping(value ="/modificarCanchaDeFutbol", consumes="multipart/form-data")
	public ModelAndView modificarCanchaDeFutbol(@ModelAttribute("nuevaCanchaDeFutbol") CanchaDeFutbol CanchaDeFutbolnueva, @RequestParam("file") MultipartFile[] archivo){
		ModelAndView listadoCanchaDeFutbols = new ModelAndView("mostrarCanchaDeFutbols");
		
		try {
			VF.warn("CanchaDeFutbol modificado: " + CanchaDeFutbolnueva.getCodigo());
			unServicio.cargarCanchaDeFutbol(CanchaDeFutbolnueva);
		}catch(Exception e) {
			listadoCanchaDeFutbols.addObject("cargaCanchaDeFutbolErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		listadoCanchaDeFutbols.addObject("CanchaDeFutbolListado",unServicio.listarCanchasDeFutbol());
		
		return listadoCanchaDeFutbols;
	}
	
	//ELIMINAR
	
	@GetMapping("/eliminarCanchaDeFutbol/{LgetCodigo}")
	public ModelAndView eliminarCanchaDeFutbol(@PathVariable(name="LgetCodigo") Integer Codigo) {
		ModelAndView eliminarCanchaDeFutbol = new ModelAndView("mostrarCanchaDeFutbols");
		
		try {
			VF.warn("Eliminando CanchaDeFutbol");
			unServicio.eliminarCanchaDeFutbol(Codigo);
		}catch(Exception e) {
			eliminarCanchaDeFutbol.addObject("eliminarCanchaDeFutbolErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		try {
			VF.warn("Listando CanchaDeFutbols");
			eliminarCanchaDeFutbol.addObject("CanchaDeFutbolListado", unServicio.listarCanchasDeFutbol());
		}catch(Exception e) {
			eliminarCanchaDeFutbol.addObject("listarCanchaDeFutbolErrorMessage", e.getMessage());
			VF.error(e);
		}
		
		return eliminarCanchaDeFutbol;
	}

}

