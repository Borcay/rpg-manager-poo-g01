package rpgmanager.model;

public abstract class Personaje {

    protected String nombre;
    protected int nivel;
    protected int puntosVida;
    protected int puntosVidaMax;

    public Personaje(String nombre, int nivel, int puntosVidaMax) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntosVidaMax = puntosVidaMax;
        this.puntosVida = puntosVidaMax;
    }

    public void recibirDano(int dano) {
        puntosVida = Math.max(0, puntosVida - dano);
        System.out.println(nombre + " recibe " + dano + " de daño. "
                + "HP: " + puntosVida + "/" + puntosVidaMax
                + (puntosVida == 0 ? " — ¡Ha sido derrotado!" : ""));
    }

    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public abstract void atacar(Personaje objetivo);

    public abstract String getTipoPersonaje();

    @Override
    public String toString() {
        return "[" + getTipoPersonaje() + "] " + nombre + " Nv." + nivel+ " | HP: " + puntosVida + "/" + puntosVidaMax;
    }

    public String getNombre(){ 
        return nombre;        
    }
    
    public int getNivel(){ 
        return nivel;         
    }
    public int getPuntosVida(){ 
        return puntosVida;    
    }
    public int getPuntosVidaMax(){ 
        return puntosVidaMax; 
    }
}