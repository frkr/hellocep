package com.github.eepiloto.database;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class CEP implements Serializable {

    @Id
    @NotNull
    @Size(max = 8,min = 8)
    @Pattern(regexp = "\\d{8,8}")
    private String cep;

    @Column
    @NotEmpty
    @Size(max = 255)
    private String rua;

    @Column
    @NotEmpty
    @Size(max = 255)
    private String cidade;

    @Column
    @Size(max = 2,min = 2)
    @NotNull
    private String estado;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
