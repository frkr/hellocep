package com.github.eepiloto.dto;

import com.github.eepiloto.database.CEP;
import com.github.eepiloto.database.Endereco;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDTO implements Serializable {

    private String id;

    @NotNull
    private CEP cep;

    @NotEmpty
    @Size(max=255)
    private String numero;

    @Size(max=255)
    private String complemento;

    @Size(max=255)
    private String bairro;

    public EnderecoDTO(){}

    public EnderecoDTO(Endereco end) {
        this.id=(end.getId()==null) ? null : end.getId().toString();
        this.cep=end.getCep();
        this.numero=end.getNumero();
        this.complemento=end.getComplemento();
        this.bairro=end.getBairro();
    }

    public static List<EnderecoDTO> getLista(List<Endereco> lista) {
        List<EnderecoDTO> convert = new ArrayList<>();
        for (Endereco e: lista) {
            convert.add(new EnderecoDTO(e));
        }
        return convert;
    }

    public Endereco getEndereco() {
        Endereco rt = new Endereco();
        try {
            rt.setId(Long.parseLong(this.id));
        } catch (Exception e) {
        }
        rt.setCep(this.cep);
        rt.setNumero(this.numero);
        rt.setComplemento(this.complemento);
        rt.setBairro(this.bairro);
        return rt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CEP getCep() {
        return cep;
    }

    public void setCep(CEP cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

}
