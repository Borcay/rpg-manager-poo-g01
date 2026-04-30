package rpgmanager.model;

import rpgmanager.interfaces.Habilidoso;
import rpgmanager.interfaces.Sanador;

public class Mago extends Personaje implements Habilidoso, Sanador {

    private int mana;
    private int manaMax;

    public Mago(String nombre, int nivel) {
        super(nombre, nivel, 60 + nivel * 5);
        this.manaMax = 80 + nivel * 10;
        this.mana    = this.manaMax;
    }

    // ── Personaje abstractos ──────────────────────────────────────────────────

    @Override
    public void atacar(Personaje objetivo) {
        if (mana >= 20) {
            int dano = 25 + nivel * 5;
            System.out.println(nombre + " lanza un hechizo sobre "
                    + objetivo.getNombre() + " causando " + dano + " de daño!");
            objetivo.recibirDano(dano);
            mana -= 20;
            System.out.println(nombre + " | Mana: " + mana + "/" + manaMax);
        } else {
            System.out.println(nombre + " no puede lanzar el hechizo — mana insuficiente!");
        }
    }

    @Override
    public String getTipoPersonaje() {
        return "Mago";
    }

    // ── Habilidoso ────────────────────────────────────────────────────────────

    @Override
    public void usarHabilidadEspecial(Personaje objetivo) {
        if (mana >= 20) {
            System.out.println(nombre + " lanza Bola de Fuego sobre "
                    + objetivo.getNombre() + " causando 40 de daño!");
            objetivo.recibirDano(40);
            mana -= 20;
            System.out.println(nombre + " | Mana: " + mana + "/" + manaMax);
        } else {
            System.out.println(nombre + " no tiene mana suficiente para Bola de Fuego!");
        }
    }

    @Override
    public int getCostoHabilidad() {
        return 20;
    }

    @Override
    public String getNombreHabilidad() {
        return "Bola de Fuego";
    }

    // ── Sanador ───────────────────────────────────────────────────────────────

    @Override
    public void sanar(Personaje objetivo) {
        int vidaAntes = objetivo.getPuntosVida();
        int vidaNueva = Math.min(objetivo.getPuntosVidaMax(), vidaAntes + 25);
        // usamos reflexion para modificar desde fuera — alternativa: metodo curar() en Personaje
        // Como puntosVida es protected, Mago (mismo paquete) puede accederlo directamente:
        objetivo.puntosVida = vidaNueva;
        System.out.println(nombre + " sana a " + objetivo.getNombre()
                + " — HP: " + vidaNueva + "/" + objetivo.getPuntosVidaMax());
    }

    @Override
    public int getPotenciaSanacion() {
        return 25;
    }

    // ── Propio ────────────────────────────────────────────────────────────────

    public void recuperarMana(int cantidad) {
        mana = Math.min(manaMax, mana + cantidad);
        System.out.println(nombre + " recupera mana. Mana: " + mana + "/" + manaMax);
    }

    public int getMana()    { return mana;    }
    public int getManaMax() { return manaMax; }
}