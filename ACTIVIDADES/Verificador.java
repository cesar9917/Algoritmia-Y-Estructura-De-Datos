package ACTIVIDADES;

public class Verificador {
    
    // Metodo para determinar la relacion entre dos rectangulos
    public static String verificarRelación(Rectangulo A, Rectangulo B) {
        // Obtener coordenadas mínimas y maximas de cada rectangulo
        double xMinA = Math.min(A.getEsquina1().getX(), A.getEsquina2().getX());
        double xMaxA = Math.max(A.getEsquina1().getX(), A.getEsquina2().getX());
        double yMinA = Math.min(A.getEsquina1().getY(), A.getEsquina2().getY());
        double yMaxA = Math.max(A.getEsquina1().getY(), A.getEsquina2().getY());

        double xMinB = Math.min(B.getEsquina1().getX(), B.getEsquina2().getX());
        double xMaxB = Math.max(B.getEsquina1().getX(), B.getEsquina2().getX());
        double yMinB = Math.min(B.getEsquina1().getY(), B.getEsquina2().getY());
        double yMaxB = Math.max(B.getEsquina1().getY(), B.getEsquina2().getY());

        // Caso 1: Se sobreponen (interseccion no vacia)
        boolean seSobreponen = (xMinA < xMaxB && xMaxA > xMinB) && (yMinA < yMaxB && yMaxA > yMinB);
        if (seSobreponen) {
            return "Caso 1: Los rectangulos se sobreponen.";
        }

        // Caso 2: Estan juntos (comparten un borde)
        boolean estanJuntos = 
            (xMaxA == xMinB || xMinA == xMaxB) && (yMaxA >= yMinB && yMinA <= yMaxB) || // Borde vertical
            (yMaxA == yMinB || yMinA == yMaxB) && (xMaxA >= xMinB && xMinA <= xMaxB);   // Borde horizontal
        if (estanJuntos) {
            return "Caso 2: Los rectangulos se juntan.";
        }

        // Caso 3: Son disjuntos (no hay interseccion ni contacto)
        return "Caso 3: Los rectangulos son disjuntos.";
    }
    
    public static double calcularAreaInterseccion(Rectangulo A, Rectangulo B) {
        double xMinA = Math.min(A.getEsquina1().getX(), A.getEsquina2().getX());
        double xMaxA = Math.max(A.getEsquina1().getX(), A.getEsquina2().getX());
        double yMinA = Math.min(A.getEsquina1().getY(), A.getEsquina2().getY());
        double yMaxA = Math.max(A.getEsquina1().getY(), A.getEsquina2().getY());

        double xMinB = Math.min(B.getEsquina1().getX(), B.getEsquina2().getX());
        double xMaxB = Math.max(B.getEsquina1().getX(), B.getEsquina2().getX());
        double yMinB = Math.min(B.getEsquina1().getY(), B.getEsquina2().getY());
        double yMaxB = Math.max(B.getEsquina1().getY(), B.getEsquina2().getY());

        // Calcular los limites de la interseccion
        double xMinInt = Math.max(xMinA, xMinB);
        double xMaxInt = Math.min(xMaxA, xMaxB);
        double yMinInt = Math.max(yMinA, yMinB);
        double yMaxInt = Math.min(yMaxA, yMaxB);

        // Si hay interseccion, calcular area
        if (xMinInt < xMaxInt && yMinInt < yMaxInt) {
            double ancho = xMaxInt - xMinInt;
            double alto = yMaxInt - yMinInt;
            return ancho * alto;
        }

        // Si no hay interseccion, el area es 0
        return 0.0;
    }
}
