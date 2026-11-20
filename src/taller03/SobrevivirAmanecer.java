package taller03;

public class SobrevivirAmanecer implements Minijuego {

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return "Sobrevivir al amanecer";
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return "Aguanta en piue cuadno salfan los primeros rayos del sol";
	}

	@Override
	public String getHabilidadPremiada() {
		// TODO Auto-generated method stub
		return "Regenerable";
	}

	@Override
	public double calcularPotencial(Monstruo m) {
		// TODO Auto-generated method stub
		return m.potencialAmanecer();
	}

}
