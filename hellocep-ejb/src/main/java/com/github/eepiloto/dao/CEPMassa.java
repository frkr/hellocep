package com.github.eepiloto.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
@Deprecated
public class CEPMassa {

    @Inject
    CEPDao dao;

    @PostConstruct
    public void init() {
        try {
            dao.createMassaDeDados();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
