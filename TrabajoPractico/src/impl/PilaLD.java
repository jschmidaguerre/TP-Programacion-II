package impl;

import api.PilaTDA;

public class PilaLD implements PilaTDA {
    class Nodo {
        int info;
        Nodo sig;
    }

    Nodo c;

    public void InicializarPila() {
        c = null;
    }

    public void Apilar(int x) {
        Nodo nuevo = new Nodo();
        nuevo.info = x;
        nuevo.sig = c;
        c = nuevo;
    }

    public void Desapilar() {
        if (c != null) {
            c = c.sig;
        }
    }

    public int Tope() {
        return c.info;
    }

    public boolean PilaVacia() {
        return c == null;
    }
}
