package torre;

public class Hanoi {

    public static void main(String[] args) {
        // Llamada al método recursivo para resolver el problema con 3 discos
        torresHanoi(3, 1, 2, 3);
    }

    // Método recursivo para resolver las Torres de Hanoi
    public static void torresHanoi(int discos, int torre1, int torre2, int torre3) {
        // Caso base: si solo queda un disco
        if (discos == 1) {
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
        } else {
            // Paso 1: Mover los discos -1 de la torre1 a torre2 usando torre3
            torresHanoi(discos - 1, torre1, torre3, torre2);
            
            // Paso 2: Mover el disco restante de torre1 a torre3
            System.out.println("Mover disco de torre " + torre1 + " a torre " + torre3);
            
            // Paso 3: Mover los discos -1 de torre2 a torre3 usando torre1
            torresHanoi(discos - 1, torre2, torre1, torre3);
        }
    }
}
