package com.github.eepiloto.ext.rest.cep;

import com.github.eepiloto.service.CEPConsultaService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/cep")
public class CEPConsulta {

    @Inject
    private CEPConsultaService cepSrv;


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public CEPJsonResponse exportarCep(CEPJsonRequest cep) {
        try {
            return new CEPJsonResponse(cepSrv.getCEP(cep.getCep()));
        } catch (Exception e) {
            return new CEPJsonResponse(e.getMessage());
        }
    }

}
