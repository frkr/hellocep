package com.github.eepiloto.view;

import com.github.eepiloto.database.CEP;
import com.github.eepiloto.service.CEPConsultaService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("enderecoViewConverter")
public class EnderecoViewConverter implements Converter {

    @Inject
    CEPConsultaService cepSrv;

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return cepSrv.getCEP(value);
            } catch(Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Consulta CEP", e.getMessage()));
            }
        }
        else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((CEP) object).getCep());
        }
        else {
            return null;
        }
    }

}
