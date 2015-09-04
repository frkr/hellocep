package com.github.eepiloto.view;

import com.github.eepiloto.service.StreamService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class StreamView implements Serializable {

    private String ultimoChar="";
    private String todosOsCaracteres="aAbBABac";

    public String getTodosOsCaracteres() {
        return todosOsCaracteres;
    }

    public void setTodosOsCaracteres(String todosOsCaracteres) {
        this.todosOsCaracteres = todosOsCaracteres;
    }

    public String getUltimoChar() {
        return ultimoChar;
    }

    public void setUltimoChar(String ultimoChar) {
        this.ultimoChar = ultimoChar;
    }


    @Inject
    StreamService st;

    @Inject
    DFView dfView;

    public void firstChar() {
        ultimoChar="";
        try {
            ultimoChar = st.firstChar(todosOsCaracteres)+"";
        } catch (Exception e) {
            dfView.showMessage("Stream Error",e.getMessage());
        }
    }

}
