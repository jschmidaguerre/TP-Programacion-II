package api;

public interface PilaTDA {

    void InicializarPila();
    // siempre que la pila este inicializada
    void Apilar(int x);
    // siempre que la pila este inicializada y no este vacia
    void Desapilar();
    // siempre que la pila este inicializada
    boolean PilaVacia();
    // siempre que la pila este inicializada y no este vacia
    int Tope();
    
}
