package torre;

public class TorresDeHanoi {

    // Método recursivo para resolver el problema de las Torres de Hanoi
    public static void hanoi(int n, String origen, String destino, String auxiliar) {
        // Caso base: si hay solo un disco, moverlo directamente de origen a destino
        if (n == 1) {
            System.out.println("Mover disco 1 de " + origen + " a " + destino);
        } else {
            // Paso 1: Mover los n-1 discos de origen a auxiliar
            hanoi(n - 1, origen, auxiliar, destino);
            
            // Paso 2: Mover el disco n de origen a destino
            System.out.println("Mover disco " + n + " de " + origen + " a " + destino);
            
            // Paso 3: Mover los n-1 discos de auxiliar a destino
            hanoi(n - 1, auxiliar, destino, origen);
        }
    }

    // Método principal para ejecutar el algoritmo
    public static void main(String[] args) {
        int numDiscos = 2;  // Número de discos
        String origen = "A";  // Varilla origen
        String destino = "C";  // Varilla destino
        String auxiliar = "B";  // Varilla auxiliar

        // Llamar al método recursivo para resolver el problema
        hanoi(numDiscos, origen, destino, auxiliar);
    }
}
