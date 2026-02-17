package Actividad_2;

import java.util.*;

public class Ej_1_2 {
    public static void main(String[] args) {
        ArrayList<Album> albumes = new ArrayList<Album>();
        LinkedList<Cancion> playlist = new LinkedList<Cancion>();

        // Álbum 1 - Queen
        Album aNightAtTheOpera = new Album("A Night at the Opera", "Queen");
        aNightAtTheOpera.addCancion("Bohemian Rhapsody", 5.55);
        aNightAtTheOpera.addCancion("Love of My Life", 3.38);
        aNightAtTheOpera.addCancion("You're My Best Friend", 2.52);
        albumes.add(aNightAtTheOpera);


        // Álbum 2
        Album thriller = new Album("Thriller", "Michael Jackson");
        thriller.addCancion("Thriller", 5.57);
        thriller.addCancion("Billie Jean", 4.54);
        thriller.addCancion("Beat It", 4.18);
        albumes.add(thriller);


        // Playlist
        albumes.getFirst().addToPlaylist(1, playlist);
        albumes.getFirst().addToPlaylist(3, playlist);

        albumes.getLast().addToPlaylist("Thriller", playlist);
        albumes.getLast().addToPlaylist("Beat It", playlist);

        // MASTERIZACIÓN
        //Collections.sort(playlist);
        
        // INICIAR TOD0
        play(playlist);
    }

    public static void play(LinkedList<Cancion> playlist) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        boolean continuar = true; /* Se crea para que cuando le das a opciones como anterior
                                     canción, porque si no te saldría la misma que ya está
                                     reproduciéndose*/

        ListIterator<Cancion> it = playlist.listIterator();

        if (playlist.isEmpty()) {
            System.out.println("La playlist está vacía.");
            return;
        } else {
            System.out.println("Reproduciendo: " + it.next());
           mostrarMenu();
        }

        // Opciones del menú
        while (!salir) {
            try {
                System.out.print("\nElige una opción: ");
                int opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {

                    case 0:
                        salir = true;
                        System.out.println("Saliendo del programa...");
                        break;

                    case 1: // Siguiente canción
                        if (!continuar) {
                            if (it.hasNext()) {
                                it.next();
                            }
                            continuar = true;
                        }

                        if (it.hasNext()) {
                            System.out.println("Reproduciendo: " + it.next());
                        } else {
                            System.out.println("Fin de la playlist...");
                        }
                        break;

                    case 2: // Anterior canción
                        if (continuar) {
                            if (it.hasPrevious()) {
                                it.previous();
                            }
                            continuar = false;
                        }

                        if (it.hasPrevious()) {
                            System.out.println("Reproduciendo: " + it.previous());
                        } else {
                            System.out.println("Inicio de la playlist...");
                        }
                        break;

                    case 3:
                        if (continuar) {
                            if (it.hasPrevious()) {
                                System.out.println("Repitiendo: " + it.previous());
                            }
                            continuar = false;
                        } else {
                            if (it.hasNext()) {
                                System.out.println("Repitiendo: " + it.previous());
                            }
                            continuar = true;
                        }
                        break;

                    case 4: // Imprimir playlist
                        printList(playlist);
                        break;

                    case 5: // Mostrar menú
                        mostrarMenu();
                        break;

                    case 6: // Eliminar canciones
                        if (playlist.isEmpty()) {
                            System.out.println("La playlist está vacía.");
                            break;
                        }

                        it.remove(); // elimina la canción actual

                        if (it.hasNext()) {
                            System.out.println("Reproduciendo: " + it.next());
                            continuar = true;

                        } else if (it.hasPrevious()) {
                            System.out.println("Reproduciendo: " + it.previous());
                            continuar = false;

                        } else {
                            System.out.println("La playlist quedó vacía.");
                            salir = true;
                        }
                        break;



                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("No has introducido un número válido.");
                sc.nextLine(); // IMPORTANTE PARA LIMPIAR EL BUFFER DEL TRY-CATCH
            }
        }
    }

    // Métod0 para imprimir playlist
    public static void printList(LinkedList<Cancion> playList) {
        ListIterator<Cancion> iterator = playList.listIterator();
        System.out.println("==== Lista de reproducción ====");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("===============================");
    }

    private static void mostrarMenu() {
        System.out.println("\n====== MENÚ ======");
        System.out.println("0 - Salir de la lista de reproducción");
        System.out.println("1 - Reproducir siguiente canción en la lista");
        System.out.println("2 - Reproducir la canción previa de la lista");
        System.out.println("3 - Repetir la canción actual");
        System.out.println("4 - Imprimir la lista de canciones en la playlist");
        System.out.println("5 - Volver a imprimir el menú");
        System.out.println("6 - Eliminar canción actual\n");
    }
}

class Cancion {
    private String titulo;
    private double duracion;

    public Cancion(String titulo, double duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo + " : " + duracion + "min";
    }
}

class Album {
    private String nombre;
    private String artista;
    ArrayList<Cancion> canciones;

    public Album(String artista, String nombre) {
        this.artista = artista;
        this.nombre = nombre;
        this.canciones = new ArrayList<>();
    }

    private Cancion findSong(String titulo) {
       for (Cancion cancion : this.canciones) {
           if (cancion.getTitulo().equalsIgnoreCase(titulo)) {
               return cancion;
           }
       }
        return null;
    }

    public boolean addCancion(String titulo, double duracion) {
        Cancion cancion = new Cancion(titulo, duracion);

        if (findSong(titulo) != null) {
            return false;
        } else  {
            this.canciones.add(cancion);
            return true;
        }
    }

    public boolean addToPlaylist(int numPista, LinkedList<Cancion> playlist) {
        int indice = numPista - 1;

        if (indice >= 0 && indice < canciones.size()) {
            playlist.add(canciones.get(indice));
            return true;
        } else {
            return false;
        }
    }

    public boolean addToPlaylist(String titulo, LinkedList<Cancion> playlist) {
        Cancion cancion = findSong(titulo);

        if (cancion != null) {
            playlist.add(cancion);
            return true;
        } else {
            return false;
        }
    }
}