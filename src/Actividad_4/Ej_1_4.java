package Actividad_4;

import java.util.*;

public class Ej_1_4 {
}

class CuerpoCeleste {
    public enum TipoCuerpoCeleste {
        ESTRELLA, PLANETA, PLANETA_ENANO, LUNA, COMETA, ASTEROIDE
    }

    private String nombre;
    private double periodoOrbital;
    private Set<CuerpoCeleste> satelites;
    private TipoCuerpoCeleste tipoCuerpo;

    // Constructor
    public CuerpoCeleste(String nombre, double periodoOrbital, TipoCuerpoCeleste tipoCuerpo) {
        this.nombre = nombre;
        this.periodoOrbital = periodoOrbital;
        this.tipoCuerpo = tipoCuerpo;
        this.satelites = new HashSet<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getPeriodoOrbital() {
        return periodoOrbital;
    }

    public TipoCuerpoCeleste getTipoCuerpo() {
        return tipoCuerpo;
    }

    public Set<CuerpoCeleste> getSatelites() {
        return new HashSet<>(satelites);
    }

    public boolean addSatelite(CuerpoCeleste satelite) {
        if (this.satelites.contains(satelite)) {
            System.out.println("El cuerpo tiene un satelite...");
            return false;
        }

        return this.satelites.add(satelite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuerpoCeleste cuerpoCeleste = (CuerpoCeleste) o;
        return cuerpoCeleste.tipoCuerpo == this.tipoCuerpo && Objects.equals(this.nombre, cuerpoCeleste.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, tipoCuerpo);
    }

    @Override
    public String toString() {
        return nombre + ": " + tipoCuerpo + ", " + periodoOrbital;
    }
}

class Planeta extends CuerpoCeleste {
    public Planeta(String nombrePlaneta, double periodoOrbital) {
        super(nombrePlaneta, periodoOrbital, TipoCuerpoCeleste.PLANETA);
    }

    @Override
    public boolean addSatelite(CuerpoCeleste satelite) {
        if (satelite.getTipoCuerpo() == TipoCuerpoCeleste.LUNA) {
            return super.addSatelite(satelite);
        }
        return false;
    }
}

class PlanetaEnano extends CuerpoCeleste {
    public PlanetaEnano(String nomPlanetaEnano, double periodoOrbital) {
        super(nomPlanetaEnano, periodoOrbital, TipoCuerpoCeleste.PLANETA_ENANO);
    }
}

class Luna extends CuerpoCeleste {
    public Luna(String nombreLuna, double periodoOrbital) {
        super(nombreLuna, periodoOrbital, TipoCuerpoCeleste.LUNA);
    }
}

class Main {
    static Map<String, CuerpoCeleste> sistemaSolar = new HashMap<>();
    static Set<CuerpoCeleste> planetas = new HashSet<>();

    public static void main(String[] args) {
        //i y ii
        Planeta mercurio = new Planeta("Mercurio", 88);
        sistemaSolar.put("Mercurio", mercurio);
        planetas.add(mercurio);

        Planeta venus = new Planeta("Venus", 225);
        sistemaSolar.put("Venus", venus);
        planetas.add(venus);

        Planeta tierra = new Planeta("La Tierra", 365);
        sistemaSolar.put("La Tierra", tierra);
        planetas.add(tierra);

        Planeta marte = new Planeta("Marte", 687);
        sistemaSolar.put("Marte", marte);
        planetas.add(marte);

        Planeta jupiter = new Planeta("Jupiter", 4332);
        sistemaSolar.put("Jupiter", jupiter);
        planetas.add(jupiter);

        Planeta saturno = new Planeta("Saturno", 10759);
        sistemaSolar.put("Saturno", saturno);
        planetas.add(saturno);

        Planeta urano = new Planeta("Urano", 30660);
        sistemaSolar.put("Urano", urano);
        planetas.add(urano);

        Planeta neptuno = new Planeta("Neptuno", 165);
        sistemaSolar.put("Neptuno", neptuno);
        planetas.add(neptuno);

        Planeta pluton = new Planeta("Pluton", 248);
        sistemaSolar.put("Pluton", pluton);
        planetas.add(pluton);

        // iii
        Luna luna = new Luna("Luna", 27);
        sistemaSolar.put("Luna", luna);
        tierra.addSatelite(luna);

        // iv
        Luna deimos = new Luna("Deimos", 1.3);
        Luna phobos = new Luna("Phobos", 0.3);
        sistemaSolar.put("Deimos", deimos);
        sistemaSolar.put("Phobos", phobos);
        marte.addSatelite(deimos);
        marte.addSatelite(phobos);

        // v
        Luna io = new Luna("Io", 1.8);
        Luna europa =  new Luna("Europa", 3.5);
        Luna ganymede =  new Luna("Ganymede", 7.1);
        Luna calisto =  new Luna("Calisto", 16.7);
        sistemaSolar.put("Io", io);
        sistemaSolar.put("Europa", europa);
        sistemaSolar.put("Ganymede", ganymede);
        sistemaSolar.put("Calisto", calisto);
        jupiter.addSatelite(io);
        jupiter.addSatelite(europa);
        jupiter.addSatelite(ganymede);
        jupiter.addSatelite(calisto);

        // vi
        System.out.println("PLANETAS: ");
        for (CuerpoCeleste planeta : planetas) {
            System.out.println(planeta.getNombre());
        }
        System.out.println("\n");

        // vii
        System.out.println(sistemaSolar.put("Marte", marte));
        System.out.println("Y sus lunas son: " + marte.getSatelites());
        System.out.println("\n");

        // viii y ix
        Set<CuerpoCeleste> lunas = new HashSet<>();
        for (CuerpoCeleste planeta : planetas) {
            lunas.addAll(planeta.getSatelites());
        }
        System.out.println("Lunas son: " + lunas);

        // ix: Io: LUNA, 1.8, Calisto: LUNA, 16.7, Deimos: LUNA, 1.3,
        //     Luna: LUNA, 27.0, Europa: LUNA, 3.5, Ganymede: LUNA, 7.1, Phobos: LUNA, 0.3

        // x ??
        Planeta pluton2 = new Planeta("Pluton", 884);
        sistemaSolar.put("Pluton", pluton2);
        planetas.add(pluton2);

        System.out.println("\n");

        System.out.println("PLANETAS + Plutón2: ");
        for (CuerpoCeleste planeta : planetas) {
            System.out.println(planeta.toString());
        }
        System.out.println("\n");

        // Son iguales ("Pluton", PLANETA)→ HashSet no permite duplicados y, por lo tanto,
        // no se añade.

        // xi
        PlanetaEnano pluton_enano = new PlanetaEnano("Pluton", 884);
        sistemaSolar.put("Pluton", pluton_enano);
        planetas.add(pluton_enano);

        System.out.println("\n");

        System.out.println("PLANETAS + Plutón_Enano: ");
        for (CuerpoCeleste planeta : planetas) {
            System.out.println(planeta.toString());
        }
        System.out.println("\n");
        // En este caso se añade, debido a que a nivel del HashSet no es igual,
        // ahora lo que trata de añadir es ("Pluton", PLANETAENANO) y no PLANETA
        // como en el anterior, por lo tanto, no lo cuenta como valores duplicados.


        // xii Porque solamente aplica a 1 y no puedes usar el retain all y remove all??
        Set<CuerpoCeleste> copiaPlanetas = new HashSet<>(planetas);

        // Intersección
        System.out.println("\n");
        planetas.remove(tierra);
        planetas.remove(marte);

        copiaPlanetas.retainAll(planetas);
        System.out.println("Intersección: " + copiaPlanetas);

        // Diferencia
        System.out.println("\n");
        Planeta kepler442b = new Planeta("Kepler442b", 128763);
        copiaPlanetas.add(kepler442b);

        copiaPlanetas.removeAll(planetas);
        System.out.println("Diferencia: " + copiaPlanetas);
    }
}