package com.github.eepiloto.service;

import com.github.eepiloto.database.Endereco;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Serviço de Cadastro de Endereço
 */
@RunWith(CdiRunner.class)
public class EnderecoServiceTest {

    private static boolean setUpIsDone = false;

    @Inject
    EnderecoService srv;

    @Inject
    CEPConsultaService srvCEP;

    @Before
    public void setUp() throws Exception {
        if (setUpIsDone) {
            return;
        }
        // do the setup
        setUpIsDone = true;
        srv.dao.createMassaDeDados();
    }

    /**
     * Verifica se existe o ID 1 já que sequencialmente a massa de dados existe o primeiro ID
     */
    @Test
    public void testFindById() throws Exception {
        Endereco ende = srv.findById(1L);
        assertNotNull(ende);
        assertEquals(ende.getId().longValue(), 1L);
    }

    /**
     * Verifica se retorna uma lista de Enderecos
     */
    @Test
    public void testGetLista() throws Exception {
        List<Endereco> lista = srv.getLista();
        assertNotNull(lista);
        assertNotEquals(lista.size(), 0);
    }

    /**
     * Verifica se deleta o endereco de ID 2
     */
    @Test
    public void testDelete() throws Exception {
        Endereco ende = srv.findById(2L);
        assertNotNull(ende);

        srv.delete(2L);
        assertNull(srv.findById(2L));
    }

    /**
     * Faz alterações e verifica se foram gravadas
     */
    @Test
    public void testMerge() throws Exception {
        Endereco ende = srv.findById(3L);
        assertNotNull(ende);

        String bairro="TESTE";
        ende.setBairro(bairro);

        srv.merge(ende);

        ende = srv.findById(3L);
        assertNotNull(ende);
        assertTrue(ende.getBairro().equals(bairro));
    }

    /**
     * Cria um novo registro e verifica se foi salvo
     */
    @Test
    public void testSave() throws Exception {
        String bairro="TESTE";
        Endereco ende = srv.dao.newEndereco();
        ende.setBairro(bairro);

        try {
            ende.setCep(srvCEP.dao.findById("1"));
        } catch (Exception e) {
            srvCEP.dao.createMassaDeDados();
            ende.setCep(srvCEP.dao.findById("1"));
        }

        srv.save(ende);
        for (Endereco o:srv.getLista()) {
            if (o.getBairro().equals(bairro)) {
                ende=o;
            }
        }
        assertNotNull(ende.getId());
    }

    /**
     * Cria um novo registro e Verifica se pega a validacao
     */
    @Test(expected = Exception.class)
    public void testSaveValidate() throws Exception {
        Endereco ende = srv.dao.newEndereco();
        ende.setNumero(null);

        try {
            ende.setCep(srvCEP.dao.findById("1"));
        } catch (Exception e) {
            srvCEP.dao.createMassaDeDados();
            ende.setCep(srvCEP.dao.findById("1"));
        }

        srv.save(ende);
    }


}