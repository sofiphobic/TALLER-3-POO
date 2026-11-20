package taller03;

public class LanzamientoLapidas implements Minijuego {

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Lanzamiento de lapidas";
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return "Lanza una lapida lo mas lejos posible";
	}

	@Override
	public String getHabilidadPremiada() {
		// TODO Auto-generated method stub
		return "Furioso";
	}

	@Override
	public double calcularPotencial(Monstruo m) {
		// TODO Auto-generated method stub
		return m.potencialLanzamiento();
	}

}
