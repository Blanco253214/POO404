import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static colaFIFO cola = new colaFIFO(8);   // capacidad según tu diagrama
    private static pilaLIFO pila = new pilaLIFO(10);  // capacidad según tu diagrama

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;
        while (seguir) {
            System.out.println("\n=== DEMO ESTRUCTURAS ===");
            System.out.println("1) Probar COLA (FIFO) - Interactivo");
            System.out.println("2) Probar PILA (LIFO) - Interactivo");
            System.out.println("3) Pruebas RÁPIDAS (batch) a COLA");
            System.out.println("4) Pruebas RÁPIDAS (batch) a PILA");
            System.out.println("5) Mostrar ESTADO actual (cola y pila)");
            System.out.println("9) Limpiar estructuras (clear simulado)");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            String opcion = sc.nextLine().trim();
            switch (opcion) {
                case "1":
                    menuCola(sc);
                    break;
                case "2":
                    menuPila(sc);
                    break;
                case "3":
                    pruebasRapidasCola();
                    break;
                case "4":
                    pruebasRapidasPila();
                    break;
                case "5":
                    mostrarEstado();
                    break;
                case "9":
                    clearEstructuras();
                    System.out.println("→ Se limpiaron la cola y la pila.");
                    break;
                case "0":
                    seguir = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
        System.out.println("Fin. ¡Gracias!");
    }
    // Menú COLA (FIFO)
    private static void menuCola(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- COLA (FIFO) ---");
            System.out.println("1) Enqueue (agregar)");
            System.out.println("2) Dequeue (extraer)");
            System.out.println("3) Peek (ver frente)");
            System.out.println("4) Estado de la cola");
            System.out.println("0) Volver");
            System.out.print("Opción: ");

            String op = sc.nextLine().trim();
            switch (op) {
                case "1":
                    System.out.print("  Ingrese texto a encolar: ");
                    String valorEncolar = sc.nextLine();
                    var r1 = cola.enqueue(valorEncolar);
                    mostrarResultado(r1);
                    break;
                case "2":
                    var r2 = cola.dequeue();
                    mostrarResultado(r2);
                    break;
                case "3":
                    var r3 = cola.peek();
                    mostrarResultado(r3);
                    break;
                case "4":
                    estadoCola();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    // Menú PILA (LIFO)
    private static void menuPila(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- PILA (LIFO) ---");
            System.out.println("1) Push (apilar)");
            System.out.println("2) Pop (desapilar)");
            System.out.println("3) Peek (ver top)");
            System.out.println("4) Estado de la pila");
            System.out.println("0) Volver");
            System.out.print("Opción: ");
            String op = sc.nextLine().trim();
            switch (op) {
                case "1":
                    System.out.print("  Ingrese texto a apilar: ");
                    String valorApilar = sc.nextLine();
                    var r1 = pila.push(valorApilar);
                    mostrarResultado(r1);
                    break;
                case "2":
                    var r2 = pila.pop();
                    mostrarResultado(r2);
                    break;
                case "3":
                    var r3 = pila.peek();
                    mostrarResultado(r3);
                    break;
                case "4":
                    estadoPila();
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    // Pruebas rápidas (batch)
    private static void pruebasRapidasCola() {
        System.out.println("\n== PRUEBAS RÁPIDAS: COLA ==");
        // limpiar cola (simulado)
        while (!cola.isEmpty()) cola.dequeue();
        for (String s : new String[]{"A","B","C","D","E","F","G","H"}) {
            mostrarResultado(cola.enqueue(s));
        }
        // Overflow
        mostrarResultado(cola.enqueue("I"));
        // Peek
        mostrarResultado(cola.peek());
        // Dequeue 3
        mostrarResultado(cola.dequeue());
        mostrarResultado(cola.dequeue());
        mostrarResultado(cola.dequeue());
        // Snapshot/estado
        estadoCola();
        // Dequeue hasta vaciar
        while (!cola.isEmpty()) {
            mostrarResultado(cola.dequeue());
        }
        // Underflow
        mostrarResultado(cola.dequeue());
        estadoCola();
    }
    private static void pruebasRapidasPila() {
        System.out.println("\n== PRUEBAS RÁPIDAS: PILA ==");
        while (!pila.isEmpty()) pila.pop();
        // Push válidos hasta capacidad
        for (String s : new String[]{"1","2","3","4","5","6","7","8","9","10"}) {
            mostrarResultado(pila.push(s));
        }
        // Overflow
        mostrarResultado(pila.push("11"));
        // Peek
        mostrarResultado(pila.peek());
        // Pop 3
        mostrarResultado(pila.pop());
        mostrarResultado(pila.pop());
        mostrarResultado(pila.pop());
        // Snapshot/estado
        estadoPila();
        // Pop hasta vaciar
        while (!pila.isEmpty()) {
            mostrarResultado(pila.pop());
        }
        // Underflow
        mostrarResultado(pila.pop());
        estadoPila();
    }
    // Utilidades de impresión/estado
    private static void mostrarResultado(Object r) {
        // Tanto cola.resultados como pila.resultados implementan toString()
        System.out.println("  -> " + r);
    }
    private static void estadoCola() {
        System.out.println("COLA | size=" + cola.size() + "/" + cola.capacidad()
                + " | vacía=" + cola.isEmpty() + " | llena=" + cola.isFull());
        System.out.println("      snapshot=" + Arrays.toString(cola.toSnapshot()));
    }
    private static void estadoPila() {
        System.out.println("PILA | size=" + pila.size() + "/" + pila.capacidad()
                + " | vacía=" + pila.isEmpty() + " | llena=" + pila.isFull());
        System.out.println("      snapshot(base->top)=" + Arrays.toString(pila.toSnapshot()));
    }
    private static void mostrarEstado() {
        System.out.println();
        estadoCola();
        estadoPila();
    }
    private static void clearEstructuras() {
        while (!cola.isEmpty()) cola.dequeue();
        while (!pila.isEmpty()) pila.pop();
    }
}