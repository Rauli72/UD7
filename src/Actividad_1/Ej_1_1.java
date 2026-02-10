package Actividad_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Ej_1_1 {
    public static void main(String[] args) {
        /*
        // TESTER
        // Contactos añadidos
        Contacto c1 = Contacto.crearContacto("Pepe", "31415926");
        Contacto c2 = Contacto.crearContacto("Alicia", "16180339");
        Contacto c3 = Contacto.crearContacto("Tomás", "11235813");
        Contacto c4 = Contacto.crearContacto("Jessica", "23571113");

        // Contactos NO añadidos
        Contacto c5 = Contacto.crearContacto("Óscar", "414141411");

        tm.anadirNuevoContacto(c1);
        tm.anadirNuevoContacto(c2);
        tm.anadirNuevoContacto(c3);
        tm.anadirNuevoContacto(c4);
        */

        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        TelefonoMovil tm = new TelefonoMovil("741455157");
        mostrarMenu();

        while (!salir) {
            System.out.print("\nElige una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 0:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;

                case 1:
                    tm.printContactos();
                    break;

                case 2:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();

                    Contacto nuevo = Contacto.crearContacto(nombre, telefono);

                    if (tm.anadirNuevoContacto(nuevo)) {
                        System.out.println("Contacto añadido correctamente.");
                    } else {
                        System.out.println("El contacto ya existe.");
                    }
                    break;

                case 3:
                    System.out.print("Nombre del contacto a actualizar: ");
                    String nombreViejo = sc.nextLine();
                    Contacto viejo = tm.queryContacto(nombreViejo);

                    if (viejo == null) {
                        System.out.println("El contacto no existe.");
                        break;
                    }

                    System.out.print("Nuevo nombre: ");
                    String nombreNuevo = sc.nextLine();
                    System.out.print("Nuevo teléfono: ");
                    String telefonoNuevo = sc.nextLine();

                    Contacto actualizado = Contacto.crearContacto(nombreNuevo, telefonoNuevo);

                    if (tm.updateContacto(actualizado, viejo)) {
                        System.out.println("Contacto actualizado correctamente.");
                    } else {
                        System.out.println("No se pudo actualizar el contacto.");
                    }
                    break;

                case 4:
                    System.out.print("Nombre del contacto a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    Contacto eliminar = tm.queryContacto(nombreEliminar);

                    if (eliminar == null) {
                        System.out.println("El contacto no existe.");
                    } else {
                        tm.eliminarContacto(eliminar);
                        System.out.println("Contacto eliminado correctamente.");
                    }
                    break;

                case 5:
                    System.out.print("Nombre del contacto a buscar: ");
                    String nombreBuscar = sc.nextLine();
                    Contacto encontrado = tm.queryContacto(nombreBuscar);

                    if (encontrado == null) {
                        System.out.println("Contacto no encontrado.");
                    } else {
                        System.out.println(encontrado);
                    }
                    break;

                case 6:
                    mostrarMenu();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n====== MENÚ ======");
        System.out.println("0 - Salir");
        System.out.println("1 - Imprimir contactos");
        System.out.println("2 - Añadir nuevo contacto");
        System.out.println("3 - Actualizar contacto");
        System.out.println("4 - Eliminar contacto");
        System.out.println("5 - Buscar contacto por nombre");
        System.out.println("6 - Mostrar menú\n");
    }
}


class Contacto {
    private String nombre;
    private String numeroTelefono;

    public Contacto(String nombre, String numeroTelefono) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public static Contacto crearContacto(String nombre, String telefono) {
        return new Contacto(nombre, telefono);
    }

    @Override
    public String toString() {
        return getNombre() + " -> " + getNumeroTelefono();
    }
}


class TelefonoMovil{
    private String miNumero;
    public ArrayList<Contacto> misContactos;

    public TelefonoMovil(String miNumero) {
        this.miNumero = miNumero;
        this.misContactos = new ArrayList<>();
    }

    public boolean anadirNuevoContacto(Contacto nuevoContacto) {
        for (Contacto c : misContactos) {
            if (c.getNombre().equals(nuevoContacto.getNombre()) ||
                    c.getNumeroTelefono().equals(nuevoContacto.getNumeroTelefono())) {
                return false;
            }
        }
        misContactos.add(nuevoContacto);
        return true;
    }


    public boolean updateContacto(Contacto nuevoContacto, Contacto viejoContacto) {
        int indice = misContactos.indexOf(viejoContacto);
        if (indice < 0) {
            return false;
        }

        for (Contacto c : misContactos) {
            if (c != viejoContacto && (c.getNombre().equals(nuevoContacto.getNombre()) ||
                    c.getNumeroTelefono().equals(nuevoContacto.getNumeroTelefono()))) {

                return false;
            }
        }

        misContactos.set(indice, nuevoContacto);
        return true;
    }



    public boolean eliminarContacto(Contacto contacto) {
        if (encontrarContacto(contacto) >= 0) {
            this.misContactos.remove(contacto);
            return true;

        } else  {
            return false;
        }
    }

    private int encontrarContacto(Contacto nuevoContacto) {
        return this.misContactos.indexOf(nuevoContacto);
    }

    private int existeContacto(String nombre) {
        for (int i = 0; i < this.misContactos.size(); i++) {
            if (this.misContactos.get(i).getNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public Contacto queryContacto(String contacto) {
        int posicion = existeContacto(contacto);

        if (posicion == -1) {
            return null;
        } else  {

            return this.misContactos.get(posicion);
        }
    }

    public void printContactos() {
        System.out.println("\nLista de contactos:");
        for (int i = 0; i < this.misContactos.size(); i++) {
            System.out.println((i + 1) + ". " + this.misContactos.get(i));
        }
    }
}