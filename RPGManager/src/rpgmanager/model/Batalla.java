package rpgmanager.model;

import java.util.ArrayList;

public class Batalla {

    public static void main(String[] args) {

        // ── Crear personajes ──────────────────────────────────────────────────
        ArrayList<Personaje> heroes = new ArrayList<>();
        heroes.add(new Guerrero("Thorin",  3));
        heroes.add(new Mago("Gandalf",     5));
        heroes.add(new Arquero("Legolas",  4));

        Personaje orco = new Guerrero("Orco", 1);

        // ── Mostrar estado inicial ────────────────────────────────────────────
        System.out.println("=== INICIO DE BATALLA ===");
        for (Personaje h : heroes) System.out.println(h);
        System.out.println(orco);
        System.out.println("=========================\n");

        // ── Bucle de batalla ─────────────────────────────────────────────────
        int turno = 1;
        while (orco.estaVivo()) {
            System.out.println("--- Turno " + turno + " ---");
            for (Personaje h : heroes) {
                if (orco.estaVivo()) {
                    h.atacar(orco);
                } else {
                    break;
                }
            }
            turno++;
        }

        // ── Resultado final ───────────────────────────────────────────────────
        System.out.println("\n=== FIN DE BATALLA ===");
        System.out.println("El Orco fue derrotado en " + (turno - 1) + " turno(s).");
        System.out.println("\nEstado final de los heroes:");
        for (Personaje h : heroes) System.out.println(h);
        System.out.println(orco);
    }
}