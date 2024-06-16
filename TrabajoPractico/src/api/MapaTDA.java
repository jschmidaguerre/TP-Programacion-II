package api;

public interface MapaTDA {
    void InicializarMapa();
    void CargarProvincia(String provincia);
    void CargarCiudad(String provincia, String ciudad);
    void EliminarCiudad(String ciudad);
    void CargarRuta(String origen, String destino, int distancia);
    void ListarProvincias();
    void ListarCiudadesPorProvincia(String provincia);
    void ListarCiudadesVecinas(String ciudad);
    void ListarCiudadesPuente(String ciudadA, String ciudadB);
    void ListarCiudadesPredecesoras(String ciudad);
    void ListarCiudadesExtremo();
    void ListarCiudadesFuertementeConectadas();
    void CalcularCamino(String ciudadA, String ciudadB);
    void CargarDatosDePrueba();
}
