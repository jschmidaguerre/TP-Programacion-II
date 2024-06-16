package impl;

import api.ConjuntoTDA;
import api.GrafoTDA;
import api.MapaTDA;
import api.PilaTDA;
import impl.PilaLD;

public class MapaImpl implements MapaTDA {
    private GrafoTDA grafo;
    private Provincia[] provincias;
    private int provinciaIndex;

    @Override
    public void InicializarMapa() {
        grafo = new GrafoMA();
        grafo.InicializarGrafo();
        provincias = new Provincia[100];
        provinciaIndex = 0;
    }

    @Override
    public void CargarProvincia(String provincia) {
        Provincia p = new Provincia();
        p.InicializarProvincia();
        p.nombre = provincia;
        provincias[provinciaIndex++] = p;
    }

    @Override
    public void CargarCiudad(String provincia, String ciudad) {
        for (int i = 0; i < provinciaIndex; i++) {
            if (provincias[i].nombre.equals(provincia)) {
                int ciudadHash = ciudad.hashCode();
                provincias[i].AgregarCiudad(ciudadHash, ciudad);
                grafo.AgregarVertice(ciudadHash);
                break;
            }
        }
    }

    @Override
    public void EliminarCiudad(String ciudad) {
        int ciudadHash = ciudad.hashCode();
        for (int i = 0; i < provinciaIndex; i++) {
            if (provincias[i].ciudades.Pertenece(ciudadHash)) {
                provincias[i].ciudades.Sacar(ciudadHash);
                grafo.EliminarVertice(ciudadHash);
                break;
            }
        }
    }

    @Override
    public void CargarRuta(String origen, String destino, int distancia) {
        grafo.AgregarArista(origen.hashCode(), destino.hashCode(), distancia);
    }

    @Override
    public void ListarProvincias() {
        for (int i = 0; i < provinciaIndex; i++) {
            System.out.println(provincias[i].nombre);
        }
    }

    @Override
    public void ListarCiudadesPorProvincia(String provincia) {
        for (int i = 0; i < provinciaIndex; i++) {
            if (provincias[i].nombre.equals(provincia)) {
                ConjuntoTDA ciudades = provincias[i].ciudades;
                while (!ciudades.ConjuntoVacio()) {
                    int ciudad = ciudades.Elegir();
                    System.out.println("Ciudad: " + provincias[i].getCiudadNombre(ciudad));
                    ciudades.Sacar(ciudad);
                }
                break;
            }
        }
    }

    @Override
    public void ListarCiudadesVecinas(String ciudad) {
        ConjuntoTDA vecinas = grafo.VerticesAdyacentes(ciudad.hashCode());
        while (!vecinas.ConjuntoVacio()) {
            int ciudadVecina = vecinas.Elegir();
            System.out.println("Ciudad vecina: " + getCiudadNombre(ciudadVecina));
            vecinas.Sacar(ciudadVecina);
        }
    }

    @Override
    public void ListarCiudadesPuente(String ciudadA, String ciudadB) {
        ConjuntoTDA adyacentesA = grafo.VerticesAdyacentes(ciudadA.hashCode());
        while (!adyacentesA.ConjuntoVacio()) {
            int puente = adyacentesA.Elegir();
            if (grafo.ExistenArista(puente, ciudadB.hashCode())) {
                System.out.println("Ciudad puente: " + getCiudadNombre(puente));
            }
            adyacentesA.Sacar(puente);
        }
    }

    @Override
    public void ListarCiudadesPredecesoras(String ciudad) {
        ConjuntoTDA predecesoras = grafo.VerticesPredecesores(ciudad.hashCode());
        while (!predecesoras.ConjuntoVacio()) {
            int predecesora = predecesoras.Elegir();
            System.out.println("Ciudad predecesora: " + getCiudadNombre(predecesora));
            predecesoras.Sacar(predecesora);
        }
    }

    @Override
    public void ListarCiudadesExtremo() {
        ConjuntoTDA vertices = grafo.Vertices();
        while (!vertices.ConjuntoVacio()) {
            int vertice = vertices.Elegir();
            ConjuntoTDA adyacentes = grafo.VerticesAdyacentes(vertice);
            if (adyacentes.ConjuntoVacio()) {
                System.out.println("Ciudad extremo: " + getCiudadNombre(vertice));
            }
            vertices.Sacar(vertice);
        }
    }

    @Override
    public void ListarCiudadesFuertementeConectadas() {
        ConjuntoTDA vertices = grafo.Vertices();
        while (!vertices.ConjuntoVacio()) {
            int vertice = vertices.Elegir();
            ConjuntoTDA adyacentes = grafo.VerticesAdyacentes(vertice);
            while (!adyacentes.ConjuntoVacio()) {
                int adyacente = adyacentes.Elegir();
                if (grafo.ExistenArista(adyacente, vertice)) {
                    System.out.println("Ciudades fuertemente conectadas: " + getCiudadNombre(vertice) + " y " + getCiudadNombre(adyacente));
                }
                adyacentes.Sacar(adyacente);
            }
            vertices.Sacar(vertice);
        }
    }

    @Override
    public void CalcularCamino(String ciudadA, String ciudadB) {
        PilaTDA camino = new PilaLD();
        camino.InicializarPila();
        int distancia = grafo.Camino(ciudadA.hashCode(), ciudadB.hashCode(), camino);
        System.out.println("Distancia total: " + distancia + " km");
        while (!camino.PilaVacia()) {
            System.out.println("Ciudad: " + getCiudadNombre(camino.Tope()));
            camino.Desapilar();
        }
    }

    private String getCiudadNombre(int ciudadHash) {
        for (int i = 0; i < provinciaIndex; i++) {
            String nombre = provincias[i].getCiudadNombre(ciudadHash);
            if (nombre != null) {
                return nombre;
            }
        }
        return null;
    }

    @Override
    public void CargarDatosDePrueba() {
        CargarProvincia("Buenos Aires");
        CargarProvincia("Córdoba");
        CargarProvincia("Salta");
        CargarProvincia("Chubut");

        CargarCiudad("Buenos Aires", "La Plata");
        CargarCiudad("Buenos Aires", "Mar del Plata");
        CargarCiudad("Buenos Aires", "CABA");
        CargarCiudad("Buenos Aires", "Tandil");

        CargarCiudad("Córdoba", "Ciudad de Córdoba");
        CargarCiudad("Córdoba", "Río Cuarto");
        CargarCiudad("Córdoba", "Villa Carlos Paz");

        CargarCiudad("Salta", "Cafayate");

        CargarCiudad("Chubut", "Rawson");
        CargarCiudad("Chubut", "Trelew");
        CargarCiudad("Chubut", "Puerto Madryn");

        CargarRuta("CABA", "Mar del Plata", 400);
        CargarRuta("CABA", "La Plata", 60);
        CargarRuta("CABA", "Tandil", 350);
        CargarRuta("CABA", "Ciudad de Córdoba", 1300);
        CargarRuta("Mar del Plata", "CABA", 500);
        CargarRuta("Mar del Plata", "Ciudad de Córdoba", 1800);
        CargarRuta("La Plata", "Ciudad de Córdoba", 1500);
        CargarRuta("La Plata", "Rawson", 2700);
        CargarRuta("Río Cuarto", "Ciudad de Córdoba", 200);
    }
}
