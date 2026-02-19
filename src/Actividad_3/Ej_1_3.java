package Actividad_3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ej_1_3 {

}

class Ubicacion{
    int id;
    String descripcion;
    Map<String,Integer> exits;

    public Ubicacion(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        exits = new HashMap<>();
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Map<String, Integer> getExits() {
        return exits;
    }

    public void addExit(String direccion, int idUbicacion){
        exits.put(direccion, idUbicacion);
    }
}

class Main{
    private Map<Integer, Ubicacion> ubicaciones;

    public Main() {

        ubicaciones = new HashMap<>();


        // UBICACIONES
        ubicaciones.put(0, new Ubicacion(0, "Estás sentado en la clase de programación"));
        ubicaciones.put(1, new Ubicacion(1,"Estás en la cima de una montaña"));
        ubicaciones.put(2, new Ubicacion(2, "Estás bañándote en la playa"));
        ubicaciones.put(3, new Ubicacion(3,"Estás dentro de un edificio muy alto"));
        ubicaciones.put(4, new Ubicacion(4,"Estás de pie en un puente"));
        ubicaciones.put(5, new Ubicacion(5,"Estás en un bosque"));

        // MONTAÑA(1)
        ubicaciones.get(1).addExit("N", 5); // Bosque
        ubicaciones.get(1).addExit("S", 4); // Puente
        ubicaciones.get(1).addExit("E", 3); // Edificio
        ubicaciones.get(1).addExit("O", 2); // Playa
        ubicaciones.get(1).addExit("Q", 0); // SALIDA

        // PLAYA(2)
        ubicaciones.get(2).addExit("N", 5); // Bosque
        ubicaciones.get(2).addExit("Q", 0); // SALIDA

        // EDIFICIO(3)
        ubicaciones.get(3).addExit("O", 1); // Montaña
        ubicaciones.get(3).addExit("Q", 0); // SALIDA

        // PUENTE(4)
        ubicaciones.get(4).addExit("N", 1); // Montaña
        ubicaciones.get(4).addExit("O", 2); // Playa
        ubicaciones.get(4).addExit("Q", 0); // SALIDA

        // BOSQUE(5)
        ubicaciones.get(5).addExit("S", 1); // Montaña
        ubicaciones.get(5).addExit("O", 2); // Playa
        ubicaciones.get(5).addExit("Q", 0); // SALIDA
    }

    public static void main(String[] args) {
        boolean salir = true;

        Scanner sc = new Scanner(System.in);

        int ubicacionActual = 1;

        while (salir) {
            Ubicacion ubicacion = new Main().ubicaciones.get(ubicacionActual);

            if (ubicacion == null) {
                System.out.println("No puedes ir a ese lugar");
            }

            System.out.println("\n" + ubicacion.getDescripcion());
            System.out.print("Tus salidas válidas son: ");

            for (String direccion : ubicacion.getExits().keySet()) {
                System.out.print(direccion + " ");
            }


            System.out.println("\n\nINPUT: ");
            String direcciones = sc.nextLine().toUpperCase();

            if (direcciones.equals("Q")) {
                break;
            }

            if (ubicacion.getExits().containsKey(direcciones)) {
                int siguienteUbicacion = ubicacion.getExits().get(direcciones);

                if (new Main().ubicaciones.containsKey(siguienteUbicacion)) {
                    ubicacionActual = siguienteUbicacion;
                } else {
                    System.out.println("No puedes ir a ese lugar.");
                }

            } else {
                System.out.println("No puede ir en esa dirección");
            }
        }

        System.out.println("Juego terminado.");
        sc.close();
    }
}