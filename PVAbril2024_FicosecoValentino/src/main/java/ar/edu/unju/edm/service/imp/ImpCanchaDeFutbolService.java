package ar.edu.unju.edm.service.imp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.CanchaDeFutbol;
import ar.edu.unju.edm.repository.CanchaDeFutbolRepository;
import ar.edu.unju.edm.service.ICanchaDeFutbolService;

@Service
public class ImpCanchaDeFutbolService implements ICanchaDeFutbolService {

    @Autowired
	public CanchaDeFutbolRepository especialidadRepository;

   @Override
    public void cargarCanchaDeFutbol(CanchaDeFutbol unaCanchaDeFutbol) {
        especialidadRepository.save(unaCanchaDeFutbol);
    }

   @Override
   public void eliminarCanchaDeFutbol(Integer Codigo) {
       Optional<CanchaDeFutbol> aux = CanchaDeFutbolRepository.findByCodigo(Codigo);
       aux.ifPresent(CanchaDeFutbolRepository::delete);
   }

    @Override
    public ArrayList<CanchaDeFutbol> listarCanchasDeFutbol() {
        return (ArrayList<CanchaDeFutbol>) CanchaDeFutbolRepository.findAll();
    }

    @Override
    public CanchaDeFutbol listarUnaCanchaDeFutbol(Integer Codigo) {
        Optional<CanchaDeFutbol> CanchaDeFutbolOptional = CanchaDeFutbolRepository.findByCodigo(Codigo);
        return CanchaDeFutbolOptional.orElse(null);
    }

   @Override
	public CanchaDeFutbol modificarUnaCanchaDeFutbol(Integer Codigo, CanchaDeFutbol CanchaDeFutbolModificado) {
		Optional<CanchaDeFutbol> CanchaDeFutbolOptional = CanchaDeFutbolRepository.findByCodigo(Codigo);
		if (CanchaDeFutbolOptional.isPresent()) {
			CanchaDeFutbol CanchaDeFutbol = CanchaDeFutbolOptional.get();
			CanchaDeFutbol.setHorario1(CanchaDeFutbolModificado.getHorario1());
			CanchaDeFutbol.setHorario2(CanchaDeFutbolModificado.getHorario2());
			CanchaDeFutbol.setHorario3(CanchaDeFutbolModificado.getHorario3());
			CanchaDeFutbol.setCodigo(CanchaDeFutbolModificado.getCodigo());
			CanchaDeFutbolRepository.save(CanchaDeFutbol);
			returnCanchaDeFutbol;
		}
		return null;
	}


}
