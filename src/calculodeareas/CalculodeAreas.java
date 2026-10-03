/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculodeareas;
import java.util.Scanner;

// 1. Clase Abstracta (Superclase)
abstract class FiguraGeometrica {
    // Método abstracto que obliga a las clases hijas a implementarlo (Polimorfismo)
    public abstract double calcularArea();
}

// 2. Clases Derivadas (Herencia)
class Cuadrado extends FiguraGeometrica {
    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}

class Rectangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }
}

class Triangulo extends FiguraGeometrica {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2.0;
    }
}

class Circulo extends FiguraGeometrica {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }
}

// 3. Clase Principal solicitada que gestiona la ejecución y el menú
public class CalculodeAreas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("=========================================");
            System.out.println("     SISTEMA DE CÁLCULO DE ÁREAS         ");
            System.out.println("=========================================");
            System.out.println("1. Cuadrado");
            System.out.println("2. Rectángulo");
            System.out.println("3. Triángulo");
            System.out.println("4. Círculo");
            System.out.println("5. Salir");
            System.out.println("-----------------------------------------");
            System.out.print("Seleccione una opción (1-5): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
            } else {
                System.out.println("\n[!] Error: Debe ingresar un número válido.\n");
                scanner.next(); // Limpiar entrada incorrecta
                continue;
            }

            if (opcion == 5) {
                System.out.println("\n¡Gracias por usar CalculodeAreas. Hasta luego!");
                break;
            }

            FiguraGeometrica figura = null;

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese la medida del lado: ");
                        double lado = scanner.nextDouble();
                        figura = new Cuadrado(lado);
                        break;
                    case 2:
                        System.out.print("Ingrese la base: ");
                        double baseR = scanner.nextDouble();
                        System.out.print("Ingrese la altura: ");
                        double alturaR = scanner.nextDouble();
                        figura = new Rectangulo(baseR, alturaR);
                        break;
                    case 3:
                        System.out.print("Ingrese la base: ");
                        double baseT = scanner.nextDouble();
                        System.out.print("Ingrese la altura: ");
                        double alturaT = scanner.nextDouble();
                        figura = new Triangulo(baseT, alturaT);
                        break;
                    case 4:
                        System.out.print("Ingrese el radio: ");
                        double radio = scanner.nextDouble();
                        figura = new Circulo(radio);
                        break;
                    default:
                        System.out.println("\n[!] Opción no válida. Elija entre 1 y 5.\n");
                        continue;
                }

                // Polimorfismo: se invoca el método correspondiente sin importar la subclase exacta
                double area = figura.calcularArea();
                System.out.printf("\n---> El área calculada es: %.2f unidades cuadradas\n\n", area);

            } catch (Exception e) {
                System.out.println("\n[!] Error: Entrada de datos no válida.\n");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }

        } while (opcion != 5);

        scanner.close();
    }
}