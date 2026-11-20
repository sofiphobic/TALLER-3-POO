package taller03;

public class Jugador {
	private String nombre;
    private int puntajeMaximo;

    public Jugador(String nombreNuevo, int puntajeNuevo) {
        nombre = nombreNuevo;
        puntajeMaximo = puntajeNuevo;
    }

    public String getNombre() { 
    	return nombre;
    	}
    public int getPuntajeMaximo() {
    	return puntajeMaximo; 
    	}
    public void setPuntajeMaximo(int puntajeNuevo) { 
    	puntajeMaximo = puntajeNuevo; 
    	}
}

