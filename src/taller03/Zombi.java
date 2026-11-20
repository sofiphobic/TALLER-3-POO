package taller03;

public class Zombi extends Monstruo implements Mordedor, Regenerable{

	private int fuerzaDeMordida;

    public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
        super("Zombi", vida, velocidad, fuerza, resistencia);
        this.fuerzaDeMordida = fuerzaDeMordida;
    }

    public String morder() { 
    	return "El zombi da un mordisco letal."; 
    	}
    public String regenerarse() { 
    	return "El zombi reensambla sus extremidades caídas."; 
    	}
    public String getAtributosPropios() { 
    	return "| Mordida " + fuerzaDeMordida; 
    }

    public double potencialCarrera() { 
    	return (velocidad * resistencia) / 100.0; 
    	}
    public double potencialCaza() { 
    	return (fuerzaDeMordida * vida) / 100.0 + (resistencia / 2.0); 
    	}
    public double potencialLanzamiento() { 
    	return (fuerza * resistencia) / 100.0;
    	}
    public double potencialAmanecer() { 
    	return (resistencia * vida) / 100.0; 
    	}
}
