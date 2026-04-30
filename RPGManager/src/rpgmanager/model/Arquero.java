package rpgmanager.model;

import rpgmanager.interfaces.Equipable;

public class Arquero extends Personaje implements Equipable {

    private int    flechas;
    private int    alcance;
    private String itemEquipado = "Arco basico";

    public Arquero(String nombre, int nivel) {
        super(nombre, nivel, 75 + nivel * 7);
        this.flechas = 10 + nivel * 2;
        this.alcance = 30;
    }

    // ── Personaje abstractos ──────────────────────────────────────────────────

    @Override
    public void atacar(Personaje objetivo) {
        if (flechas > 0) {
            int dano = 12 + nivel * 4;
            if (!itemEquipado.equals("Arco basico")) dano += 5;
            System.out.println(nombre + " dispara una flecha a "
                    + objetivo.getNombre() + " causando " + dano + " de daño!"
                    + (!itemEquipado.equals("Arco basico") ? " [+" + 5 + " bonus]" : ""));
            objetivo.recibirDano(dano);
            flechas--;
            System.out.println(nombre + " | Flechas restantes: " + flechas);
        } else {
            System.out.println(nombre + " no puede disparar — no le quedan flechas!");
        }
    }

    @Override
    public String getTipoPersonaje() {
        return "Arquero";
    }

    // ── Equipable ─────────────────────────────────────────────────────────────

    @Override
    public void equiparItem(String item) {
        this.itemEquipado = item;
        System.out.println(nombre + " equipa: " + itemEquipado);
    }

    @Override
    public String getItemEquipado() {
        return itemEquipado;
    }

    // ── Propio ────────────────────────────────────────────────────────────────

    public void recargarFlechas(int cantidad) {
        flechas += cantidad;
        System.out.println(nombre + " recarga " + cantidad
                + " flechas. Total: " + flechas);
    }

    public int getFlechas() { return flechas; }
    public int getAlcance() { return alcance; }
}