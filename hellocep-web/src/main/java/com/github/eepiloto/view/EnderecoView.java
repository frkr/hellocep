package com.github.eepiloto.view;

import com.github.eepiloto.database.CEP;
import com.github.eepiloto.database.Endereco;
import com.github.eepiloto.dto.EnderecoDTO;
import com.github.eepiloto.service.CEPConsultaService;
import com.github.eepiloto.service.EnderecoService;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class EnderecoView implements Serializable {

    @PostConstruct
    public void init() {
        try {
            lista=EnderecoDTO.getLista(endeSrv.getLista());
            enderecoSelecionado=new EnderecoDTO();
        } catch (Exception e) {
            dfView.showMessage("Endereco","Problema ao iniciar Endereco: " + e.getMessage());
        }
    }

    @ManagedProperty("#{dfView}")
    private DFView dfView;

    public void deletar(String idEndereco) {
        try {
            endeSrv.delete(Long.parseLong(idEndereco));
            init();
        } catch (Exception e) {
            dfView.showMessage("Erro Delete",e.getMessage());
        }
    }

    public void merge() {
        try {
            Endereco salvar = enderecoSelecionado.getEndereco();
            if (salvar.getId()==null) {
                endeSrv.save(salvar);
            } else {
                endeSrv.merge(salvar);
            }
            init();
            RequestContext.getCurrentInstance().execute("PF('enderecoDialog').hide();");
        } catch (Exception e) {
            dfView.showMessage("Erro Salvar",e.getMessage());
        }
    }

    @Inject
    CEPConsultaService cepSrv;

    public List<CEP> queryCEP(String query) {
        try {
            return cepSrv.getList(query);
        } catch (Exception e) {
            dfView.showMessage("Consulta CEP","Erro: " + e.getMessage());
        }
        return null;
    }

    public void novo() {
        this.enderecoSelecionado=new EnderecoDTO();
    }

    @Inject
    private EnderecoService endeSrv;

    private List<EnderecoDTO> lista;

    private EnderecoDTO enderecoSelecionado;

    public EnderecoDTO getEnderecoSelecionado() {
        return enderecoSelecionado;
    }

    public void setEnderecoSelecionado(EnderecoDTO enderecoSelecionado) {
        this.enderecoSelecionado = enderecoSelecionado;
    }

    public List<EnderecoDTO> getLista() {
        return lista;
    }

    public void setLista(List<EnderecoDTO> lista) {
        this.lista = lista;
    }

    public void setDfView(DFView dfView) {
        this.dfView = dfView;
    }

}
