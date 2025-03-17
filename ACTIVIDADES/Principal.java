package ACTIVIDADES;

public class Principal {
    public static void main(String[] args) {
        // Crear un contenedor para los rectangulos
        ContainerRec contenedor = new ContainerRec(6);

        // Crear y agregar rectangulos
        Rectangulo[] rectangulos = {
            new Rectangulo(new Coordenada(1.5, 0.3), new Coordenada(7.6, 2.2)),  // A
            new Rectangulo(new Coordenada(4.0, 4.2), new Coordenada(9.4, -2.5)), // B
            new Rectangulo(new Coordenada(20.5, -1.1), new Coordenada(33.3, 3.6)), // C
            new Rectangulo(new Coordenada(10.3, -5.2), new Coordenada(20.5, 8.6)), // D
            new Rectangulo(new Coordenada(4.0, 0.0), new Coordenada(9.0, 5.0)), // E
            new Rectangulo(new Coordenada(0.0, 6.0), new Coordenada(5.0, 11.0)), // F
            new Rectangulo(new Coordenada(0.1, 6.1), new Coordenada(5.1, 11.1)) // G
        };

        // Agregar los rectangulos al contenedor
        for (Rectangulo r : rectangulos) {
            contenedor.addRectangulo(r);
        }
        
        // Comparaciones entre los pares de rectangulos
        Rectangulo[][] pares = {
            {rectangulos[0], rectangulos[1]}, // A - B
            {rectangulos[2], rectangulos[3]}, // C - D
            {rectangulos[4], rectangulos[5]}  // E - F
        };

        System.out.println("\n=== VERIFICACION DE RELACION ENTRE RECTANGULOS ===");
        for (int i = 0; i < pares.length; i++) {
            Rectangulo r1 = pares[i][0];
            Rectangulo r2 = pares[i][1];
            String resultado = Verificador.verificarRelación(r1, r2);
            double area = Verificador.calcularAreaInterseccion(r1, r2);
            
            System.out.println("\n Comparacion " + (i + 1) + ":");
            System.out.println(r1);
            System.out.println(r2);
            System.out.println(resultado);
            System.out.println("Area de sobreposicion: " + area);
        }
        System.out.println(contenedor);
    }
}
