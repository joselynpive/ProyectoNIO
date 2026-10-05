
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Alex Pyve
 */
public class Main {
    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);
        MetodosCRUD crud = new MetodosCRUD();
        String ruta = "alumnos.txt";
        int opcion = 0;

        while (opcion != 6) {
            System.out.println("\n1. Registrar nuevo alumno");
            System.out.println("2. Ver todos los alumnos");
            System.out.println("3. Buscar alumno por ID");
            System.out.println("4. Actualizar nombre de alumno");
            System.out.println("5. Eliminar alumno");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opcion: ");
            
            try {
                opcion = Integer.parseInt(consola.nextLine());
                
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- Registrar Alumno ---");
                        System.out.print("Ingrese ID: ");
                        String id = consola.nextLine();
                        System.out.print("Ingrese Nombre: ");
                        String nombre = consola.nextLine();
                        crud.registrarAlumno(ruta, id, nombre);
                        break;
                        
                    case 2:
                        System.out.println("\n--- Lista de Alumnos ---");
                        crud.verTodosAlumnos(ruta);
                        break;
                        
                    case 3:
                        System.out.println("\n--- Buscar Alumno ---");
                        System.out.print("Ingrese el ID a buscar: ");
                        String idBuscar = consola.nextLine();
                        crud.buscarAlumnoPorId(ruta, idBuscar);
                        break;
                        
                    case 4:
                        System.out.println("\n--- Actualizar Nombre ---");
                        System.out.print("Ingrese el ID del alumno: ");
                        String idAct = consola.nextLine();
                        System.out.print("Ingrese el NUEVO Nombre: ");
                        String nuevoNombre = consola.nextLine();
                        crud.actualizarNombre(ruta, idAct, nuevoNombre);
                        break;
                        
                    case 5:
                        System.out.println("\n--- Eliminar Alumno ---");
                        System.out.print("Ingrese el ID del alumno a eliminar: ");
                        String idDel = consola.nextLine();
                        crud.eliminarAlumno(ruta, idDel);
                        break;
                        
                    case 6:
                        System.out.println("Saliendo del sistema...");
                        break;
                        
                    default:
                        System.out.println("Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número entero valido.");
            }
        }
        consola.close();
    }
}
