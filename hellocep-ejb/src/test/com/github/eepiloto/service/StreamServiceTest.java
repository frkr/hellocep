package com.github.eepiloto.service;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Servico Exemplo de Throughput
 */
@RunWith(CdiRunner.class)
public class StreamServiceTest {

    String massa = "AsAe32aAF42sssb";

    @Inject
    StreamService srv;

    /**
     * Faremos um teste de 5mil execucoes para medir tempo
     */
    @Test
    public void testThroughput() throws Exception {
        for (int i=1;i<=5000;i++) {
            List<String> exemplo = Arrays.asList(massa.split(""));
            Collections.shuffle(exemplo);
            String nova = Arrays.deepToString(exemplo.toArray());
            srv.firstChar(nova);
        }
    }

    /**
     * Na massa de dados é procurado o caracter "e" correspondente ao primeiro nao igual
     */
    @Test
    public void testFirstChar() throws Exception {
        assertEquals(srv.firstChar(massa), Character.valueOf((char) 101));
    }

}