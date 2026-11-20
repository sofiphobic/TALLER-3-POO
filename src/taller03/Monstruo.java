package taller03;

public abstract class Monstruo {
	protected String tipo;
    protected int vida;
    protected int velocidad;
    protected int fuerza;
    protected int resistencia;
    protected boolean usado;

    public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resistencia) {
        this.tipo = tipo;
        this.vida = vida;
        this.velocidad = velocidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.usado = false; // disponible
    }

    public String getTipo() { 
		return tipo; 
	}
    public boolean getUsado() { 
		return usado; 
	}
    public void setUsado(boolean estado) { 
		usado = estado; 
	}

    public abstract String getAtributosPropios();
    public abstract double potencialCarrera();
    public abstract double potencialCaza();
    public abstract double potencialLanzamiento();
    public abstract double potencialAmanecer();
}
