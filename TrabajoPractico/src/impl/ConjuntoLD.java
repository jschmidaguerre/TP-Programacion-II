package impl;

import api.ConjuntoTDA;

public class ConjuntoLD implements ConjuntoTDA {
    class Nodo {
        int info;
        Nodo sig;
    }

    Nodo c;

    public void InicializarConjunto() {
        c = null;
    }

    public boolean ConjuntoVacio() {
        return c == null;
    }

    public void Agregar(int x) {
        if (!this.Pertenece(x)) {
            Nodo nuevo = new Nodo();
            nuevo.info = x;
            nuevo.sig = c;
            c = nuevo;
        }
    }

    public int Elegir() {
        return c.info;
    }

    public void Sacar(int x) {
        if (c != null) {
            if (c.info == x) {
                c = c.sig;
            } else {
                Nodo aux = c;
                while (aux.sig != null && aux.sig.info != x) {
                    aux = aux.sig;
                }
                if (aux.sig != null) {
                    aux.sig = aux.sig.sig;
                }
            }
        }
    }

    public boolean Pertenece(int x) {
        Nodo aux = c;
        while (aux != null && aux.info != x) {
            aux = aux.sig;
        }
        return aux != null;
    }
}
