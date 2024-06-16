package api;

public interface GrafoTDA {
    void InicializarGrafo();
    //siempre que el grafo este inicializado y no exista el nodo
    void AgregarVertice(int v);
    //siempre que el grafo este inicializado y exista el nodo
    void EliminarVertice(int v);
    //siempre que el grafo este inicializado
    ConjuntoTDA Vertices();
    /* siempre que el grafo este inicializado , no exista la
     arista , pero existan ambos nodos */
    void AgregarArista(int v1, int v2, int peso);
    //siempre que el grafo este inicializado y exista la arista
    void EliminarArista(int v1, int v2);
    //siempre que el grafo este inicializado y exista los nodos
    boolean ExistenArista(int v1, int v2);
    //siempre que el grafo este inicializado y exista la arista
    int PesoArista(int v1, int v2);

    ConjuntoTDA VerticesAdyacentes(int v); //NO incluidos en la guia teorica
    ConjuntoTDA VerticesPredecesores(int v); // Nuevo método
    // Definición del método Camino
    int Camino(int origen, int destino, PilaTDA camino);
}
