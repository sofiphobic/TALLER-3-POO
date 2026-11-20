package taller03;

public class Momia extends Monstruo implements Regenerable, Furioso {
	private int longitudVendajes;

    public Momia(int vida, int vel, int fue, int res, int longVendajes) {
        super("Momia", vida, vel, fue, res);
        longitudVendajes = longVendajes;
    }

    public String regenerarse() {
    	return "La momia se envuelve en nuevas vendas místicas."; 
    	}
    public String enfurecerse() {
    	return "La momia desata una antigua maldición de furia."; 
    }
    public String getAtributosPropios() { 
    	return "| Vendajes " + longitudVendajes; 
    	}

    public double potencialCarrera() { 
    	return (velocidad * 100.0) / (100.0 + longitudVendajes); 
    	}
    public double potencialCaza() { 
    	return (longitudVendajes * fuerza) / 200.0; 
    	}
    public double potencialLanzamiento() { 
    	return fuerza + (longitudVendajes / 10.0); 
    	}
    public double potencialAmanecer() { 
    	return ((resistencia + vida) / 2.0) * (1.0 + (longitudVendajes / 300.0)); 
    	}
}
