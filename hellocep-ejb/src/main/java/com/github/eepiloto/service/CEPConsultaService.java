package com.github.eepiloto.service;

import com.github.eepiloto.dao.CEPDao;
import com.github.eepiloto.database.CEP;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Stateful
@Named
public class CEPConsultaService implements Serializable {

    @Inject
    CEPDao dao;

    public CEP getCEP(String id) throws Exception {
        return dao.findById(id);
    }

    public List<CEP> getList() throws Exception {
        return dao.getLista();
    }

    public List<CEP> getList(String id) throws Exception {
        return dao.getLista(id);
    }

}
