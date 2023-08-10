/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package caso2;
import java.util.Scanner;
/**
 *
 * @author jerjo
 */
public class Caso2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Amigo[] amigos = {
            new Amigo("Joshua"),
            new Amigo("Greivin"),
            new Amigo("Guillermo"),
            new Amigo("Andres"),
            new Amigo("Tavo"),
            new Amigo("David")
        };

        mostrarMenu(scanner, amigos);
    }

    public static void mostrarMenu(Scanner scanner, Amigo[] amigos) {
        while (true) {
            System.out.println("1. Registrar movimiento");
            System.out.println("2. Mostrar deudas");
            System.out.println("3. Pagar deuda");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarMovimiento(scanner, amigos);
                    break;
                case 2:
                    mostrarTabla(amigos);
                    break;
                case 3:
                    pagarDeuda(scanner, amigos);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    public static void registrarMovimiento(Scanner scanner, Amigo[] amigos) {
        System.out.println("Amigos disponibles:");
        for (int i = 0; i < amigos.length; i++) {
            System.out.println((i + 1) + ". " + amigos[i].nombre);
        }

        System.out.print("Seleccione el número de amigo A: ");
        int indiceA = scanner.nextInt() - 1;
        System.out.print("Seleccione el número de amigo B: ");
        int indiceB = scanner.nextInt() - 1;
        System.out.print("Ingrese el monto del movimiento: ");
        double monto = scanner.nextDouble();

        Movimiento movimiento = new Movimiento(amigos[indiceA], amigos[indiceB], monto);
        amigos[indiceA].deudaTotal += monto;
        amigos[indiceB].deudaTotal -= monto;

        System.out.println("Movimiento registrado exitosamente.");
    }

    public static void mostrarTabla(Amigo[] amigos) {
        System.out.println("+------------+--------------+");
        System.out.println("|   Amigo    |   Deuda ($)  |");
        System.out.println("+------------+--------------+");
        for (Amigo amigo : amigos) {
            String deuda = amigo.deudaTotal != 0 ? String.valueOf(amigo.deudaTotal) : "0";
            System.out.printf("| %-10s | %-12s |%n", amigo.nombre, deuda);
            System.out.println("+------------+--------------+");
        }
    }

    public static void pagarDeuda(Scanner scanner, Amigo[] amigos) {
        System.out.println("Amigos disponibles:");
        for (int i = 0; i < amigos.length; i++) {
            System.out.println((i + 1) + ". " + amigos[i].nombre);
        }

        System.out.print("Seleccione el número de amigo que va a realizar el pago: ");
        int indicePagador = scanner.nextInt() - 1;

        System.out.print("¿Va a pagar una sola persona (1) o van a dividir la cuenta (2)? ");
        int opcionPago = scanner.nextInt();

        if (opcionPago == 1) {
            pagarTodo(scanner, amigos, indicePagador);
        } else if (opcionPago == 2) {
            pagarDivision(scanner, amigos, indicePagador);
        } else {
            System.out.println("Opción inválida.");
        }
    }

    public static void pagarTodo(Scanner scanner, Amigo[] amigos, int indicePagador) {
        System.out.print("Ingrese el monto total del pago: ");
        double montoTotal = scanner.nextDouble();

        amigos[indicePagador].deudaTotal -= montoTotal;
        
        if (amigos[indicePagador].deudaTotal == 0) {
            System.out.println("¡La deuda ha sido completamente pagada!");
        } else {
            System.out.println("Pago realizado exitosamente.");
        }

        mostrarDeudaIndividual(amigos, indicePagador);
    }

    public static void pagarDivision(Scanner scanner, Amigo[] amigos, int indicePagador) {
        System.out.print("¿Cuántas personas van a pagar (incluyendo al que paga)? ");
        int numPersonas = scanner.nextInt();

        double montoTotal = 0;
        for (int i = 0; i < numPersonas; i++) {
            System.out.print("Ingrese el monto que va a pagar la persona " + (i + 1) + ": ");
            double montoPago = scanner.nextDouble();
            montoTotal += montoPago;
            amigos[(indicePagador + i) % amigos.length].deudaTotal += montoPago;
        }

        amigos[indicePagador].deudaTotal -= montoTotal;
        
        if (amigos[indicePagador].deudaTotal == 0) {
            System.out.println("¡La deuda ha sido completamente pagada!");
        } else {
            System.out.println("Pago realizado exitosamente.");
        }

        mostrarDeudaIndividual(amigos, indicePagador);
    }

    public static void mostrarDeudaIndividual(Amigo[] amigos, int indice) {
        System.out.println("Deuda individual:");
        System.out.println("+------------+--------------+");
        System.out.println("|   Amigo    |   Deuda ($)  |");
        System.out.println("+------------+--------------+");
        String deuda = amigos[indice].deudaTotal != 0 ? String.valueOf(amigos[indice].deudaTotal) : "0";
        System.out.printf("| %-10s | %-12s |%n", amigos[indice].nombre, deuda);
        System.out.println("+------------+--------------+");
    }
}