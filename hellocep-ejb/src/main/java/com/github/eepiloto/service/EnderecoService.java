package com.github.eepiloto.service;

import com.github.eepiloto.dao.EnderecoDao;
import com.github.eepiloto.database.Endereco;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Stateful
@Named
public class EnderecoService implements Serializable {

    @Inject
    EnderecoDao dao;

    public Endereco findById(Long id) throws Exception {
        return dao.findById(id);
    }

    public List<Endereco> getLista() throws Exception {
        return dao.getLista();
    }

    public void delete(Long id) throws Exception {
        dao.delete(id);
    }

    public void merge(Endereco et) throws Exception {
        dao.merge(et);
    }

    public void save(Endereco et) throws Exception {
        dao.save(et);
    }

}
