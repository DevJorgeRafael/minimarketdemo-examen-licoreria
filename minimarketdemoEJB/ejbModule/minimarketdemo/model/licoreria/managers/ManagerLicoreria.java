package minimarketdemo.model.licoreria.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.LicLicor;
import minimarketdemo.model.core.entities.LicTiposLicor;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerLicoreria
 */
@Stateless
@LocalBean
public class ManagerLicoreria {
	@EJB
	private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerLicoreria() {
        
    }
    
    //LICORES
    public LicLicor insertarLicLicor(LicLicor licLicor) throws Exception {
    	mDAO.insertar(licLicor);
    	return licLicor;
    }
    
    @SuppressWarnings("unchecked")
    public List<LicLicor> findAllLicLicor() {
    	return mDAO.findAll(LicLicor.class);
    }
    
    public LicLicor findLicLicorById(int idLicor) throws Exception {
    	return (LicLicor) mDAO.findById(LicLicor.class, idLicor);
    }
    
    public LicLicor actualizarLicLicor(LicLicor licLicor) throws Exception {
    	LicLicor licLicorExistente = findLicLicorById(licLicor.getIdLicores());
    	licLicorExistente.setDescripcion(licLicor.getDescripcion());
    	licLicorExistente.setNombreLicor(licLicor.getNombreLicor());
    	licLicorExistente.setPorcentajeAlcohol(licLicor.getPorcentajeAlcohol());
    	licLicorExistente.setVolumen(licLicor.getVolumen());
    	licLicorExistente.setLicTiposLicor(licLicor.getLicTiposLicor());
    	mDAO.actualizar(licLicorExistente);
    	return licLicorExistente;
    }
    
    public void eliminarLicLicor(int idLicor) throws Exception {
    	mDAO.eliminar(LicLicor.class, idLicor);
    }
    
    //LicTiposLicor
    public LicTiposLicor insertaLicTiposLicor(LicTiposLicor licTiposLicor) throws Exception {
    	mDAO.insertar(licTiposLicor);
    	return licTiposLicor;
    }
    
    @SuppressWarnings("unchecked")
    public List<LicTiposLicor> findAllLicTiposLicor() {
    	return mDAO.findAll(LicTiposLicor.class);
    }
        
    public LicTiposLicor findLicTiposLicorById(int idTipoLicor) {
    	try {
    		return (LicTiposLicor) mDAO.findById(LicTiposLicor.class, idTipoLicor);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public LicTiposLicor actualizarLicTiposLicor(LicTiposLicor licTiposLicor) throws Exception {
    	LicTiposLicor licTiposLicorExistente = findLicTiposLicorById(licTiposLicor.getIdLicTiposLicores());
    	licTiposLicorExistente.setDescripcion(licTiposLicor.getDescripcion());
    	licTiposLicorExistente.setNombreTipo(licTiposLicor.getNombreTipo());
    	mDAO.actualizar(licTiposLicorExistente);
    	return licTiposLicorExistente;
    }
    
    public void eliminarLicTiposLicor(int idTipoLicor) throws Exception {
    	mDAO.eliminar(LicTiposLicor.class, idTipoLicor);
    }
}
