package rpgmanager.model;

public class Guerrero extends Personaje {

    private int fuerza;
    private int defensa;

    public Guerrero(String nombre, int nivel) {
        super(nombre, nivel, 100 + nivel * 10);
        this.fuerza  = 15 + nivel * 3;
        this.defensa = 10 + nivel * 2;
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(nombre + " golpea a " + objetivo.getNombre()
                + " con " + fuerza + " de daño!");
        objetivo.recibirDano(fuerza);
    }

    @Override
    public String getTipoPersonaje() {
        return "Guerrero";
    }

    public void usarEscudo() {
        System.out.println(nombre + " bloquea con su escudo. Defensa: " + defensa);
    }

    public int getFuerza()  { return fuerza;  }
    public int getDefensa() { return defensa; }
}