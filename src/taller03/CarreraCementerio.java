package taller03;

public class CarreraCementerio implements Minijuego {

	@Override
	public String getNombre() {
		return "Carrera del Cementerio";
	}

	@Override
	public String getDescripcion() {
		return "Recorre el cementerio de punta a punta antes de que el sepultero te vea";
	}

	@Override
	public String getHabilidadPremiada() {
		return "Volador";
	}

	@Override
	public double calcularPotencial(Monstruo m) {
		return m.potencialCarrera();
	}

}
