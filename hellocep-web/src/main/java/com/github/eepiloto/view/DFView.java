package com.github.eepiloto.view;


import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(name="dfView")
@ViewScoped
public class DFView implements Serializable {

    public void showMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        showMessage(params.get("tittle"),params.get("msg"));
    }

    public void showMessage(String titulo, String mensagem) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

}
