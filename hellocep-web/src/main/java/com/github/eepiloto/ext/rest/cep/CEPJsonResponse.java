package com.github.eepiloto.ext.rest.cep;

import com.github.eepiloto.database.CEP;
import com.github.eepiloto.dto.CEPDTO;

public class CEPJsonResponse {

    private CEPDTO cep;
    private String message;

    public CEPJsonResponse(CEP cep) {
        this.cep=new CEPDTO(cep);
    }

    public CEPJsonResponse(String msg) {
        this.message=msg;
    }

    public CEPDTO getCep() {
        return cep;
    }

    public void setCep(CEPDTO cep) {
        this.cep = cep;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
