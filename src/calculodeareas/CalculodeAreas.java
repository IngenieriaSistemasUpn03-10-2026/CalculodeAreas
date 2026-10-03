/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculodeareas;import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculodeAreas {

    // 1. Abstracción y Contrato Común
    public interface FiguraGeometrica {
        double calcularArea();
        String obtenerNombre();
    }

    // 2. Implementaciones Específicas de Figuras
    public static class Cuadrado implements FiguraGeometrica {
        private final double lado;

        public Cuadrado(double lado) {
            validarPositivo(lado, "El lado");
            this.lado = lado;
        }

        @Override
        public double calcularArea() {
            return lado * lado;
        }

        @Override
        public String obtenerNombre() { return "Cuadrado"; }
    }

    public static class Rectangulo implements FiguraGeometrica {
        private final double base;
        private final double altura;

        public Rectangulo(double base, double altura) {
            validarPositivo(base, "La base");
            validarPositivo(altura, "La altura");
            this.base = base;
            this.altura = altura;
        }

        @Override
        public double calcularArea() {
            return base * altura;
        }

        @Override
        public String obtenerNombre() { return "Rectángulo"; }
    }

    public static class Triangulo implements FiguraGeometrica {
        private final double base;
        private final double altura;

        public Triangulo(double base, double altura) {
            validarPositivo(base, "La base");
            validarPositivo(altura, "La altura");
            this.base = base;
            this.altura = altura;
        }

        @Override
        public double calcularArea() {
            return (base * altura) / 2.0;
        }

        @Override
        public String obtenerNombre() { return "Triángulo"; }
    }

    public static class Circulo implements FiguraGeometrica {
        private final double radio;

        public Circulo(double radio) {
            validarPositivo(radio, "El radio");
            this.radio = radio;
        }

        @Override
        public double calcularArea() {
            return Math.PI * Math.pow(radio, 2);
        }

        @Override
        public String obtenerNombre() { return "Círculo"; }
    }

    // Validación auxiliar compartida para evitar redundancias
    private static void validarPositivo(double valor, String nombreAtributo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(nombreAtributo + " debe ser mayor que cero.");
        }
    }

    // 3. Gestor de Entradas de Usuario (Modularidad)
    private static class LectorDimensiones {
        private final Scanner scanner;

        public LectorDimensiones(Scanner scanner) {
            this.scanner = scanner;
        }

        public double leerDato(String mensaje) {
            System.out.print(mensaje);
            return scanner.nextDouble();
        }
    }

    // 4. Clase Controladora Principal
    private final Scanner scanner = new Scanner(System.in);
    private final LectorDimensiones lector = new LectorDimensiones(scanner);

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();

            if (opcion == 5) {
                printMensaje("\n¡Gracias por usar CalculodeAreas. Hasta luego!");
                break;
            }

            procesarOpcion(opcion);

        } while (true);
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n=========================================");
        System.out.println("     SISTEMA DE CÁLCULO DE ÁREAS         ");
        System.out.println("=========================================");
        System.out.println("1. Cuadrado");
        System.out.println("2. Rectángulo");
        System.out.println("3. Triángulo");
        System.out.println("4. Círculo");
        System.out.println("5. Salir");
        System.out.println("-----------------------------------------");
    }

    private int leerOpcion() {
        System.out.print("Seleccione una opción (1-5): ");
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.next(); // Limpiar token inválido
            return -1; // Opción inválida
        }
    }

    private void procesarOpcion(int opcion) {
        try {
            FiguraGeometrica figura = crearFigura(opcion);
            if (figura != null) {
                double area = figura.calcularArea();
                System.out.printf("\n[Éxito] El área del %s es: %.2f unidades cuadradas\n", 
                        figura.obtenerNombre(), area);
            } else {
                printMensaje("\n[!] Opción no válida. Elija entre 1 y 5.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\n[!] Error de validación: " + e.getMessage());
        } catch (InputMismatchException e) {
            printMensaje("\n[!] Error: Debe ingresar un valor numérico válido.");
            scanner.next(); // Limpiar buffer
        }
    }

    private FiguraGeometrica crearFigura(int opcion) {
        switch (opcion) {
            case 1:
                return new Cuadrado(lector.leerDato("Ingrese la medida del lado: "));
            case 2:
                return new Rectangulo(
                    lector.leerDato("Ingrese la base: "),
                    lector.leerDato("Ingrese la altura: ")
                );
            case 3:
                return new Triangulo(
                    lector.leerDato("Ingrese la base: "),
                    lector.leerDato("Ingrese la altura: ")
                );
            case 4:
                return new Circulo(lector.leerDato("Ingrese el radio: "));
            default:
                return null;
        }
    }

    private void printMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Punto de entrada de la aplicación
    public static void main(String[] args) {
        CalculodeAreas sistema = new CalculodeAreas();
        sistema.iniciar();
    }
}