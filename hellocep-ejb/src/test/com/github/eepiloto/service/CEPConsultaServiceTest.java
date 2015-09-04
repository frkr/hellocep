package com.github.eepiloto.service;

import com.github.eepiloto.database.CEP;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Serviço de busca de CEP
 */
@RunWith(CdiRunner.class)
public class CEPConsultaServiceTest {

    private static boolean setUpIsDone = false;

    @Inject
    CEPConsultaService srv;

    @Before
    public void setUp() throws Exception {
        if (setUpIsDone) {
            return;
        }
        // do the setup
        setUpIsDone = true;
        if (srv.getList().size()==0)
        srv.dao.createMassaDeDados();
    }

    /**
     * Testar se retorna uma lista de CEP
     */
    @Test
    public void testGetCEPList() throws Exception {
        List<CEP> lista = srv.getList();
        assertNotNull(lista);
        assertNotEquals(lista.size(), 0);
    }

    /**
     * Testar um determinado CEP, examplo "1", se irá retornar o CEP "10000000"
     */
    @Test
    public void testGetCEP() throws Exception {
        assertTrue(srv.getCEP("1").getCep().equals("10000000"));
    }

    /**
     * Testar se lança excessão com CEP inválido
     */
    @Test(expected = Exception.class)
    public void testGetCEPThrows() throws Exception {
        srv.getCEP("99999999");
    }


}