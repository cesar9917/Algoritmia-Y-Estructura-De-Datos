package ACTIVIDADES;

import java.util.*;

public class Rectangulo {
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    // Metodos para establecer las esquinas
    public void setEsquina1(Coordenada coo) {
        this.esquina1 = new Coordenada(coo.getX(), coo.getY());
    }

    public void setEsquina2(Coordenada coo) {
        this.esquina2 = new Coordenada(coo.getX(), coo.getY());
    }

    // Metodos para obtener las esquinas
    public Coordenada getEsquina1() {
        return this.esquina1;
    }

    public Coordenada getEsquina2() {
        return this.esquina2;
    }
    
    public double calcularDistancia() {
        return esquina1.distancia(esquina2);
    }
    
    public double calcularArea() {
        double ancho = Math.abs(esquina2.getX() - esquina1.getX());
        double alto = Math.abs(esquina2.getY() - esquina1.getY());
        return ancho * alto;
    }


    // Método toString para representar el rectángulo en texto
    public String toString() {
        return "Rectangulo: Esquina 1 " + this.esquina1 + ", Esquina 2 " + this.esquina2;
    }
}
