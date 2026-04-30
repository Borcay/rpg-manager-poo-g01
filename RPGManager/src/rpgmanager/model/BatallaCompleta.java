package rpgmanager.model;

import rpgmanager.interfaces.Habilidoso;
import rpgmanager.interfaces.Sanador;
import rpgmanager.interfaces.Equipable;
import java.util.ArrayList;

public class BatallaCompleta {

    public static void main(String[] args) {

        // ── Crear personajes ──────────────────────────────────────────────────
        ArrayList<Personaje> heroes = new ArrayList<>();
        Guerrero thorin  = new Guerrero("Thorin",  3);
        Mago     gandalf = new Mago("Gandalf",     5);
        Arquero  legolas = new Arquero("Legolas",  4);
        heroes.add(thorin);
        heroes.add(gandalf);
        heroes.add(legolas);

        Personaje orco = new Guerrero("Orco", 1);

        // ── FASE 1: Equipar ───────────────────────────────────────────────────
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║       FASE 1 — EQUIPAR       ║");
        System.out.println("╚══════════════════════════════╝");
        thorin.equiparItem("Espada Legendaria");
        legolas.equiparItem("Arco Elfico");

        System.out.println("\nEstado inicial:");
        for (Personaje h : heroes) System.out.println("  " + h);
        System.out.println("  " + orco);

        // ── FASE 2: Batalla por turnos ────────────────────────────────────────
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     FASE 2 — BATALLA         ║");
        System.out.println("╚══════════════════════════════╝");

        int turno = 1;
        while (orco.estaVivo()) {
            System.out.println("\n--- Turno " + turno + " ---");

            for (Personaje h : heroes) {
                if (!orco.estaVivo()) break;

                // En turno 2 usar habilidad especial antes de atacar
                if (turno == 2 && h instanceof Habilidoso) {
                    System.out.println("[Habilidad especial]");
                    ((Habilidoso) h).usarHabilidadEspecial(orco);
                    if (!orco.estaVivo()) break;
                }

                h.atacar(orco);
            }
            turno++;
        }

        System.out.println("\nEl Orco fue derrotado en " + (turno - 1) + " turno(s).");

        // ── FASE 3: Sanacion post batalla ─────────────────────────────────────
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     FASE 3 — SANACION        ║");
        System.out.println("╚══════════════════════════════╝");

        for (Personaje h : heroes) {
            if (h instanceof Sanador) {
                // Buscar el heroe con menos vida
                Personaje masDebil = heroes.get(0);
                for (Personaje objetivo : heroes) {
                    if (objetivo.getPuntosVida() < masDebil.getPuntosVida()) {
                        masDebil = objetivo;
                    }
                }
                System.out.println(h.getNombre() + " busca al heroe mas debil: "
                        + masDebil.getNombre() + " (" + masDebil.getPuntosVida()
                        + "/" + masDebil.getPuntosVidaMax() + " HP)");
                ((Sanador) h).sanar(masDebil);
            }
        }

        // ── Estado final ──────────────────────────────────────────────────────
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      ESTADO FINAL            ║");
        System.out.println("╚══════════════════════════════╝");
        for (Personaje h : heroes) System.out.println("  " + h);
        System.out.println("  " + orco);
    }
}