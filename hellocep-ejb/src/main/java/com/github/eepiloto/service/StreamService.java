package com.github.eepiloto.service;

import javax.ejb.Stateful;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Stateful
@Named
public class StreamService implements Serializable {

    public Character firstChar(Stream stream) throws Exception {
        /**
         * Melhor performance para adição e remoção é o novo "Deque" LinkedBlockingQueue
         * Outros "Deque"s tem performance melhor quando estamos fazendo operação em apenas uma das pontas.
         * Portando, como usaremos "add" e "remove", preferi usar o Linked.
         */
        Queue<Character> lista  = new LinkedBlockingQueue<>();
        Queue<Character> unicos = new LinkedBlockingQueue<>();

        while (stream.hasNext()) {
            char c=stream.getNext();
            if (!lista.contains(c)) {
                lista.add(c);
                unicos.add(c);
            } else {
                unicos.remove(c);
            }
        }
        if (unicos.size()==0) throw new Exception("Não foi encontrado");
        return unicos.poll();
    }

    public Character firstChar(String stream) throws Exception {
        return firstChar(new Streamer(stream));
    }

    private class Streamer implements Stream {

        int pos=0;
        String stream;

        Streamer(String stream) {
            this.stream=stream;
        }

        @Override
        public boolean hasNext() {
            return this.stream.length() > this.pos;
        }

        @Override
        public char getNext() {
            return this.stream.charAt(this.pos++);
        }
    }

}
