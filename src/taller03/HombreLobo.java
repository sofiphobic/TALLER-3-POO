package taller03;

public class HombreLobo extends Monstruo implements Mordedor, Furioso {
	private int ferocidad;
    private int faseLunar;

    public HombreLobo(int vida, int vel, int fue, int res, int ferocidad, int faseLunar) {
        super("HombreLobo", vida, vel, fue, res);
        this.ferocidad = ferocidad;
        this.faseLunar = faseLunar;
    }

    public String morder() { 
    	return "El hombre lobo desgarra con sus fauces."; 
    	}
    public String enfurecerse() { 
    	return "El hombre lobo aúlla a la luna enfurecido."; 
    	}
    public String getAtributosPropios() { 
    	return "| Ferocidad " + ferocidad + " | Luna " + faseLunar; 
    	}

    public double potencialCarrera() { 
    	return (velocidad + (ferocidad / 5.0)) * (faseLunar / 4.0); 
    	}
    public double potencialCaza() { 
    	return (ferocidad * (faseLunar / 4.0)) + (velocidad / 4.0); 
    	}
    public double potencialLanzamiento() { 
    	return fuerza + (ferocidad * (faseLunar / 8.0)); 
    	}
    public double potencialAmanecer() { 
    	return ((resistencia + vida) / 2.0) * ((5.0 - faseLunar) / 4.0); 
    	}
}

