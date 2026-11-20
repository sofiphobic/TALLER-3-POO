package taller03;

public class CazaCalabazas implements Minijuego {

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Caza de calabazas";
	}

	@Override
	public String getDescripcion() {
		return "Recorre el cementerio de punta a punta antes de que el sepultero te vea";
	}

	@Override
	public String getHabilidadPremiada() {
		// TODO Auto-generated method stub
		return "Mordedor";
	}

	@Override
	public double calcularPotencial(Monstruo m) {
		// TODO Auto-generated method stub
		return m.potencialCaza();
	}

}
