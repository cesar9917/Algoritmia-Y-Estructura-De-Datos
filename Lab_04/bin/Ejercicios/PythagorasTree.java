package pitagoras;

import java.awt.*;
import javax.swing.*;

public class PythagorasTree extends JPanel {
    private int profundidad;

    public PythagorasTree(int profundidad) {
        this.profundidad = profundidad;
        setPreferredSize(new Dimension(800, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLUE);

        trazaArbol(g2d, 400, 600, 100, -90, profundidad); // Inicia en la posición (400, 600) con ángulo -90
    }

    private void trazaArbol(Graphics2D g, int x, int y, int lado, double angulo, int nivel) {
        if (nivel == 0 || lado < 2) return; // Caso base: si no hay más niveles o el lado es muy pequeño

        // Calculando la posición final de la línea
        int x2 = x + (int) (lado * Math.cos(Math.toRadians(angulo)));
        int y2 = y + (int) (lado * Math.sin(Math.toRadians(angulo)));

        // Dibujando la línea
        g.drawLine(x, y, x2, y2);

        // Calculando el tamaño de las ramas más pequeñas
        int nuevoLado = (int) (lado * Math.sqrt(2) / 2); // Tamaño ajustado para mantener la proporción de Pitágoras

        // Dibujar las dos ramas recursivas:
        // Primera rama con un ángulo de 45 grados
        trazaArbol(g, x2, y2, nuevoLado, angulo - 45, nivel - 1);
        
        // Segunda rama con un ángulo de -45 grados (opuesta a la primera)
        trazaArbol(g, x2, y2, nuevoLado, angulo + 45, nivel - 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Árbol de Pitágoras");
        PythagorasTree arbol = new PythagorasTree(10); // Puedes cambiar la profundidad aquí
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(arbol);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centra la ventana
        frame.setVisible(true);
    }
}
