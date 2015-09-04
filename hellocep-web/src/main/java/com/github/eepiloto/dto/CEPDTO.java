package com.github.eepiloto.dto;

import com.github.eepiloto.database.CEP;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CEPDTO implements Serializable {

    @NotNull
    @Size(max = 8,min = 8)
    @Pattern(regexp = "\\d{8,8}")
    private String cep;

    @NotEmpty
    @Size(max = 255)
    private String rua;

    @NotEmpty
    @Size(max = 255)
    private String cidade;

    @Size(max = 2,min = 2)
    @NotNull
    private String estado;

    public CEPDTO(){}

    public CEPDTO(CEP cep) {
        this.cep=cep.getCep();
        this.rua=cep.getRua();
        this.cidade=cep.getCidade();
        this.estado=cep.getEstado();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
