package com.github.eepiloto.dao;

import com.github.eepiloto.database.CEP;
import com.github.eepiloto.database.Endereco;

import javax.ejb.Stateful;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Stateful
@Named
public class EnderecoDao implements Serializable {

    /**
     * @return Um endereco falso
     */
    public Endereco newEndereco() {
        Endereco ende = new Endereco();
        ende.setCep(newCEP());
        ende.setNumero("N/A");
        ende.setBairro("Chácara");
        return ende;
    }

    private CEP newCEP() {
        CEP cep = new CEP();
        cep.setCep(formatCEP(new Random().nextInt(20000000)+"") );
        cep.setCidade("São Paulo");
        cep.setEstado("SP");
        cep.setRua("Estrada 23");
        return cep;
    }

    private String formatCEP(String cep) {
        return String.format("%-8s", cep ).replace(' ', '0');
    }

    @Deprecated
    public void createMassaDeDados() {
        EntityManager emf = UtilDao.getFactory();
        emf.getTransaction().begin();

        for (int i=1;i<=10;i++) {
            Endereco ende = newEndereco();
            emf.persist(ende.getCep());
            emf.persist(ende);
        }

        emf.getTransaction().commit();
    }

    public Endereco findById(Long id) throws Exception {
        EntityManager emf = UtilDao.getFactory();
        return emf.find(Endereco.class, id);
    }

    public List<Endereco> getLista() throws Exception {
        EntityManager emf = UtilDao.getFactory();
        CriteriaBuilder builder = emf.getCriteriaBuilder();
        CriteriaQuery<Endereco> query = builder.createQuery(Endereco.class);
        Root<Endereco> from = query.from(Endereco.class);
        TypedQuery<Endereco> typedQuery = emf.createQuery(query.select(from));
        return typedQuery.getResultList();
    }

    public void delete(Long id) throws Exception {
        EntityManager emf = UtilDao.getFactory();
        emf.getTransaction().begin();
        emf.remove(emf.find(Endereco.class,id));
        emf.getTransaction().commit();
    }

    public void merge(Endereco et) throws Exception {
        validar(et);
        EntityManager emf = UtilDao.getFactory();
        emf.getTransaction().begin();
        emf.merge(et);
        emf.getTransaction().commit();
    }

    public void save(Endereco et) throws Exception {
        validar(et);
        EntityManager emf = UtilDao.getFactory();
        emf.getTransaction().begin();
        emf.merge(et);
        emf.getTransaction().commit();
    }

    private void validar(Endereco et) throws Exception {
        if (UtilDao.getValidator().validate(et).size()>0) {
            throw new Exception("Erro de validacao, por Favor preencha os campos obritorios");
        }
    }

}
