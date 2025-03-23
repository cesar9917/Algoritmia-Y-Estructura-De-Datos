package Ejercicios_8_10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OperacionesMatInteger opInt = new OperacionesMatInteger();
        OperacionesMatDouble opDouble = new OperacionesMatDouble();

        while (true) {
            try {
                // mostrar menu de opciones
                System.out.println("\n Menu de operaciones clases genericas:");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Producto");
                System.out.println("4. Division");
                System.out.println("5. Potencia");
                System.out.println("6. Raiz cuadrada");
                System.out.println("7. Raiz cubica");
                System.out.println("8. Salir");
                System.out.print("Seleccione una opcion: ");
                
                int opcion = scanner.nextInt(); // leer opcion ingresada
                if (opcion == 8) { // si la opcion es salir, se termina el programa
                    System.out.println("Saliste del programa");
                    break;
                }

                // pedir tipo de dato (integer o double)
                System.out.print("¿Que tipo de numero usara? (1 = integer, 2 = double): ");
                int tipo = scanner.nextInt();

                // validar si el tipo ingresado es correcto
                if (tipo != 1 && tipo != 2) {
                    System.out.println("Tipo invalido. intente de nuevo.");
                    continue;
                }

                // solicitar valores numericos
                System.out.print("Ingrese el primer numero: ");
                double num1 = scanner.nextDouble();
                double num2 = 0;
                
                // si la operacion no es raiz cuadrada o cubica, se solicita un segundo numero
                if (opcion != 6 && opcion != 7) {  
                    System.out.print("Ingrese el segundo numero: ");
                    num2 = scanner.nextDouble();
                }

                double resultado = 0; // variable para almacenar el resultado

                // operaciones para el tipo integer
                if (tipo == 1) {
                    int n1 = (int) num1;
                    int n2 = (int) num2;
                    resultado = switch (opcion) {
                        case 1 -> opInt.suma(n1, n2);
                        case 2 -> opInt.resta(n1, n2);
                        case 3 -> opInt.producto(n1, n2);
                        case 4 -> opInt.division(n1, n2);
                        case 5 -> opInt.potencia(n1, n2);
                        case 6 -> opInt.raizCuadrada(n1);
                        case 7 -> opInt.raizCubica(n1);
                        default -> throw new IllegalStateException("Opcion invalida.");
                    };
                } 
                // operaciones para el tipo double
                else {
                    double n1 = num1;
                    double n2 = num2;
                    resultado = switch (opcion) {
                        case 1 -> opDouble.suma(n1, n2);
                        case 2 -> opDouble.resta(n1, n2);
                        case 3 -> opDouble.producto(n1, n2);
                        case 4 -> opDouble.division(n1, n2);
                        case 5 -> opDouble.potencia(n1, n2);
                        case 6 -> opDouble.raizCuadrada(n1);
                        case 7 -> opDouble.raizCubica(n1);
                        default -> throw new IllegalStateException("Opcion invalida.");
                    };
                }

                // mostrar el resultado de la operacion
                System.out.println("Resultado: " + resultado);

            } catch (InputMismatchException e) {
                // manejar error de entrada no valida
                System.out.println("Error: entrada no valida.");
                scanner.next(); // limpiar buffer del scanner
            } catch (ArithmeticException e) {
                // manejar error aritmetico como division entre cero
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close(); // cerrar scanner al finalizar
    }
}

