package minimarketdemo.controller.licoreria;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import minimarketdemo.model.core.entities.LicTiposLicor;
import minimarketdemo.model.licoreria.managers.ManagerLicoreria;

import javax.inject.Inject;

@FacesConverter("licTiposLicorConverter")
@Stateless
public class LicTiposLicorConverter implements Converter {

    @Inject
    private ManagerLicoreria mLicoreria;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            Integer id = Integer.parseInt(value);
            return mLicoreria.findLicTiposLicorById(id); // Retorna el objeto LicTiposLicor por ID
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(((LicTiposLicor) value).getIdLicTiposLicores());
    }
}
