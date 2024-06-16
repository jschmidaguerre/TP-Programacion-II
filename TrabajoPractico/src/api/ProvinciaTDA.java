package api;

public interface ProvinciaTDA {
    void InicializarProvincia(String nombre); // Inicializa la provincia con un nombre
    String Nombre(); // Devuelve el nombre de la provincia
    void AgregarCiudad(String ciudad); // Agrega una ciudad a la provincia
    void EliminarCiudad(String ciudad); // Elimina una ciudad de la provincia
    ConjuntoTDA ListarCiudades(); // Lista todas las ciudades de la provincia
}
