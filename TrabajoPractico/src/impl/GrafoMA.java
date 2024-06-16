package impl;

import api.ConjuntoTDA;
import api.GrafoTDA;
import api.PilaTDA;
import impl.ConjuntoLD; //pa asegurar

public class GrafoMA implements GrafoTDA {
    private int[][] matrizAdyacencia;
    private int[] vertices;
    private int indice;

    @Override
    public void InicializarGrafo() {
        matrizAdyacencia = new int[100][100];
        vertices = new int[100];
        indice = 0;
    }

    @Override
    public void AgregarVertice(int v) {
        vertices[indice++] = v;
    }

    @Override
    public void EliminarVertice(int v) {
        int pos = PosicionVertice(v);
        if (pos >= 0) {
            for (int i = 0; i < indice; i++) {
                matrizAdyacencia[pos][i] = 0;
                matrizAdyacencia[i][pos] = 0;
            }
            vertices[pos] = vertices[--indice];
        }
    }

    @Override
    public ConjuntoTDA Vertices() {
        ConjuntoLD conjunto = new ConjuntoLD();
        conjunto.InicializarConjunto();
        for (int i = 0; i < indice; i++) {
            conjunto.Agregar(vertices[i]);
        }
        return conjunto;
    }

    @Override
    public void AgregarArista(int v1, int v2, int peso) {
        int pos1 = PosicionVertice(v1);
        int pos2 = PosicionVertice(v2);
        matrizAdyacencia[pos1][pos2] = peso;
    }

    @Override
    public void EliminarArista(int v1, int v2) {
        int pos1 = PosicionVertice(v1);
        int pos2 = PosicionVertice(v2);
        matrizAdyacencia[pos1][pos2] = 0;
    }

    @Override
    public boolean ExistenArista(int v1, int v2) {
        int pos1 = PosicionVertice(v1);
        int pos2 = PosicionVertice(v2);
        return matrizAdyacencia[pos1][pos2] != 0;
    }

    @Override
    public int PesoArista(int v1, int v2) {
        int pos1 = PosicionVertice(v1);
        int pos2 = PosicionVertice(v2);
        return matrizAdyacencia[pos1][pos2];
    }

    @Override
    public ConjuntoTDA VerticesAdyacentes(int v) {
        ConjuntoLD adyacentes = new ConjuntoLD();
        adyacentes.InicializarConjunto();
        int pos = PosicionVertice(v);
        for (int i = 0; i < indice; i++) {
            if (matrizAdyacencia[pos][i] != 0) {
                adyacentes.Agregar(vertices[i]);
            }
        }
        return adyacentes;
    }

    @Override
    public ConjuntoTDA VerticesPredecesores(int v) {
        ConjuntoLD predecesores = new ConjuntoLD();
        predecesores.InicializarConjunto();
        int pos = PosicionVertice(v);
        for (int i = 0; i < indice; i++) {
            if (matrizAdyacencia[i][pos] != 0) {
                predecesores.Agregar(vertices[i]);
            }
        }
        return predecesores;
    }

    @Override
    public int Camino(int origen, int destino, PilaTDA camino) {
        boolean[] visitado = new boolean[100];
        int[] distancia = new int[100];
        int[] predecesor = new int[100];

        for (int i = 0; i < 100; i++) {
            distancia[i] = Integer.MAX_VALUE;
            visitado[i] = false;
        }

        int posOrigen = PosicionVertice(origen);
        distancia[posOrigen] = 0;

        for (int i = 0; i < indice; i++) {
            int actual = Minimo(distancia, visitado);
            visitado[actual] = true;

            for (int j = 0; j < indice; j++) {
                if (!visitado[j] && matrizAdyacencia[actual][j] != 0 && distancia[actual] != Integer.MAX_VALUE && distancia[actual] + matrizAdyacencia[actual][j] < distancia[j]) {
                    distancia[j] = distancia[actual] + matrizAdyacencia[actual][j];
                    predecesor[j] = actual;
                }
            }
        }

        int posDestino = PosicionVertice(destino);
        if (distancia[posDestino] != Integer.MAX_VALUE) {
            int step = posDestino;
            while (step != posOrigen) {
                camino.Apilar(vertices[step]);
                step = predecesor[step];
            }
            camino.Apilar(origen);
        }

        return distancia[posDestino];
    }

    private int Minimo(int[] dist, boolean[] visitado) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < indice; i++) {
            if (!visitado[i] && dist[i] <= min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int PosicionVertice(int v) {
        for (int i = 0; i < indice; i++) {
            if (vertices[i] == v) {
                return i;
            }
        }
        return -1;
    }
}
