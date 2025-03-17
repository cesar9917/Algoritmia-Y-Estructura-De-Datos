package ACTIVIDADES;

public class ContainerRec {
    private Rectangulo[] rectangulos;  
    private double[] distancias;       
    private double[] areas;          
    private int n;                   
    private int contador;  

    public ContainerRec(int n) {
        this.n = n;
        rectangulos = new Rectangulo[n];
        distancias = new double[n];
        areas = new double[n];
        contador = 0;
    }

    public boolean agregarRectangulo(Rectangulo r) {
        if (contador < n) {
            rectangulos[contador] = r;
            distancias[contador] = r.calcularDistancia(); // Llamar al metodo correcto
            areas[contador] = r.calcularArea();
            contador++;
            return true; // Agregado correctamente
        }
        return false; // No hay espacio
    }

    public void mostrarRectangulos() {
        if (contador == 0) {
            System.out.println("No hay rectangulos almacenados.");
            return;
        }
        for (int i = 0; i < contador; i++) {
            System.out.println("Rectangulo " + (i + 1) + ": " + rectangulos[i] +
                    "\n  - Distancia Euclidiana: " + distancias[i] +
                    "\n  - Area: " + areas[i] + "\n");
        }
    }
    
    public boolean addRectangulo(Rectangulo r) {
        if (contador < n) {  // Verifica si aun hay espacio en el contenedor
            rectangulos[contador] = r;  // Agregar el rectangulo en la posicion disponible
            contador++;  // Incrementar el contador de rectangulos almacenados
            return true;  // Indicar que se agrego con exito
        } else {
            System.out.println("No se puede agregar mas rectangulos. Capacidad maxima alcanzada.");
            return false;  // Indicar que no se pudo agregar
        }
    }
    
    public String toString() {
        if (contador == 0) {
            return "No hay rectangulos almacenados.";
        }

        // Construir la tabla
        StringBuilder tabla = new StringBuilder();
        tabla.append("\n LISTA DE RECTANGULOS ALMACENADOS\n");
        tabla.append("------------------------------------------------------------\n");
        tabla.append("| N  | Esquina 1          | Esquina 2          | Area   |\n");
        tabla.append("------------------------------------------------------------\n");

        for (int i = 0; i < contador; i++) {
            Rectangulo r = rectangulos[i];
            tabla.append(String.format("| %-2d | %-18s | %-18s | %-6.2f |\n",
                    (i + 1),
                    r.getEsquina1(),
                    r.getEsquina2(),
                    r.calcularArea()));
        }
        tabla.append("------------------------------------------------------------\n");
        return tabla.toString();
    }
}
