import impl.MapaImpl;

public class CasoDePrueba {
    public static void main(String[] args) {
        // Inicialización del mapa
        MapaImpl mapa = new MapaImpl();
        mapa.InicializarMapa();
        
        // Cargar datos de prueba
        mapa.CargarDatosDePrueba();
        
        // Listar provincias
        System.out.println("Provincias:");
        mapa.ListarProvincias();
        
        // Listar ciudades por provincia
        System.out.println("\nCiudades en Buenos Aires:");
        mapa.ListarCiudadesPorProvincia("Buenos Aires");
        
        // Listar ciudades vecinas
        System.out.println("\nCiudades vecinas a CABA:");
        mapa.ListarCiudadesVecinas("CABA");
        
        // Listar ciudades puente
        System.out.println("\nCiudades puente entre CABA y Mar del Plata:");
        mapa.ListarCiudadesPuente("CABA", "Mar del Plata");
        
        // Listar ciudades predecesoras
        System.out.println("\nCiudades predecesoras a Mar del Plata:");
        mapa.ListarCiudadesPredecesoras("Mar del Plata");
        
        // Listar ciudades extremo
        System.out.println("\nCiudades extremo:");
        mapa.ListarCiudadesExtremo();
        
        // Listar ciudades fuertemente conectadas
        System.out.println("\nCiudades fuertemente conectadas:");
        mapa.ListarCiudadesFuertementeConectadas();
        
        // Calcular camino
        System.out.println("\nCamino desde CABA a Río Cuarto:");
        mapa.CalcularCamino("CABA", "Río Cuarto");
    }
}
