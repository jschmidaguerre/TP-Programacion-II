package impl;

import api.PilaTDA;

public class PilaDinamica implements PilaTDA{
    Nodo primero;

    @Override
    public void InicializarPila() {
        primero = null;
    }

    @Override
    public void Apilar(int x) {
        Nodo aux = new Nodo();
        aux.info = x;
        aux.sig = primero;
        primero = aux;
    }

    @Override
    public void Desapilar() {
        primero = primero.sig;
    }

    @Override
    public boolean PilaVacia() {
        return(primero == null);
    }

    @Override
    public int Tope() {
        return(primero.info);
    }

}
