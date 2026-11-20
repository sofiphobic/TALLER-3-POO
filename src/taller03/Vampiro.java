package taller03;

public class Vampiro extends Monstruo implements Mordedor, Volador{
	private int velocidadDeVuelo;
    private int resistenciaAlSol;

    public Vampiro(int vida, int vel, int fue, int res, int velVuelo, int resSol) {
        super("Vampiro", vida, vel, fue, res);
        velocidadDeVuelo = velVuelo;
        resistenciaAlSol = resSol;
    }

    public String morder() { 
    	return "El vampiro clava sus colmillos rápidamente."; 
    }
    public String volar() { 
    	return "El vampiro se transforma en murciélago y alza el vuelo."; 
    	}
    public String getAtributosPropios() { 
    	return "| Vuelo " + velocidadDeVuelo + " | Sol " + resistenciaAlSol; 
    	}

    public double potencialCarrera() { 
    	return (velocidad + (2.0 * velocidadDeVuelo)) / 2.0; 
    	}
    public double potencialCaza() { 
    	return (fuerza + velocidad) / 3.0; 
    	}
    public double potencialLanzamiento() { 
    	return fuerza + (velocidadDeVuelo / 4.0); }
    
    public double potencialAmanecer() { 
    	return (resistencia * resistenciaAlSol) / 100.0; 
    	}
}

