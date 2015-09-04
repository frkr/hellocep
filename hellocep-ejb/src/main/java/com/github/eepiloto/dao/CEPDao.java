package com.github.eepiloto.dao;

import com.github.eepiloto.database.CEP;

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
public class CEPDao implements Serializable {

    /**
     * @return Um cep aleatorio com dados falsos
     */
    public CEP newCEP() {
        CEP cep = new CEP();
        cep.setCep(formatCEP(new Random().nextInt(20000000) + "") );
        cep.setCidade("São Paulo");
        cep.setEstado("SP");
        cep.setRua("Rua Um");
        return cep;
    }

    public String formatCEP(String cep) {
        return String.format("%-8s", cep ).replace(' ', '0');
    }

    @Deprecated
    public void createMassaDeDados() {
        // FIXME Massa de dados: Ao usar dados já criados, exclua esta linha.
        EntityManager emf = UtilDao.getFactory();
        emf.getTransaction().begin();

        CEP cep = newCEP();
        cep.setCep("10000000");
        emf.persist(cep);
        cep = newCEP();
        cep.setCep("20000000");
        emf.persist(cep);

        for (int i=1;i<=10;i++) {
            cep = newCEP();
            emf.persist(cep);
        }

        emf.getTransaction().commit();
    }

    public CEP findById(String id) throws Exception {
        try {
            Integer.parseInt(id);
        } catch (Exception e) {
            throw new Exception("CEP inválido");
        }
        try {
            List<CEP> lista = getList(formatCEP(id));
            for (int i=1;i<=id.length()-1;i++) {
                if (lista.size()>=1) {
                    break;
                }
                lista = getList(formatCEP(id.substring(0, i)));
            }
            return lista.get(0);
        } catch (Exception e) {
            throw new Exception("CEP inválido");
        }
    }

    private List<CEP> getList(String id) {
        EntityManager emf = UtilDao.getFactory();
        CriteriaBuilder builder = emf.getCriteriaBuilder();
        CriteriaQuery<CEP> query = builder.createQuery(CEP.class);
        Root<CEP> from = query.from(CEP.class);
        TypedQuery<CEP> typedQuery = emf.createQuery(
                query.select(from)
                        .where(
                                builder.equal(from.<String>get("cep"), id)
                        )
        );
        return typedQuery.getResultList();
    }

    public List<CEP> getLista() throws Exception {
        EntityManager emf = UtilDao.getFactory();
        CriteriaBuilder builder = emf.getCriteriaBuilder();
        CriteriaQuery<CEP> query = builder.createQuery(CEP.class);
        Root<CEP> from = query.from(CEP.class);
        TypedQuery<CEP> typedQuery = emf.createQuery(query.select(from));
        return typedQuery.getResultList();
    }

    public List<CEP> getLista(String id) throws Exception {
        EntityManager emf = UtilDao.getFactory();
        CriteriaBuilder builder = emf.getCriteriaBuilder();
        CriteriaQuery<CEP> query = builder.createQuery(CEP.class);
        Root<CEP> from = query.from(CEP.class);
        TypedQuery<CEP> typedQuery = emf.createQuery(
                query.select(from)
                        .where(
                                builder.like(from.<String>get("cep"), id+"%")
                        )
        );
        return typedQuery.getResultList();
    }

}
