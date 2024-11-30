package minimarketdemo.controller.licoreria;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.licoreria.managers.ManagerLicoreria;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;
import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.LicLicor;
import minimarketdemo.model.core.entities.LicTiposLicor;

@Named
@SessionScoped
public class BeanLicoreria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ManagerLicoreria mLicoreria;
	@EJB
	private ManagerSeguridades mSeguridades;
	
	private List<LicLicor> listaLicores;
    private List<LicTiposLicor> listaTiposLicores;
    private LicLicor nuevoLicor;
    private LicLicor edicionLicor;
    private LicLicor licorSeleccionado;
    private LicTiposLicor nuevoTipoLicor;
    private LicTiposLicor edicionTipoLicor;
    private LicTiposLicor tipoLicorSeleccionado;
	
	public BeanLicoreria() {
		nuevoLicor = new LicLicor();
		nuevoLicor.setLicTiposLicor(new LicTiposLicor());
        edicionLicor = new LicLicor();
        edicionLicor.setLicTiposLicor(new LicTiposLicor());
        nuevoTipoLicor = new LicTiposLicor();
        edicionTipoLicor = new LicTiposLicor();
	}
	
	@PostConstruct
	public void incializacion() {
		listaLicores = mLicoreria.findAllLicLicor();
		listaTiposLicores = mLicoreria.findAllLicTiposLicor();
	}
	
	public String actionCargarLicores() {
		listaLicores = mLicoreria.findAllLicLicor();
		return "licores?faces-redirect=true";
	}
	
	public void actionListenerInsertarLicor() {
	    try {
	        LicTiposLicor tipoLicorSeleccionado = mLicoreria.findLicTiposLicorById(nuevoLicor.getLicTiposLicor().getIdLicTiposLicores());
	        if (tipoLicorSeleccionado != null) {
	            nuevoLicor.setLicTiposLicor(tipoLicorSeleccionado);
	            mLicoreria.insertarLicLicor(nuevoLicor);
	            listaLicores = mLicoreria.findAllLicLicor();
	            JSFUtil.crearMensajeINFO("Licor creado correctamente.");
	            nuevoLicor = new LicLicor(); 
	        } else {
	            JSFUtil.crearMensajeERROR("Tipo de licor no válido.");
	        }
	    } catch (Exception e) {
	        JSFUtil.crearMensajeERROR(e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	public void actionListenerCargarLicor(LicLicor licor) {
	    edicionLicor = licor;
	}

	
	public void actionListenerGuardarEdicionLicor() {
	    try {
	        if (edicionLicor.getLicTiposLicor() != null 
	            && edicionLicor.getLicTiposLicor().getIdLicTiposLicores() != null) {

	            LicTiposLicor tipoLicorSeleccionado = mLicoreria.findLicTiposLicorById(edicionLicor.getLicTiposLicor().getIdLicTiposLicores());

	            if (tipoLicorSeleccionado != null) {
	                edicionLicor.setLicTiposLicor(tipoLicorSeleccionado);
	                mLicoreria.actualizarLicLicor(edicionLicor);
	                listaLicores = mLicoreria.findAllLicLicor();
	                JSFUtil.crearMensajeINFO("Licor editado exitosamente.");
	                edicionLicor = new LicLicor();
	            } else {
	                JSFUtil.crearMensajeERROR("Tipo de licor no válido.");
	            }
	        } else {
	            JSFUtil.crearMensajeERROR("Debe seleccionar un tipo de licor.");
	        }
	    } catch (Exception e) {
	        JSFUtil.crearMensajeERROR(e.getMessage());
	        e.printStackTrace();
	    }
	}


    public void actionListenerEliminarLicor(int idLicor) {
        try {
            mLicoreria.eliminarLicLicor(idLicor);
            listaLicores = mLicoreria.findAllLicLicor();
            JSFUtil.crearMensajeINFO("Licor eliminado correctamente.");
        } catch (Exception e) {
            JSFUtil.crearMensajeERROR(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public String actionCargarTiposLicor() {
        listaTiposLicores = mLicoreria.findAllLicTiposLicor();
        return "tiposLicor?faces-redirect=true";
    }

    public void actionListenerInsertarTipoLicor() {
        try {
            mLicoreria.insertaLicTiposLicor(nuevoTipoLicor);
            listaTiposLicores = mLicoreria.findAllLicTiposLicor();
            JSFUtil.crearMensajeINFO("Tipo de licor creado.");
            nuevoTipoLicor = new LicTiposLicor();
        } catch (Exception e) {
            JSFUtil.crearMensajeERROR(e.getMessage());
            e.printStackTrace();
        }
    }

    public void actionListenerCargarTipoLicor(LicTiposLicor tipoLicor) {
        edicionTipoLicor = tipoLicor;
    }

    public void actionListenerGuardarEdicionTipoLicor() {
        try {
            mLicoreria.actualizarLicTiposLicor(edicionTipoLicor);
            listaTiposLicores = mLicoreria.findAllLicTiposLicor();
            JSFUtil.crearMensajeINFO("Tipo de licor editado.");
        } catch (Exception e) {
            JSFUtil.crearMensajeERROR(e.getMessage());
            e.printStackTrace();
        }
    }

    public void actionListenerEliminarTipoLicor(int idTipoLicor) {
        try {
            mLicoreria.eliminarLicTiposLicor(idTipoLicor);
            listaTiposLicores = mLicoreria.findAllLicTiposLicor();
            JSFUtil.crearMensajeINFO("Tipo de licor eliminado.");
        } catch (Exception e) {
            JSFUtil.crearMensajeERROR(e.getMessage());
            e.printStackTrace();
        }
    }

    // Getter y Setter para las propiedades
    public List<LicLicor> getListaLicores() {
        return listaLicores;
    }

    public void setListaLicores(List<LicLicor> listaLicores) {
        this.listaLicores = listaLicores;
    }

    public List<LicTiposLicor> getListaTiposLicores() {
        return listaTiposLicores;
    }

    public void setListaTiposLicores(List<LicTiposLicor> listaTiposLicores) {
        this.listaTiposLicores = listaTiposLicores;
    }

    public LicLicor getNuevoLicor() {
        return nuevoLicor;
    }

    public void setNuevoLicor(LicLicor nuevoLicor) {
        this.nuevoLicor = nuevoLicor;
    }

    public LicLicor getEdicionLicor() {
        return edicionLicor;
    }

    public void setEdicionLicor(LicLicor edicionLicor) {
        this.edicionLicor = edicionLicor;
    }

    public LicLicor getLicorSeleccionado() {
        return licorSeleccionado;
    }

    public void setLicorSeleccionado(LicLicor licorSeleccionado) {
        this.licorSeleccionado = licorSeleccionado;
    }
    
    public LicTiposLicor getNuevoTipoLicor() {
        return nuevoTipoLicor;
    }

    public void setNuevoTipoLicor(LicTiposLicor nuevoTipoLicor) {
        this.nuevoTipoLicor = nuevoTipoLicor;
    }

    public LicTiposLicor getEdicionTipoLicor() {
        return edicionTipoLicor;
    }

    public void setEdicionTipoLicor(LicTiposLicor edicionTipoLicor) {
        this.edicionTipoLicor = edicionTipoLicor;
    }

    public LicTiposLicor getTipoLicorSeleccionado() {
        return tipoLicorSeleccionado;
    }

    public void setTipoLicorSeleccionado(LicTiposLicor tipoLicorSeleccionado) {
        this.tipoLicorSeleccionado = tipoLicorSeleccionado;
    }
	
	
}
