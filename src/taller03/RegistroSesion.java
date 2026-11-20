package taller03;

public class RegistroSesion {
	String nombreMinijuego;
    String tipoMonstruo;
    String habilidadUsada;
    int puntajeObtenido;
    
    public RegistroSesion(String juego, String monstruo, String habilidad, int puntaje) {
        nombreMinijuego = juego;
        tipoMonstruo = monstruo;
        habilidadUsada = habilidad;
        puntajeObtenido = puntaje;
    }
}

