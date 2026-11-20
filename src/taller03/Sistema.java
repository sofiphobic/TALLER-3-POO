package taller03;
import java.util.ArrayList;
import java.util.LinkedList;

public class Sistema {
	private ArrayList<Monstruo> listaMonstruos = new ArrayList<>();
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private Minijuego[] arregloMinijuegos;
    
    //  variables de la sesion 
    private Jugador jugadorActual;
    private boolean esNuevo;
    private boolean[] jugados;
    private int[] puntajes;
    private LinkedList<RegistroSesion> historial;
    private int totalAcumulado;

    public Sistema() {
        arregloMinijuegos = new Minijuego[4];
        arregloMinijuegos[0] = new CarreraCementerio();
        arregloMinijuegos[1] = new CazaCalabazas();
        arregloMinijuegos[2] = new LanzamientoLapidas();
        arregloMinijuegos[3] = new SobrevivirAmanecer();
    }

    public void agregarJugador(String nombre, int puntaje) {
        Jugador nuevoJugador = new Jugador(nombre, puntaje);
        listaJugadores.add(nuevoJugador);
    }

    public boolean agregarMonstruo(String[] datos) {
        try {
            String tipo = datos[0];
            int vida = Integer.parseInt(datos[1]);
            int vel = Integer.parseInt(datos[2]);
            int fue = Integer.parseInt(datos[3]);
            int res = Integer.parseInt(datos[4]);

            if (vida < 1 || vida > 200) 
            	return false;
            if (vel < 1 || vel > 100) 
            	return false;
            if (fue < 1 || fue > 100) 
            	return false;
            if (res < 1 || res > 100) 
            	return false;

            if (tipo.equals("Zombi") && datos.length == 6) {
                int mordida = Integer.parseInt(datos[5]);
                if (mordida >= 1 && mordida <= 100) {
                    listaMonstruos.add(new Zombi(vida, vel, fue, res, mordida));
                    return true;
                }
            } else if (tipo.equals("Vampiro") && datos.length == 7) {
                int vuelo = Integer.parseInt(datos[5]);
                int sol = Integer.parseInt(datos[6]);
                if (vuelo >= 1 && vuelo <= 100 && sol >= 1 && sol <= 100) {
                    listaMonstruos.add(new Vampiro(vida, vel, fue, res, vuelo, sol));
                    return true;
                }
            } else if (tipo.equals("Momia") && datos.length == 6) {
                int vendas = Integer.parseInt(datos[5]);
                if (vendas >= 1 && vendas <= 300) {
                    listaMonstruos.add(new Momia(vida, vel, fue, res, vendas));
                    return true;
                }
            } else if (tipo.equals("HombreLobo") && datos.length == 7) {
                int ferocidad = Integer.parseInt(datos[5]);
                int luna = Integer.parseInt(datos[6]);
                if (ferocidad >= 1 && ferocidad <= 100 && luna >= 1 && luna <= 4) {
                    listaMonstruos.add(new HombreLobo(vida, vel, fue, res, ferocidad, luna));
                    return true;
                }
            }
        } catch(Exception e) {
            return false;
        }
        return false;
    }

    public boolean iniciarSesion(String nombre) {
        if (nombre.equals("") || nombre.contains(";")) {
            return false;
        }
        
        jugadorActual = null;
        esNuevo = true;
        
        for (int i = 0; i < listaJugadores.size(); i++) { // se busca si existe
            Jugador j = listaJugadores.get(i);
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                jugadorActual = j;
                esNuevo = false;
            }
        }
        
        if (jugadorActual == null) {
            jugadorActual = new Jugador(nombre, 0);
        }

        
        for (int i = 0; i < listaMonstruos.size(); i++) { // se reinicia para volver a jugar
            listaMonstruos.get(i).setUsado(false);
        }
        
        jugados = new boolean[4];
        puntajes = new int[4];
        historial = new LinkedList<>();
        totalAcumulado = 0;
        
        return true;
    }

    public Jugador getJugadorActual() { 
    	return jugadorActual; 
    	}
    public boolean getEsNuevo() { 
    	return esNuevo; 
    	}
    public int getTotalAcumulado() { 
    	return totalAcumulado; 
    	}
    public ArrayList<Monstruo> getMonstruos() {
    	return listaMonstruos; 
    	}
    public ArrayList<Jugador> getJugadores() { 
    	return listaJugadores; }
    public Minijuego[] getMinijuegos() { return arregloMinijuegos; 
    }
    public boolean[] getJugados() { 
    	return jugados; 
    	}
    public LinkedList<RegistroSesion> getHistorial() { 
    	return historial; 
    	}

    public void guardarJuego(int indice, Monstruo m, String habilidad, double potencial, boolean efectiva) {
        m.setUsado(true);
        jugados[indice] = true;
        
        double multiplicador = 1.0;
        if (efectiva == true) {
            multiplicador = 1.5;
        }
        
        int puntosCalculados = (int) Math.round(potencial * 10 * multiplicador);
        puntajes[indice] = puntosCalculados;
        totalAcumulado = totalAcumulado + puntosCalculados;
        
        RegistroSesion registro = new RegistroSesion(arregloMinijuegos[indice].getNombre(), m.getTipo(), habilidad, puntosCalculados);
        historial.add(registro);
    }

    public boolean cerrarSesion() {
        if (historial.size() == 0) {
            return false;
        }

        if (esNuevo == true) {
            jugadorActual.setPuntajeMaximo(totalAcumulado);
            listaJugadores.add(jugadorActual);
            return true;
        } else {
            if (totalAcumulado > jugadorActual.getPuntajeMaximo()) {
                jugadorActual.setPuntajeMaximo(totalAcumulado);
                return true;
            }
        }
        return false;
    }

    public void ordenarRanking() {
        for (int i = 0; i < listaJugadores.size() - 1; i++) { //bubble sort
            for (int j = i + 1; j < listaJugadores.size(); j++) {
                Jugador j1 = listaJugadores.get(i);
                Jugador j2 = listaJugadores.get(j);
                
                boolean debeCambiar = false;
                if (j2.getPuntajeMaximo() > j1.getPuntajeMaximo()) {
                    debeCambiar = true;
                } else if (j2.getPuntajeMaximo() == j1.getPuntajeMaximo()) {
                    if (j1.getNombre().compareTo(j2.getNombre()) > 0) {
                        debeCambiar = true;
                    }
                }
                
                if (debeCambiar == true) {
                    listaJugadores.set(i, j2);
                    listaJugadores.set(j, j1);
                }
            }
        }
    }
}

