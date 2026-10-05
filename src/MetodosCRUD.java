
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Alex Pyve
 */
public class MetodosCRUD {


    // 1. Registrar nuevo alumno
    public void registrarAlumno(String ruta, String id, String nombre) {
        Path path = Paths.get(ruta);
        String linea = id + "," + nombre + "\n";
        try {
            Files.write(path, linea.getBytes(),  //linea.getBytes(), se esta haciendo una traducción: pasa el texto de "idioma humano" a "idioma de computadora" para que se pueda guardar en el disco duro.
                        StandardOpenOption.CREATE, 
                        StandardOpenOption.APPEND);
            System.out.println("¡Alumno registrado con exito!");
        } catch (IOException e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }
    
  // 2. Ver todos los alumnos
    public void verTodosAlumnos(String ruta) {
        Path path = Paths.get(ruta);
        try {
            if (!Files.exists(path) || Files.readAllLines(path).isEmpty()) {
                System.out.println("No hay alumnos registrados.");
                return;
            }
            List<String> lineas = Files.readAllLines(path);
            for (String linea : lineas) {
                String[] datos = linea.split(",");
                System.out.println("ID: " + datos[0] + " | Nombre: " + datos[1]);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }    
    
// 3. Buscar alumno por ID
    public void buscarAlumnoPorId(String ruta, String idBuscar) {
        Path path = Paths.get(ruta);
        try {
            if (!Files.exists(path)) {
                System.out.println("No hay alumnos registrados.");
                return;
            }
            List<String> lineas = Files.readAllLines(path);
            boolean encontrado = false;
            for (String linea : lineas) {
                String[] datos = linea.split(","); //sirve para dividir una cadena de texto (String) en varias partes más pequeñas
                if (datos[0].equals(idBuscar)) {
                    System.out.println("Alumno encontrado -> ID: " + datos[0] + " | Nombre: " + datos[1]);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("Alumno con ID " + idBuscar + " no encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
    }

    
// 4. Actualizar nombre de alumno
    public void actualizarNombre(String ruta, String idBuscar, String nuevoNombre) {
        Path path = Paths.get(ruta);
        try {
            if (!Files.exists(path)) {
                System.out.println("El archivo no existe.");
                return;
            }
            List<String> lineasModificadas = Files.lines(path) //sirve para almacenar y rastrear los cambios de texto realizados en un archivo o documento.
                .map(linea -> {
                    String[] datos = linea.split(",");
                    if (datos[0].equals(idBuscar)) {
                        return datos[0] + "," + nuevoNombre;
                    }
                    return linea;
                }).collect(Collectors.toList());

            Files.write(path, lineasModificadas);
            System.out.println("¡Nombre de alumno actualizado con éxito!");
        } catch (IOException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

  // 5. Eliminar alumno
    public void eliminarAlumno(String ruta, String idEliminar) {
        Path path = Paths.get(ruta);
        try {
            if (!Files.exists(path)) {
                System.out.println("El archivo no existe.");
                return;
            }
            List<String> lineasFiltradas = Files.lines(path)
                .filter(linea -> {
                    String[] datos = linea.split(",");
                    return !datos[0].equals(idEliminar);
                }).collect(Collectors.toList());

            Files.write(path, lineasFiltradas);
            System.out.println("¡Alumno eliminado con éxito!");
        } catch (IOException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }  
    
}
